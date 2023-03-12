package com.pragma.users.infrastructure.output.utils;

import feign.Headers;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.entity.ContentType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//, url = "http://localhost:8081"
@FeignClient(name = "square-service", configuration = SquareFeingClientConfiguration.class)
public interface SquareFeingClient {

    @PostMapping("/square/users/{OwnerId}/{restaurantId}/{employeeId}")
    Boolean createEmployee(@PathVariable("OwnerId") Long OwnerId, @PathVariable("restaurantId") Long restaurantId, @PathVariable("employeeId") Long employeeId);


    }

