package com.example.msgateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "tmx-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders != null && requestHeaders.get(CORRELATION_ID) != null) {
            List<String> s = requestHeaders.get(CORRELATION_ID);
            return s.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String headerName, String headerValue) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(headerName, headerValue).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}
