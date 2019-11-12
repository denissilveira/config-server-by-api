package com.poc.configserver.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("config-server")
public interface CloudConfigClient {

    @RequestMapping(value = "/config/{label}/{application}-{profile}.json", method = RequestMethod.GET)
    String getConfiguration(@PathVariable(value = "label") String label,
                            @PathVariable(value = "application") String application,
                            @PathVariable(value = "profile") String profile);
}
