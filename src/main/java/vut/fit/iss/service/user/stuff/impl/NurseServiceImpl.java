package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.domain.user.staff.Nurse;
import vut.fit.iss.repository.user.stuff.NurseRepository;
import vut.fit.iss.service.user.account.AccountService;
import vut.fit.iss.service.user.stuff.NurseService;

import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {
    private final NurseRepository repository;
    private final AccountService accountService;

    @Autowired
    public NurseServiceImpl(NurseRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public Optional<Nurse> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Nurse persist(Nurse nurse) {
        if (nurse.getAccount().getId() == null) {
            nurse.setAccount(accountService.persist(nurse.getAccount()));
        }
        return repository.save(nurse);
    }

    @Override
    public void delete(Nurse nurse) {
        repository.delete(nurse);
    }

    @Override
    public Nurse create(StaffDTO dto) {
        Account account = accountService.getOrCreate(dto.getUsername(), dto.getPassword());
        return new Nurse(dto.getFirstName(),
                dto.getLastName(), dto.getBirthdate(),
                dto.getTelephone(), dto.getAddress(),
                dto.getRole(), account);
    }
}
