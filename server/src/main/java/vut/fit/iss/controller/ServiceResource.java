package vut.fit.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.service.user.account.AccountService;

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
}
