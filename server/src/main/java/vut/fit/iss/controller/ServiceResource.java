package vut.fit.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.service.user.account.AccountService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ServiceResource {

    private final AccountService service;

    @Autowired
    public ServiceResource(AccountService service) {
        this.service = service;
    }

    @RequestMapping
    public String index() {
        return "Home Page";
    }

    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public void corsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }

}
