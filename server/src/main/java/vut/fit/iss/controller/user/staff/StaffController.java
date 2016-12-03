package vut.fit.iss.controller.user.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.user.staff.Staff;
import vut.fit.iss.service.user.stuff.AdministratorService;
import vut.fit.iss.service.user.stuff.DoctorService;
import vut.fit.iss.service.user.stuff.NurseService;
import vut.fit.iss.service.user.stuff.StaffService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class StaffController {
    private final StaffService service;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final AdministratorService administratorService;

    @Autowired
    public StaffController(StaffService service, DoctorService doctorService, NurseService nurseService, AdministratorService administratorService) {
        this.service = service;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
        this.administratorService = administratorService;
    }

    @RequestMapping("/staff")
    public ResponseEntity<Collection<Staff>> getAllStaffs() {
        Collection<Staff> staffs = service.getAll();
        if(staffs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Staff>>(staffs, HttpStatus.OK);
    }

    @RequestMapping("/staff/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = service.getById(id);
        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
