package vut.fit.iss.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentController {
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @RequestMapping("/departments")
    public Collection<Department> getAllDepartments() {
        return service.getAllDepartments();
    }
    @RequestMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = service.getDepartmentById(id);

        if(department.isPresent()) {
            return  department.get();
        }
        return null;
    }
}
