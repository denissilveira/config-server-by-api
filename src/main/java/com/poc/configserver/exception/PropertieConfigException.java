package com.poc.configserver.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class PropertieConfigException extends AbstractThrowableProblem {

    public PropertieConfigException() {
        super(URI.create("/api/v1/config-server"), "Config Server Error", Status.BAD_REQUEST);
    }
}
