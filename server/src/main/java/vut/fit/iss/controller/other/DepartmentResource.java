package vut.fit.iss.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.dto.DepartmentDTO;
import vut.fit.iss.domain.other.Department;
import vut.fit.iss.service.other.DepartmentService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class DepartmentResource {
    private final DepartmentService service;

    @Autowired
    public DepartmentResource(DepartmentService service) {
        this.service = service;
    }

    //-------------------Retrieve all Departments-------------------------------------------------
    @RequestMapping("/department")
    public ResponseEntity<Collection<Department>> getAllDepartments() {
        Collection<Department> departments = service.getAllDepartments();
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //-------------------Retrieve a Department--------------------------------------------------------
    @RequestMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = service.getDepartmentById(id);

        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //-------------------Create a Department--------------------------------------------------------

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentDTO departmentDTO, UriComponentsBuilder ucBuilder) {

        if (!service.isDepartmentExist(departmentDTO.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Department entity = service.create(departmentDTO);
        entity = service.persist(entity);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/department/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Department --------------------------------------------------------

    @RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
        if (!service.isDepartmentExist(department.getName())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Department currentDepartment = service.persist(department);
        if (currentDepartment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(currentDepartment, HttpStatus.OK);
    }

    //------------------- Delete a Department --------------------------------------------------------

    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id) {

        Optional<Department> department = service.getDepartmentById(id);
        if (!department.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(department.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
