package com.sky.hiwise.design.lesson.db;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCTest {

    /**
     *
     Driver接口
     - Driver接口由数据库厂家提供，对于Java开发者而言，只需要使用Driver接口就可以了。
     - 在编程中要连接数据库，必须先装载特定厂商的数据库驱动程序。不同的数据库有不同的装载方法。
     - 驱动：就是各个厂商实现Sun公司提出的JDBC接口。即对Connection等接口的实现类的jar文件。
     - 装载mysql驱动：Class.forName("com.mysql.jdbc.Driver");
     - 装载oracle驱动：Class.forName("com.jdbc.driver.OracleDriver");
     DriverManager接口
     - DriverManager接口是JDBC的管理层，作用于用户和驱动程序之间。
     - DriverManager跟踪可用的驱动程序，并在数据库和相应的驱动之间建立连接。
     Connection接口
     - Connection与特定数据库的连接(会话)，在连接上下文中执行SQL语句并返回结果。
     - DriverManager的getConnection()方法建立在JDBC URL中定义的数据库Connection连接上
     Mysql：jdbc:mysql://host:port/database Oracle：jdbc:oracle:thin@host:port/database
     Statement接口
     - 用于执行静态SQL语句并返回它所生成结果的对象。
     - 三种Statement类：
     - Statement：
     由createStatement创建，用于发送简单的SQL语句（不带参数）
     - PreparedStatement：
     - 继承自Statement接口，有preparedStatement创建，用于发送含有一个或者多个输入参数的SQL语句。PreparedStatement对象
     比Statement对象效率更高，并且是防止SQL注入。我们一般都使用PreparedStatement。
     - CallableStatement：
     - 继承自preparedStatement。由方法prePareCall创建，用于调用存储过程。
     - 常用的Statement方法
     - execute()：运行语句，返回是否有结果集。
     - executeQuery()：运行select语句，返回ResultSet结果节
     - executeUpdate()：运行insert/update/delete操作，返回更新的行数。
     ResultSet接口
     - Statement执行SQL语句时返回Result结果集。
     - ResultSet提供的检索不同类型字段的方法，常用的有：
     - getString()：获得在数据库里是varchar，char等数据类型的对象。
     - getFloat()：获得数据库里是Float类型的对象
     - getDate()：获得数据库里是Date类型的对象
     - getBoolean()：获得数据库里是Boolean类型的数据
     - 依序关闭使用的对象和连接
     Result->Statement->Connection
     DDL:操作数据库对象 包括创建create 删除drop 和修改alter数据库对象
     DML:主要操作数据库表数据 主要由insert into、update、delete from三个命令组成
     * @param args
     */
    public static void main(String[] args) {

        test();
    }

    public static Connection getMySqlConnection() throws SQLException, ClassNotFoundException {
       // Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://localhost:3306/test";
        String userName = "root";
        String password = "LGsky@123456";
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }


    public static void test() {
        //连接对象
        Connection conn = null;
        try {
            conn = getMySqlConnection();
            String selectSql = "select id,name,sex,age from user";

            //testExecuteQuery(conn, selectSql);

            //testExecuteUpdate(conn);

            //testExecute(conn, selectSql);

            //testPreparedStatement(conn);

            //testResultSet(conn);

            testTransaction(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("空指针异常");
        }finally {
            try {
                conn.close();
                System.out.println("关闭成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testExecuteQuery(Connection conn, String sql) throws SQLException {
        //test Statement executeQuery
        Statement stm = conn.createStatement();
        ResultSet stmSet = stm.executeQuery(sql);
        print(stmSet);
    }

    public static void testExecuteUpdate(Connection conn) throws SQLException {
        String createTable = "CREATE TABLE `test_user` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `name` varchar(45) NOT NULL DEFAULT '' COMMENT '姓名',\n" +
                "  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',\n" +
                "  `sex` smallint(2) NOT NULL DEFAULT '0' COMMENT '性别',\n" +
                "  `description` varchar(45) NOT NULL DEFAULT '' COMMENT '描述',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息测试表';";
        String insertSql = "INSERT INTO `test`.`user` (`id`, `name`, `age`, `sex`) VALUES ('11', 'sfsf', '23', '1');\n";

        //test executeUpdate 执行DDL语句返回0  执行DML语句返回受影响的条数
        Statement stm = conn.createStatement();
        //int count = stm.executeUpdate(selectSql);
        int count = stm.executeUpdate(insertSql);
        System.out.println(count);
        System.out.println(stm.executeUpdate(createTable));
    }

    public static void testExecute(Connection conn, String selectSql) throws SQLException {
        String insertSql = "INSERT INTO `test`.`user` (`id`, `name`, `age`, `sex`) VALUES ('8', 'sfsf', '23', '1');\n";
        //test execute
        Statement stm = conn.createStatement();
        boolean hasResultSet = stm.execute(selectSql);
        //boolean hasResultSet = stm.execute(insertSql);
        if (hasResultSet) {
            print(stm.getResultSet());
        } else {
            System.out.println("该SQL语句影响记录数：" + stm.getUpdateCount());
        }
    }

    /**
     * PreparedStatement 预编译SQL 性能更好
     * 无须拼接SQL语句编程更简单
     * 防止SQL注入 安全性更好
     * @param conn
     * @throws SQLException
     */
    public static void testPreparedStatement(Connection conn) throws SQLException {
        //test PreparedStatement
        String preparedSelectSql = "select id,name,sex,age from user where name =? and age = ?";
        PreparedStatement cmd=conn.prepareStatement(preparedSelectSql);
        cmd.setString(1,"stelin");
        cmd.setInt(2, 43);
        //执行查询并返回结果集
        ResultSet set=cmd.executeQuery();
        print(set);
    }

    /**
     * getTables（String catalog,String schema,String tableName,String[] types），这个方法带有四个参数，他们表示的含义如下：
     String catalog——要获得表所在的编目。串“”””意味着没有任何编目，Null表示所有编目。
     String schema——要获得表所在的模式。串“”””意味着没有任何模式，Null表示所有模式。该参数可以包含单字符的通配符（“_”）,也可以包含多字符的通配符（“%”）。
     String tableName——指出要返回表名与该参数匹配的那些表，该参数可以包含单字符的通配符（“_”）,也可以包含多字符的通配符（“%”）。
     String types ——一个指出返回何种表的数组。可能的数组项是：”TABLE”，”VIEW”，”SYSTEM TABLE”，”GLOBAL TEMPORARY”，”LOCAL TEMPORARY”，”ALIAS”，“SYSNONYM”。
     通过getTables（）方法返回一个表的信息的结果集。这个结果集包括字段有：TABLE_CAT表所在的编目。TABLE_SCHEM表所在的模式，TABLE_NAME表的名称。TABLE_TYPE标的类型。REMARKS一段解释性的备注。通过这些字段可以完成表的信息的获取。
     还有两个方法一个是获得列 getColumns(String catalog,String schama,String tablename,String columnPattern)
     一个是获得关键字的方法getPrimaryKeys(String?catalog, String?schema, String?table)这两个方法中的参数的含义和上面的介绍的是相同的。
     凡是pattern的都是可以用通配符匹配的。getColums()返回的是结果集，这个结果集包括了列的所有信息，类型，名称，可否为空等。getPrimaryKey（）则是返回了某个表的关键字的结果集。
     * @param conn
     * @throws SQLException
     */
    public static void testResultSet(Connection conn) throws SQLException {
        String preparedSelectSql = "select id,name,sex,age from user where name =? and age = ?";
        PreparedStatement cmd=conn.prepareStatement(preparedSelectSql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        cmd.setString(1,"stelin");
        cmd.setInt(2, 43);
        //执行查询并返回结果集
        ResultSet set=cmd.executeQuery();
//        while (set.next()) {
//            set.updateInt(3, 34);
//            set.updateRow();
//        }

        //ResultSetMetaData 管理结果集
//        ResultSetMetaData rsmd = set.getMetaData();
//        System.out.println(rsmd.getColumnCount());
//        for (int i = 0; i < rsmd.getColumnCount(); i++) {
//            System.out.println("column name:" + rsmd.getColumnName(i+1));
//        }
//
//        DatabaseMetaData dbmd = conn.getMetaData();
//        ResultSet dbTypes = dbmd.getTableTypes();
//        printResultSet(dbTypes);
//        ResultSet tables = dbmd.getTables(null,null, "%", new String[] {"TABLE"});
//        System.out.println("数据库表信息");
//        List tableNameList = new ArrayList();
//
//        while (tables.next()) {
//            tableNameList.add(tables.getString("TABLE_NAME"));
//        }
//        System.out.println(tableNameList);

        //CachedRowSet RowSet 集合离线处理数据
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crt = factory.createCachedRowSet();
        crt.populate(set);
        set.close();
        conn.close();
        print(crt);
    }

    public static void testTransaction(Connection conn) throws SQLException, NullPointerException {
        String insertSql1 = "INSERT INTO `test`.`user` (`id`, `name`, `age`, `sex`) VALUES ('15', 'sfsf', '23', '1');\n";
        String insertSql2 = "INSERT INTO `test`.`user` (`id`, `name`, `age`, `sex`) VALUES ('25', 'sfsf', '23', '1');\n";

        String str = null;
        conn.setAutoCommit(false);
        Statement cmd = conn.createStatement();
        cmd.executeUpdate(insertSql1);

        System.out.println(str.length());
        cmd.executeUpdate(insertSql2);
        conn.commit();
        //conn.rollback();
    }

    public static void print(ResultSet set) throws SQLException {
        //遍历每一行
        while (set.next()) {
            //取出当前行的id值
            System.out.print(set.getInt("id")+"\t");
            System.out.print(set.getString("name")+"\t");
            System.out.print(set.getInt("sex")+"\t");
            System.out.print(set.getInt("age")+"\n");
        }

        //关闭结果集
        set.close();
    }

    public static void printResultSet(ResultSet set) throws SQLException {
        ResultSetMetaData rsmd = set.getMetaData();
        for(int i = 0; i < rsmd.getColumnCount(); i++) {
            System.out.println(rsmd.getColumnName(i+1));
        }
        while (set.next()) {
            for (int j = 0; j < rsmd.getColumnCount(); j++) {
                System.out.println(set.getString(j+1));
            }
        }
        set.close();
    }

}
