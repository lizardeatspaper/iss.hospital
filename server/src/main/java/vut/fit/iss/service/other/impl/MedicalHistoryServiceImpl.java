package vut.fit.iss.service.other.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.other.MedicalHistory;
import vut.fit.iss.repository.other.MedicalHistoryRepository;
import vut.fit.iss.service.other.MedicalHistoryService;

import java.util.Collection;
import java.util.Optional;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository repository;

    @Autowired
    public MedicalHistoryServiceImpl(MedicalHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MedicalHistory> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<MedicalHistory> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}
