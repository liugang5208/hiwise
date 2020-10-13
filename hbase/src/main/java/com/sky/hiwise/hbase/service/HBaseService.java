package com.sky.hiwise.hbase.service;

import com.sky.hiwise.hbase.common.*;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HBaseService implements HbaseOperations {

    @Autowired
    Connection hbaseConnection;

    /**
     * find
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param action
     * @param <T>
     * @return
     */
    public <T> List<T> find(String tableName, String startRowKey, String stopRowKey, final RowMapper<T> action) {
        return scanner(tableName, Bytes.toBytes(startRowKey), Bytes.toBytes(stopRowKey), new RowMapperResultsExtractor<T>(action));
    }

    private  <T> T scanner(String tableName, byte[] startRowKey, byte[] stopRowKey, ResultsExtractor<T> resultsExtractor) {
        T t;
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Scan scan = new Scan();
            scan.setStartRow(startRowKey);
            scan.setStopRow(stopRowKey);
            scan.setCaching(1000);
            ResultScanner results = table.getScanner(scan);
            t = resultsExtractor.extractData(results);
        } catch (Exception e) {
            String msg = String.format("scan table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return t;
    }

    /**
     * find
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param filterList
     * @param action
     * @param <T>
     * @return
     */
    public <T> List<T> find(String tableName, String startRowKey, String stopRowKey, FilterList filterList, final RowMapper<T> action) {
        return scanner(tableName, Bytes.toBytes(startRowKey), Bytes.toBytes(stopRowKey),
                filterList, new RowMapperResultsExtractor<>(action));
    }

    private <T> T scanner(String tableName, byte[] startRowKey, byte[] stopRowKey, FilterList filterList, ResultsExtractor<T> resultsExtractor) {
        T t;
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Scan scan = new Scan();
            scan.setStartRow(startRowKey);
            scan.setStopRow(stopRowKey);
            scan.setCaching(1000);
            scan.setFilter(filterList);
            ResultScanner results = table.getScanner(scan);
            t = resultsExtractor.extractData(results);
        } catch (Exception e) {
            String msg = String.format("scan table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return t;
    }

    /**
     * existsRow
     * @param tableName
     * @param row
     * @return
     */
    public boolean existsRow(String tableName, String row) {
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Get g = new Get(Bytes.toBytes(row));
            return table.exists(g);
        } catch (Exception e) {
            String msg = String.format("check exists row from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
    }

    /**
     * getRow
     * @param tableName
     * @param row
     * @param filterList
     * @param action
     * @param <T>
     * @return
     */
    public <T> T getRow(String tableName, String row, FilterList filterList, final RowMapper<T> action) {
        T t;
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Get g = new Get(Bytes.toBytes(row));
            g.setFilter(filterList);
            Result rs = table.get(g);
            t = action.mapRow(rs, 0);
        } catch (Exception e) {
            String msg = String.format("get row from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return t;
    }

    /**
     * getRow
     * @param tableName
     * @param row
     * @param action
     * @param <T>
     * @return
     */
    public <T> T getRow(String tableName, String row, final RowMapper<T> action) {
        T t;
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Get g = new Get(Bytes.toBytes(row));
            Result rs = table.get(g);
            t = action.mapRow(rs, 0);
        } catch (Exception e) {
            String msg = String.format("get row from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return t;
    }

    /**
     * getRows
     * @param tableName
     * @param rows
     * @param action
     * @param <T>
     * @return
     */
    public <T> List<T> getRows(String tableName, List<String> rows, final RowMapper<T> action) {
        List<T> list = new ArrayList<>();
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            List<Get> gets = new ArrayList<>();
            for (String row : rows) {
                if (row != null) {
                    Get g = new Get(Bytes.toBytes(row));
                    gets.add(g);
                }
            }
            if (gets.size() > 0) {
                Result[] results = table.get(gets);
                for (Result result : results) {
                    list.add(action.mapRow(result, 0));
                }
            }
        } catch (Exception e) {
            String msg = String.format("get rows from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return list;
    }

    /**
     * putRow
     * @param tableName
     * @param row
     * @param columnFamily
     * @param qualifier
     * @param data
     * @return
     */
    public boolean putRow(String tableName, String row, String columnFamily, String qualifier, String data) {
        try {
            Put put = new Put(Bytes.toBytes(row));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),
                    Bytes.toBytes(data));
            putRows(tableName, Arrays.asList(put));
        } catch (Exception e) {
            String msg = String.format("put row from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * putRow
     * @param tableName
     * @param put
     * @return
     */
    public boolean putRow(String tableName, Put put) {
        try {
            putRows(tableName, Arrays.asList(put));
        } catch (Exception e) {
            String msg = String.format("put row from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * putRows
     * @param tableName
     * @param puts
     * @return
     */
    public boolean putRows(String tableName, List<Put> puts) {
        final BufferedMutator.ExceptionListener listener = (e, mutator) -> {
            String msg = String.format("put rows from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        };
        BufferedMutatorParams params = new BufferedMutatorParams(TableName.valueOf(tableName))
                .listener(listener);
        params.writeBufferSize(5 * 1024 * 1024);
        try (final BufferedMutator mutator = hbaseConnection.getBufferedMutator(params)) {
            mutator.mutate(puts);
            mutator.flush();
        } catch (Exception e) {
            String msg = String
                    .format("put rows from table=%s error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * deleteColumnFamily
     * @param tableName
     * @param columnFamilyName
     * @return
     */
    public boolean deleteColumnFamily(String tableName, String columnFamilyName) {
        try (HBaseAdmin admin = (HBaseAdmin) hbaseConnection.getAdmin()) {
            admin.deleteColumn(tableName, columnFamilyName);
        } catch (IOException e) {
            String msg = String
                    .format("delete table=%s , column family=%s error. msg=%s", tableName, columnFamilyName,
                            e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * delete Qualifier
     * @param tableName
     * @param rowName
     * @param columnFamilyName
     * @param qualifierName
     * @return
     */
    public boolean deleteQualifier(String tableName, String rowName, String columnFamilyName, String qualifierName) {
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Delete delete = new Delete(rowName.getBytes());
            delete.addColumns(columnFamilyName.getBytes(), qualifierName.getBytes());
            table.delete(delete);
        } catch (Exception e) {
            String msg = String
                    .format("delete table=%s , column family=%s , qualifier=%s error. msg=%s", tableName,
                            columnFamilyName, qualifierName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * delete row
     * @param tableName
     * @param rowName
     * @return
     */
    public boolean deleteRow(String tableName, String rowName) {
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            Delete delete = new Delete(Bytes.toBytes(rowName));
            table.delete(delete);
        } catch (Exception e) {
            String msg = String
                    .format("delete table=%s , row=%s error. msg=%s", tableName, rowName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

    /**
     * delete rows
     * @param tableName
     * @param rows
     * @return
     */
    public boolean deleteRows(String tableName, List<String> rows) {
        try (Table table = hbaseConnection.getTable(TableName.valueOf(tableName))) {
            List<Delete> list = new ArrayList<>();
            for (String row : rows) {
                Delete d = new Delete(Bytes.toBytes(row));
                list.add(d);
            }
            if (list.size() > 0) {
                table.delete(list);
            }
        } catch (Exception e) {
            String msg = String
                    .format("delete table=%s , rows error. msg=%s", tableName, e.getMessage());
            throw new HBaseServerException(ErrorCodes.ERROR_HBASE, msg);
        }
        return true;
    }

}
