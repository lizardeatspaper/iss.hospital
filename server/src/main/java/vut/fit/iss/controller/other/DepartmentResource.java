package vut.fit.iss.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
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

    @RequestMapping("/department")
    public ResponseEntity<Collection<Department>> getAllDepartments() {
        Collection<Department> departments = service.getAllDepartments();
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @RequestMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = service.getDepartmentById(id);

        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
