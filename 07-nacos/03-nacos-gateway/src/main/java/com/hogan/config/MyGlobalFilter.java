package com.hogan.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 这个就是过滤的方法
     * 过滤器链模式
     * 责任链模式
     * 网关里面有使用  mybatis的 二级缓存有变种责任链模式
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        System.out.println(path);
        HttpHeaders headers = request.getHeaders();
        System.out.println(headers);
        String methodName = request.getMethod().name();
        System.out.println(methodName);
        String ip = request.getHeaders().getHost().getHostString();
        System.out.println(ip);

        /*ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg", "授权失败");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = new byte[0];
        try {
            bytes = objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));*/


        // 放行到下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 指定顺序的方法
     * 越小越先执行
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
