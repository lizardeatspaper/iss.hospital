package vut.fit.iss.controller.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.service.user.account.AccountService;

import java.util.Collection;

@RestController
@RequestMapping(Constants.BASE_URL)
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping("/accounts")
    public Collection<Account> getUsers() {
        return service.getAllAccounts();
    }
}
