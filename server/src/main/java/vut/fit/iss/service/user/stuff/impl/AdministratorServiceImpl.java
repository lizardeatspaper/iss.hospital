package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.domain.user.staff.Administrator;
import vut.fit.iss.repository.user.stuff.AdministratorRepository;
import vut.fit.iss.service.user.account.AccountService;
import vut.fit.iss.service.user.stuff.AdministratorService;

import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository repository;
    private final AccountService accountService;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public Optional<Administrator> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Administrator persist(Administrator administrator) {
        if (administrator.getAccount().getId() == null) {
            administrator.setAccount(accountService.persist(administrator.getAccount()));
        }
        return repository.save(administrator);
    }

    @Override
    public Administrator create(StaffDTO dto) {
        Account account = accountService.getOrCreate(dto.getUsername(), dto.getPassword());
        return new Administrator(dto.getFirstName(),
                dto.getLastName(), dto.getBirthdate(),
                dto.getTelephone(), dto.getAddress(),
                dto.getRole(), account);
    }
}

