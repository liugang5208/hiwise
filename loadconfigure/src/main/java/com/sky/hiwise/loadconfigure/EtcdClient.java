package com.sky.hiwise.loadconfigure;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coreos.jetcd.Client;
import com.coreos.jetcd.ClientBuilder;
import com.coreos.jetcd.Watch;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.options.WatchOption;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * 配置etcd
 *
 * @author linjunli
 */
public class EtcdClient {

    private static final Logger logger = LoggerFactory.getLogger(EtcdClient.class);

    private Client client;

    private final int TYPE_STRING = 1;
    private final int TYPE_JSON = 2;
    private final int TYPE_PROPERTY = 3;
    private final int TYPE_YAML = 4;

    private String userName;

    private String password;

    private String[] endpoints;

    private WatcherHelper helper;

    private Map<String, String> stringMap = new ConcurrentHashMap<>();

    private Map<String, Properties> propertiesMap = new ConcurrentHashMap<>();

    private Map<String, JSONObject> jsonMap = new ConcurrentHashMap<>();

    private String watchKeyPrefix;

    @PostConstruct
    public void init() {
        if (endpoints != null && userName != null && password != null) {
            ClientBuilder clientBuilder = Client.builder().endpoints(endpoints)
                    .user(ByteSequence.fromString(userName)).password(ByteSequence.fromString(password));
            this.client = clientBuilder.build();
            Watch.Watcher watcher;
            //开启监听
            if (!StringUtils.isEmpty(watchKeyPrefix)) {
                WatchOption.Builder builder = WatchOption.newBuilder();
                builder.withPrefix(ByteSequence.fromString(watchKeyPrefix));
                watcher = client.getWatchClient().watch(ByteSequence.fromString(watchKeyPrefix), builder.build());
                helper = new WatcherHelper(watcher);
                helper.addListener(event -> {
                    KeyValue kv = event.getKeyValue();
                    putValue(kv, TYPE_STRING);
                });
            }


        }

    }

    public EtcdClient(String userName, String password, String... endpoints) {
        this.userName = userName;
        this.password = password;
        this.endpoints = endpoints;
        init();
    }

    public EtcdClient() {
    }


    private void putValue(KeyValue kv, int type) {
        switch (type) {
            case TYPE_STRING:
                stringMap.put(kv.getKey().toStringUtf8(), kv.getValue().toStringUtf8());
                break;
            case TYPE_JSON:
                JSONObject json = JSONObject.parseObject(kv.getValue().toStringUtf8());
                jsonMap.put(kv.getKey().toStringUtf8(), json);
                break;
            case TYPE_PROPERTY:
                Properties properties = parseProperty(kv.getValue().toStringUtf8());
                propertiesMap.put(kv.getKey().toStringUtf8(), properties);
                break;
            case TYPE_YAML:
                Properties p = parseYaml(kv.getValue().toStringUtf8());
                propertiesMap.put(kv.getKey().toStringUtf8(), p);
                break;
            default:
                break;

        }
    }


    private Properties parseProperty(String value) {
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(value));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return properties;
    }

    private Properties parseYaml(String value) {
        YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
        yml.setResources(new ByteArrayResource(value.getBytes()));
        return yml.getObject();

    }

    public void saveValue(String key, int type) {
        ByteSequence configKey = ByteSequence.fromString(key);
        //初次解析
        List<KeyValue> kvs = null;
        try {
            kvs = client.getKVClient().get(configKey).get().getKvs();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
        if (kvs.size() == 0) {
            return;
        }
        putValue(kvs.get(0), type);

    }

    public String getString(String key) {
        if (stringMap.containsKey(key)) {
            return stringMap.get(key);
        } else {
            saveValue(key, TYPE_STRING);
        }
        return stringMap.get(key);

    }

    public JSONObject getJSON(String key) {
        if (jsonMap.containsKey(key)) {
            return jsonMap.get(key);
        } else {
            saveValue(key, TYPE_JSON);

        }
        return jsonMap.get(key);

    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (stringMap.containsKey(key)) {
            String json = stringMap.get(key);
            return JSONObject.parseObject(json, clazz);
        } else {
            saveValue(key, TYPE_STRING);

        }
        String json = stringMap.get(key);
        return JSONObject.parseObject(json, clazz);

    }

    public List getList(String key) {
        List list = new ArrayList();
        if (stringMap.containsKey(key)) {
            String value = stringMap.get(key);
            try {
                list = IOUtils.readLines(new StringReader(value));
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            return list;
        } else {
            saveValue(key, TYPE_STRING);
        }
        String value = stringMap.get(key);
        try {
            list = IOUtils.readLines(new StringReader(value));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    public <T> List<T> getJSONList(String key, Class<T> clazz) {
        if (stringMap.containsKey(key)) {
            String json = stringMap.get(key);
            JSONArray array = JSONArray.parseArray(json);
            return array.toJavaList(clazz);
        } else {
            saveValue(key, TYPE_STRING);

        }
        String json = stringMap.get(key);
        JSONArray array = JSONArray.parseArray(json);
        return array.toJavaList(clazz);

    }

    public Properties getProperty(String key) {
        if (propertiesMap.containsKey(key)) {
            return propertiesMap.get(key);
        } else {
            saveValue(key, TYPE_PROPERTY);
        }
        return propertiesMap.get(key);
    }

    public void setWatchKeyPrefix(String watchKeyPrefix) {
        this.watchKeyPrefix = watchKeyPrefix;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(String[] endpoints) {
        this.endpoints = endpoints;
    }
}
