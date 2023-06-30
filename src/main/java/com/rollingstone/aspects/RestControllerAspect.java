package com.rollingstone.aspects;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.Map;

@Aspect
@Component
public class RestControllerAspect {

    private final Logger logger = LoggerFactory.getLogger("RestControllerAspect");

    Counter customerCreatedCounter = Metrics.counter("com.rollingstone.customer.created");

    @Before("execution(public * com.rollingstone.controller.*Controller.*(..))")
    public void generalAllMethodASpect() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logger.info("All Method Calls invoke this general aspect method");
        logger.info("Request Method : " + request.getMethod());
        logger.info("Request Path : " + request.getPathInfo());
        logger.info("Request URI : " + request.getRequestURI());
        Enumeration<String> headers = request.getHeaderNames();

        logger.info("Request Headers : ");
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            logger.info("Request Header name : "+ header + " Request Header Value :" + request.getHeader(header));
        }
    }

    @AfterReturning("execution(public * com.rollingstone.controller.*Controller.createCustomer(..))")
    public void getsCalledOnCustomerChange() {
        logger.info("This aspect is fired when the createCustomer method of the controller is called");
        customerCreatedCounter.increment();
    }
}
