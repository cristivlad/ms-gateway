package com.example.msgateway.filters;

import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class ResponseTraceFilter {

    public static final Logger log = getLogger(ResponseTraceFilter.class);

    private final FilterUtility filterUtility;

    public ResponseTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }

    @Bean
    public GlobalFilter postFilter() {
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            String correlationId = filterUtility.getCorrelationId(exchange.getRequest().getHeaders());
            log.debug("tmx-correlation-id in response filter: {}.", correlationId);
            exchange.getResponse().getHeaders().add(FilterUtility.CORRELATION_ID, correlationId);
        }));
    }
}
