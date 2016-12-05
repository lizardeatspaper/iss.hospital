package vut.fit.iss.service.user.account;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.account.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_USER')")
    Optional<Account> getUserByUserName(String userName);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Collection<Account> getAllAccounts();

    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    Account persist(Account account);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_USER')")
    Account getOrCreate(String login, String password);
}
