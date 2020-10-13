package com.sky.hiwise.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;

@Configuration
public class HosServerBeanConfiguration {


  /**
   * create hbase client connection.
   *
   * @return conn
   * @throws IOException ioe.
   */
  @Bean(value = "hbaseConnection")
  public Connection getConnection() throws IOException {
    org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
    config.set("hbase.zookeeper.quorum", "localhost:2181");
    config.set("hbase.zookeeper.property.clientPort", "2181");
    config.set(HConstants.HBASE_RPC_TIMEOUT_KEY, "3600000");
    return ConnectionFactory.createConnection(config);
  }

  @Bean
  public HbaseTemplate hbaseTemplate() {
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    conf.set("hbase.zookeeper.quorum", "localhost");
    conf.set("hbase.zookeeper.property.clientPort", "2181");
    return new HbaseTemplate(conf);
  }


}
