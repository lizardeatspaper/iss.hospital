package vut.fit.iss.controller.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.dto.MedicalHistoryDTO;
import vut.fit.iss.domain.other.MedicalHistory;
import vut.fit.iss.service.other.MedicalHistoryService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class MedicalHistoryResource {
    private final MedicalHistoryService service;

    @Autowired
    public MedicalHistoryResource(MedicalHistoryService service) {
        this.service = service;
    }

    @RequestMapping("/history/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        Optional<MedicalHistory> medicalHistory = service.getById(id);

        if (medicalHistory.isPresent()) {
            return new ResponseEntity<>(medicalHistory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/history/patient/{id}")
    public ResponseEntity<Collection<MedicalHistory>> getMedicalHistoryByPatientId(@PathVariable Long id) {
        Collection<MedicalHistory> medicalHistory = service.getByPatientId(id);

        if (medicalHistory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);

    }

    //-------------------Create History--------------------------------------------------------
    @RequestMapping(value = "/history/", method = RequestMethod.POST)
    public ResponseEntity<Void> createHistory(@RequestBody MedicalHistoryDTO historyDTO, UriComponentsBuilder ucBuilder) {

        MedicalHistory entity = service.create(historyDTO);
        entity = service.persist(entity);

        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/history/{id}").buildAndExpand(entity.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Delete a Staff --------------------------------------------------------

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteHistory(@PathVariable("id") long id) {

        Optional<MedicalHistory> history = service.getById(id);
        if (!history.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.delete(history.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
