package vut.fit.iss.controller.user.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vut.fit.iss.config.Constants;
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
    public ResponseEntity<Void> createStaff(@Valid @RequestBody Staff staff, UriComponentsBuilder ucBuilder) {

        if (!service.isStaffExist(staff)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Staff entity = persistEntity(staff);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/staff/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    private Staff persistEntity(Staff staff) {
        Staff entity = null;

        switch (staff.getRole()) {
            case ADMIN:
                entity = administratorService.persist((Administrator) staff);
                break;
            case DOCTOR:
                entity = doctorService.persist((Doctor) staff);
                break;
            case NURSE:
                entity = nurseService.persist((Nurse) staff);
                break;
        }
        return entity;
    }


    //------------------- Update a Staff --------------------------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") long id, @Valid @RequestBody Staff staff) {
        System.out.println("Updating User " + id);


        if (!service.isStaffExist(staff)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Staff currentStaff = persistEntity(staff);
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
