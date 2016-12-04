package vut.fit.iss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vut.fit.iss.service.user.account.AccountService;

@Controller
public class ServiceResource {

    private final AccountService service;

    @Autowired
    public ServiceResource(AccountService service) {
        this.service = service;
    }

}
