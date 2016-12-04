package vut.fit.iss.controller.user.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.dto.StaffDTO;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.domain.user.staff.Administrator;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.domain.user.staff.Nurse;
import vut.fit.iss.domain.user.staff.Staff;
import vut.fit.iss.service.user.stuff.AdministratorService;
import vut.fit.iss.service.user.stuff.DoctorService;
import vut.fit.iss.service.user.stuff.NurseService;
import vut.fit.iss.service.user.stuff.StaffService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class StaffResource {
    private final StaffService service;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final AdministratorService administratorService;

    @Autowired
    public StaffResource(StaffService service, DoctorService doctorService, NurseService nurseService, AdministratorService administratorService) {
        this.service = service;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
        this.administratorService = administratorService;
    }

    //-------------------Retrieve all Staffs-----------------------------------------------------

    @RequestMapping("/staff")
    public ResponseEntity<Collection<Staff>> getAllStaffs() {
        Collection<Staff> staffs = service.getAll();
        if (staffs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    //-------------------Retrieve a Staff--------------------------------------------------------

    @RequestMapping("/staff/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = service.getById(id);
        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //-------------------Create a Staff--------------------------------------------------------

    @RequestMapping(value = "/staff/", method = RequestMethod.POST)
    public ResponseEntity<Void> createStaff(@Valid @RequestBody StaffDTO staff, UriComponentsBuilder ucBuilder) {

        if (!service.isStaffExist(staff.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Staff entity = persistEntity(staff, null);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/staff/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    private Staff persistEntity(StaffDTO staff, Long id) {
        Staff entity = null;
        switch (staff.getRole()) {
            case ADMIN:
                Administrator administrator = administratorService.create(staff);
                administrator.setId(id);
                entity = administratorService.persist(administrator);
                break;
            case DOCTOR:
                Doctor doctor = doctorService.create(staff);
                doctor.setId(id);
                entity = doctorService.persist(doctor);
                break;
            case NURSE:
                Nurse nurse = nurseService.create(staff);
                nurse.setId(id);
                entity = nurseService.persist(nurse);
                break;
        }
        return entity;
    }


    //------------------- Update a Staff --------------------------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") long id, @Valid @RequestBody StaffDTO staff) {

        if (!service.isStaffExist(staff.getUsername())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Staff currentStaff = persistEntity(staff, id);
        if (currentStaff == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(currentStaff, HttpStatus.OK);
    }

    //------------------- Delete a Staff --------------------------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Staff> deleteStaff(@PathVariable("id") long id) {

        Optional<Staff> staff = service.getById(id);
        if (!staff.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (staff.get().getRole() == UserRole.ADMIN) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        deleteEntity(staff.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void deleteEntity(Staff staff) {
        switch (staff.getRole()) {
            case DOCTOR:
                doctorService.delete((Doctor) staff);
                break;
            case NURSE:
                nurseService.delete((Nurse) staff);
                break;
        }
    }
}
