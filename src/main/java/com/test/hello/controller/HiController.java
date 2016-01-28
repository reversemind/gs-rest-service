package com.test.hello.controller;

import com.test.hello.domain.Hi;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/hi")
    public Hi greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Hi(counter.incrementAndGet(),
                String.format(template, name));
    }
}