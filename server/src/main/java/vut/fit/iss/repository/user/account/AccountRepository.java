package vut.fit.iss.repository.user.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vut.fit.iss.domain.user.account.Account;

import java.util.Optional;

/**
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
}
