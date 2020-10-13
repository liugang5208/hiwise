package com.sky.hiwise.hbase.service;

import com.sky.hiwise.hbase.common.RowMapper;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.filter.FilterList;

import java.util.List;

public interface HbaseOperations {

    /**
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param action
     * @param <T>
     * @return
     */
    <T> List<T> find(String tableName, String startRowKey, String stopRowKey, final RowMapper<T> action);

    /**
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param filterList
     * @param action
     * @param <T>
     * @return
     */
    <T> List<T> find(String tableName, String startRowKey, String stopRowKey, FilterList filterList, final RowMapper<T> action);

    /**
     * @param tableName
     * @param row
     * @return
     */
    boolean existsRow(String tableName, String row);

    /**
     * @param tableName
     * @param row
     * @param filterList
     * @param action
     * @param <T>
     * @return
     */
    <T> T getRow(String tableName, String row, FilterList filterList, final RowMapper<T> action);

    /**
     * @param tableName
     * @param row
     * @param action
     * @param <T>
     * @return
     */
    <T> T getRow(String tableName, String row, final RowMapper<T> action);

    /**
     * @param tableName
     * @param rows
     * @param action
     * @param <T>
     * @return
     */
    //<T> List<T> getRows(String tableName, List<String> rows, final RowMapper<T> action);
    public <T> List<T> getRows(String tableName, List<String> rows, final RowMapper<T> action);

    /**
     * @param tableName
     * @param row
     * @param columnFamily
     * @param qualifier
     * @param data
     * @return
     */
    boolean putRow(String tableName, String row, String columnFamily, String qualifier, String data);

    /**
     * @param tableName
     * @param put
     * @return
     */
    boolean putRow(String tableName, Put put);

    /**
     * @param tableName
     * @param puts
     * @return
     */
    boolean putRows(String tableName, List<Put> puts);

    /**
     * @param tableName
     * @param columnFamilyName
     * @return
     */
    boolean deleteColumnFamily(String tableName, String columnFamilyName);

    /**
     * @param tableName
     * @param rowName
     * @param columnFamilyName
     * @param qualifierName
     * @return
     */
    boolean deleteQualifier(String tableName, String rowName, String columnFamilyName, String qualifierName);

    /**
     * @param tableName
     * @param rowName
     * @return
     */
    boolean deleteRow(String tableName, String rowName);

    /**
     * @param tableName
     * @param rows
     * @return
     */
    boolean deleteRows(String tableName, List<String> rows);

}
