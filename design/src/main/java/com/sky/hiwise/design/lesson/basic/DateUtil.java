package com.sky.hiwise.design.lesson.basic;

//import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static void main(String[] args) {
        test3();
    }

    public static void test() {
        /**
         * Java提供了Data类来处理日期、时间（此处的Date指的是java.util包下的Date类，而不会java.sql包下的Date类），Date对象既包含日期，也包含时间。Date类送JDK1.0开始就存在了，但因为它历史悠久，所以它的大部分构造器、方法都已经过时了，不再推荐使用了。

         Date类提供了6个构造器，其中4个已经Deprecated（Java不再推荐使用，使用不再推荐的构造器编译器会提出警告信息，并导致程序性能、安全性等方面的问题），剩下的两个构造器如下
         ①Date()：生成一个代表当前日期时间的Date对象。该构造器在底层调用了System.currentTimeMillis()获得long整数作为日期参数。

         ②Date(long date)：根据指定的long型整数来生成一个Date对象。该构造器的参数表示创建的Date对象和GMT 1970年1月1日00:00:00之间的时间差，以毫秒作为计时单位。

         Date对象的大部分方法也已经Deprecated了，剩下为数不多的几个方法。
         ①boolean after(Date when):测试该日期是否在指定日期when之后。

         ②boolean before(Date when)：测试该日期是否在指定日期when之前。

         ③long getTime()：返回该时间对于的long型整数，即从GMT 1970-01-01 00:00:00之间的时间差，以毫秒作为单位。

         ④void setTime(long time)：设置该Date对象的事件。
         */
//        long a = 1516950739848L;
//        Date date1 = new Date(a);
//        System.out.println(date1);

//        Date date = new Date();
//        System.out.println(date);
//
//        //如何获取时间戳
//        //方法一
//        System.out.println(date.getTime());
//        //方法二
//        System.out.println(System.currentTimeMillis());
//        //方法三
//        System.out.println(Calendar.getInstance().getTimeInMillis());

        Date d1 = new Date();
        //获取当前时间后100ms的事件
        Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));
    }

    public static void test1() {
        /**
         * Calendar类本身是一个抽象类，它是所有日历类的模板，并提供了一些所有日历通用的方法；但它本身不能直接实例化，程序只能创建Calendar子类的实例，Java本身提供了一个GregorianCalendar类，一个代表格里高利日的子类。
         Calendar类是一个抽象类，所以不能使用构造器来创建Calendar对象。但它提供了几个静态getInstance()方法来获取Calendar对象，这些方法根据TimeZone，Locale类来获取特定的Calendar，
         如果不指定Time、Locale，则使用了默认的TimeZone、Locale来创建Calendar。
         */

        //创建一个默认的Calendar对象
        Calendar calendar = Calendar.getInstance();
        //从Calendar对象中取出Date对象
        Date date = calendar.getTime();
        System.out.println(date);
        //通过Date对象获得对于的Calendar对象
        //因为Calendar/GregorianCalendar没有构造函数可以接收Date对象
        //所以必须先获得一个Calendar实例，然后调用其setTime方法
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        System.out.println(calendar2);

        /**
         * Calendar类提供了大量访问、修改日期时间的方法。很多方法需要一个int类型的field，field是Calendar类的类变量，如Calendar.YEAR、Calendar.MONTH等分别代表了年、月、日、小时、分钟、秒等时间字段。
         需要注意的是，Calendar.MONTH字段代表月份，月份的起始值不是1，而是0，所以要设置8月时，用7而不是8。如下程序示范了Calendar类的常规用法。
         */
        Calendar c = Calendar.getInstance();
        //取出年
        System.out.println(c.get(Calendar.YEAR));
        //取出月份
        System.out.println(c.get(Calendar.MONTH));
        //取出日
        System.out.println(c.get(Calendar.DATE));
        //分别设置年、月、日、小时、分钟、秒
        c.set(2017,10,23,12,32,23);//2003-11-23  12:32:23
        System.out.println(c.getTime());
        //将Calendar往前推1年
        c.add(Calendar.YEAR, -1);//2002-11-23 12:32:23
        //Calendar往前推8个月
        c.roll(Calendar.MONTH, -8);//2002-03-23 12:32:23
        System.out.println(c.getTime());

        Calendar cal = Calendar.getInstance();
        cal.set(2003, 7 , 31);
        //将月份设为9，系统会把cal自动调整到10月1日
        //如果立即修改，系统将会把cal改为10月1日

        //关闭容错性
        //cal.setLenient(false);

        cal.set(Calendar.MONTH, 8);
        //下面代码输出10月1日
        System.out.println(cal.getTime());//①
        //设置DATE字段为5
        cal.set(Calendar.DATE, 5);//②
        System.out.println(cal.getTime());//③

    }

    public static void test2() {

        Date d = new Date();
        //创建一个SimpleDateFormat对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        //将d格式化成日期，输出:公元2016年中第223天
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr2 = sdf2.format(d);
        System.out.println(dateStr2);

        //定义一个任意格式的日期、时间字符串
        String str1 = "2017==08==10 01时17分10秒";
        //根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter formatter1 = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");
        //执行解析
        LocalDateTime dt1 = LocalDateTime.parse(str1,
                formatter1);
        System.out.println(dt1);//输出2016-08-10T01:17:10

        //下面代码再次解析另一个字符串
        String str2 = "2016$$$08$$$10 20时";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy$$$MM$$$dd HH时");
        LocalDateTime dt2 = LocalDateTime.parse(str2,
                formatter2);
        System.out.println(dt2);//输出2016-08-10T20:00
    }

    public static void test3() {
        /**
         * 2.java.sql.Date 是针对SQL语句使用的，new java.sql.Date(new java.util.Date().getTime()，它只包含日期而没有时间部分
         */
        long a = 1516950739848L;
        Date date = new java.sql.Date(a);
        System.out.println(date);
        System.out.println(date.getTime());
        Date time = new java.sql.Time(a);
        System.out.println(time);
        System.out.println(time.getTime());

        Date util2sql = new java.sql.Date(new Date().getTime());
        System.out.println(util2sql);
        Date sql2util = new Date(new java.sql.Date(a).getTime());
        System.out.println(sql2util);

        Date timestamp = new java.sql.Timestamp(a);
        System.out.println(timestamp);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr2 = sdf2.format(date);
        System.out.println(dateStr2);

    }
}
