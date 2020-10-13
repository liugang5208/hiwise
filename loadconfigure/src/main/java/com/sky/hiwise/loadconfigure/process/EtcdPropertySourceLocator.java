package com.sky.hiwise.loadconfigure.process;

import com.sky.hiwise.loadconfigure.EtcdClient;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.yaml.SpringProfileDocumentMatcher;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;
import org.yaml.snakeyaml.resolver.Resolver;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author linjunli
 * @date 2018/9/3
 */
public class EtcdPropertySourceLocator implements PropertySourceLocator {
    private EtcdClient etcdClient;


    public EtcdPropertySourceLocator(EtcdClient etcdClient) {
        this.etcdClient = etcdClient;
    }

    @Override
    public PropertySource<?> locate(Environment environment) {
        String userName = environment.getProperty("etcd.username");
        String password = environment.getProperty("etcd.password");
        String endpoints = environment.getProperty("etcd.etcdUrl");
        etcdClient.setUserName(userName);
        etcdClient.setPassword(password);
        etcdClient.setEndpoints(endpoints.split(","));
        String watchKeyPrefix = environment.getProperty("etcd.watchKeyPrefix");
        if (!StringUtils.isEmpty(watchKeyPrefix)) {
            etcdClient.setWatchKeyPrefix(watchKeyPrefix);
        }
        etcdClient.init();
        Map<String, Object> properties = new HashMap<>();
        String configKeys = environment.getProperty("etcd.configKeys");
        if (!StringUtils.isEmpty(configKeys)) {
            List<String> list = Arrays.asList(environment.getProperty("etcd.configKeys").split(","));
            list.forEach(key -> {
                String value = etcdClient.getString(key);
                Resource resource = new ByteArrayResource(value.getBytes());
                Processor processor = new Processor(resource, null);
                Map<String, Object> source = processor.process();
                properties.putAll(source);
            });

            if (!properties.isEmpty()) {
                return new EtcdPropertySource("etcdPropertySource", properties);
            }
        }
        EtcdPropertySource myPropertySource = new EtcdPropertySource("etcdPropertySource", properties);
        return myPropertySource;
    }


    /**
     * {@link YamlProcessor} to create a {@link Map} containing the property values.
     * Similar to {@link YamlPropertiesFactoryBean} but retains the order of entries.
     */
    private static class Processor extends YamlProcessor {

        Processor(Resource resource, String profile) {
            if (profile == null) {
                setMatchDefault(true);
                setDocumentMatchers(new SpringProfileDocumentMatcher());
            } else {
                setMatchDefault(false);
                setDocumentMatchers(new SpringProfileDocumentMatcher(profile));
            }
            setResources(resource);
        }

        @Override
        protected Yaml createYaml() {
            return new Yaml(new StrictMapAppenderConstructor(), new Representer(),
                    new DumperOptions(), new Resolver() {
                @Override
                public void addImplicitResolver(Tag tag, Pattern regexp,
                                                String first) {
                    if (tag == Tag.TIMESTAMP) {
                        return;
                    }
                    super.addImplicitResolver(tag, regexp, first);
                }
            });
        }

        public Map<String, Object> process() {
            final Map<String, Object> result = new LinkedHashMap<String, Object>();
            process((properties, map) ->
                    result.putAll(getFlattenedMap(map))
            );
            return result;
        }
    }
}
