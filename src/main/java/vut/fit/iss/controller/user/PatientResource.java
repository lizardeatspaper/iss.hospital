package vut.fit.iss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.dto.PatientDTO;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.service.user.PatientService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class PatientResource {
    private final PatientService service;

    @Autowired
    public PatientResource(PatientService service) {
        this.service = service;
    }

    //-------------------Retrieve all Patients-----------------------------------------------------

    @RequestMapping("/patient")
    public ResponseEntity<Collection<Patient>> getAllPatients() {
        Collection<Patient> patients = service.getAll();
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    //-------------------Retrieve a Patient--------------------------------------------------------

    @RequestMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = service.getById(id);
        if (patient.isPresent()) {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //-------------------Create a Staff--------------------------------------------------------
    @RequestMapping(value = "/patient/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPatient(@Valid @RequestBody PatientDTO patient, UriComponentsBuilder ucBuilder) {

        if (service.isPatientExist(patient.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        Patient entity = service.create(patient);
        entity = service.persist(entity);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Staff --------------------------------------------------------
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @Valid @RequestBody Patient patient) {

        if (!service.isPatientExist(patient.getAccount().getUserName())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Patient currentPatient = service.persist(patient);
        if (currentPatient == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(currentPatient, HttpStatus.OK);
    }

    //------------------- Delete a Staff --------------------------------------------------------

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") long id) {

        Optional<Patient> patient = service.getById(id);
        if (!patient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(patient.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
