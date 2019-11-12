package com.poc.configserver.web.rest;

import com.poc.configserver.exception.BadRequestAlertException;
import com.poc.configserver.exception.PropertieConfigException;
import com.poc.configserver.service.PropertiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/properties-config")
@Api(value = "PropertiesController")
public class PropertiesController {

     @Autowired
     private PropertiesService propertiesService;

    @ApiOperation(value = "save a new configuration")
    @PostMapping("/{label}/{application}-{profile}")
    public ResponseEntity<Void> save(@PathVariable("profile") final String profile,
                                     @PathVariable("label") final String label,
                                     @PathVariable("application") final String application,
                                     @RequestBody final Object properties) throws BadRequestAlertException {
        propertiesService.save(profile, label, application, properties);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "find configurations by application name")
    @GetMapping("/{label}/{application}-{profile}")
    public ResponseEntity<LinkedHashMap> findConfigurations(@PathVariable("profile") final String profile,
                                                     @PathVariable("label") final String label,
                                                     @PathVariable("application") final String application) throws IOException {
        final LinkedHashMap configurations = propertiesService.findConfigurations(profile, label, application);
        if (CollectionUtils.isEmpty(configurations)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(configurations, HttpStatus.OK);
    }

    @ApiOperation(value = "list all names of the applications configured")
    @GetMapping
    public ResponseEntity<List<String>> findAllApplications() {

        List<String> names = propertiesService.findAllApplications();

        if (CollectionUtils.isEmpty(names)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @ApiOperation(value = "update a configuration")
    @PatchMapping("/{label}/{application}-{profile}")
    public ResponseEntity<Void> update(@PathVariable("profile") final String profile,
                                       @PathVariable("label") final String label,
                                       @PathVariable("application") final String application,
                                       @RequestBody final Object properties) throws PropertieConfigException, IOException {
        try {
            propertiesService.update(profile, label, application, properties);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PropertieConfigException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
