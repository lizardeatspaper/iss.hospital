package vut.fit.iss.service.other.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.DepartmentDTO;
import vut.fit.iss.domain.other.Department;
import vut.fit.iss.repository.other.DepartmentRepository;
import vut.fit.iss.service.other.DepartmentService;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department persist(Department department) {
        return repository.save(department);
    }

    @Override
    public boolean isDepartmentExist(String name) {
        return repository.findByName(name).isPresent();
    }

    @Override
    public Department create(DepartmentDTO dto) {
        return new Department(dto.getName());
    }

    @Override
    public void delete(Department department) {
        repository.delete(department);
    }
}
