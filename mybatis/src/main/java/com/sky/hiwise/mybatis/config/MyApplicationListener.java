package com.sky.hiwise.mybatis.config;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.env.*;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.*;


public class MyApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {

    public static final String BOOTSTRAP_PROPERTY_SOURCE_NAME = "bootstrap";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        // don't listen to events in a bootstrap context
        if (environment.getPropertySources().contains(BOOTSTRAP_PROPERTY_SOURCE_NAME)) {
            return;
        }
        String configName = BOOTSTRAP_PROPERTY_SOURCE_NAME;
        StandardEnvironment bootstrapEnvironment = new StandardEnvironment();
        MutablePropertySources bootstrapProperties = bootstrapEnvironment
                .getPropertySources();
        for (PropertySource<?> source : bootstrapProperties) {
            bootstrapProperties.remove(source.getName());
        }
        Map<String, Object> bootstrapMap = new HashMap<>();
        bootstrapMap.put("spring.config.name", configName);

        bootstrapProperties.addFirst(
                new MapPropertySource(BOOTSTRAP_PROPERTY_SOURCE_NAME, bootstrapMap));
        for (PropertySource<?> source : environment.getPropertySources()) {
            bootstrapProperties.addLast(source);
        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // Use names and ensure unique to protect against duplicates
        List<String> names = SpringFactoriesLoader
                .loadFactoryNames(BootstrapConfiguration.class, classLoader);
        for (String name : StringUtils.commaDelimitedListToStringArray(
                environment.getProperty("spring.cloud.bootstrap.sources", ""))) {
            names.add(name);
        }
        // TODO: is it possible or sensible to share a ResourceLoader?
        SpringApplicationBuilder builder = new SpringApplicationBuilder()
                .profiles(environment.getActiveProfiles()).bannerMode(Banner.Mode.OFF)
                .environment(bootstrapEnvironment)
                .properties("spring.application.name:" + configName)
                .registerShutdownHook(false).logStartupInfo(false).web(WebApplicationType.NONE);
        if (environment.getPropertySources().contains("refreshArgs")) {
            // If we are doing a context refresh, really we only want to refresh the
            // Environment, and there are some toxic listeners (like the
            // LoggingApplicationListener) that affect global static state, so we need a
            // way to switch those off.
            builder.application()
                    .setListeners(filterListeners(builder.application().getListeners()));
        }
        List<Class<?>> sources = new ArrayList<>();
        for (String name : names) {
            Class<?> cls = ClassUtils.resolveClassName(name, null);
            try {
                cls.getDeclaredAnnotations();
            }
            catch (Exception e) {
                continue;
            }
            sources.add(cls);
        }
        AnnotationAwareOrderComparator.sort(sources);
        builder.sources(sources.toArray(new Class[sources.size()]));
        builder.run();
    }

    private Collection<? extends ApplicationListener<?>> filterListeners(
            Set<ApplicationListener<?>> listeners) {
        Set<ApplicationListener<?>> result = new LinkedHashSet<>();
        for (ApplicationListener<?> listener : listeners) {
            result.add(listener);
        }
        return result;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 5;
    }
}
