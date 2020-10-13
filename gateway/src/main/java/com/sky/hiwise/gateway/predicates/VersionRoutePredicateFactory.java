package com.sky.hiwise.gateway.predicates;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class VersionRoutePredicateFactory extends AbstractRoutePredicateFactory<VersionRoutePredicateFactory.Config> {

    public VersionRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String version = serverWebExchange.getRequest().getQueryParams().getFirst("version");
                if (StringUtils.isNotEmpty(version)) {
                    return StringUtils.compare(version, config.getVersion()) >= 0;
                }
                return false;
            }

            @Override
            public String toString() {
                return String.format("Version: %s", config.getVersion());
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("version");
    }

    @Data
    public static class Config {
        private String version;
    }
}
