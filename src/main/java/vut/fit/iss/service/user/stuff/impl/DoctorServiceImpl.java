package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.other.Department;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.repository.user.stuff.DoctorRepository;
import vut.fit.iss.service.other.DepartmentService;
import vut.fit.iss.service.user.account.AccountService;
import vut.fit.iss.service.user.stuff.DoctorService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;
    private final AccountService accountService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository repository, AccountService accountService, DepartmentService departmentService) {
        this.repository = repository;
        this.accountService = accountService;
        this.departmentService = departmentService;
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Doctor persist(Doctor doctor) {
        if(doctor.getAccount().getId() == null) {
            doctor.setAccount(accountService.persist(doctor.getAccount()));
        }
        return repository.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        repository.delete(doctor);
    }

    @Override
    public Doctor create(StaffDTO dto) {
        Account account = accountService.getOrCreate(dto.getUsername(), dto.getPassword());
        Optional<Department> departmentOptional = departmentService.getDepartmentById(dto.getDepartmentId());
        if (!departmentOptional.isPresent()) {
            throw new EntityNotFoundException("Department with id: " + dto.getDepartmentId() + "does not exist");
        }
        return new Doctor(dto.getFirstName(),
                dto.getLastName(), dto.getBirthdate(),
                dto.getTelephone(), dto.getAddress(),
                dto.getRole(), account, departmentOptional.get());
    }
}
