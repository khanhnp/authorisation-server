package com.spsvn.authorisation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by npkhanh on 6/12/2018.
 */
@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String greeting() {
        return "Hello world";
    }
}
