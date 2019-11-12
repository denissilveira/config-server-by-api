package com.poc.configserver.web.rest;

import com.poc.configserver.exception.BadRequestAlertException;
import com.poc.configserver.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/properties-config")
public class PropertiesController {

     @Autowired
     private PropertiesService propertiesService;

    @PostMapping("/{label}/{application}-{profile}")
    public ResponseEntity<Void> save(@PathVariable("profile") final String profile,
                                     @PathVariable("label") final String label,
                                     @PathVariable("application") final String application,
                                     @RequestBody final Object properties) throws BadRequestAlertException {
        propertiesService.save(profile, label, application, properties);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
