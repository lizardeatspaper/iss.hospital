package vut.fit.iss.controller.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.service.user.account.AccountService;

import java.util.Collection;

@RestController
@RequestMapping(Constants.BASE_URL)
public class AccountResource {

    private AccountService service;

    @Autowired
    public AccountResource(AccountService service) {
        this.service = service;
    }

    @RequestMapping("/account")
    public ResponseEntity<Collection<Account>> getUsers() {
        Collection<Account> accounts = service.getAllAccounts();
        if (accounts.isEmpty()) {
            return new ResponseEntity<Collection<Account>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Account>>(accounts, HttpStatus.OK);
    }
}
