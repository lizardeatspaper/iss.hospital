package vut.fit.iss;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vut.fit.iss.domain.other.Department;
import vut.fit.iss.domain.other.MedicalHistory;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.PatientStatus;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.domain.user.staff.Administrator;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.domain.user.staff.Nurse;
import vut.fit.iss.repository.other.DepartmentRepository;
import vut.fit.iss.repository.other.MedicalHistoryRepository;
import vut.fit.iss.repository.user.PatientRepository;
import vut.fit.iss.repository.user.account.AccountRepository;
import vut.fit.iss.repository.user.stuff.AdministratorRepository;
import vut.fit.iss.repository.user.stuff.DoctorRepository;
import vut.fit.iss.repository.user.stuff.NurseRepository;

import java.util.Date;

@Configuration
@SpringBootApplication
public class HospitalApplication {


    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository,
                           final DepartmentRepository departmentRepository,
                           final MedicalHistoryRepository medicalHistoryRepository,
                           final DoctorRepository doctorRepository, final NurseRepository nurseRepository,
                           final PatientRepository patientRepository, AdministratorRepository administratorRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {

                try {
                    departmentRepository.save(new Department("ASD"));
                    departmentRepository.save(new Department("FDA"));
                    administratorRepository.save(new Administrator("Vasya", "Ivan", new Date(), "1654846456", "fdsfdsfds5f5ds4f54ds65f4ds654f6sd", UserRole.ADMIN, accountRepository.save(new Account("admin", "admin"))));
                    Doctor doctor = doctorRepository.save(new Doctor("Petya", "Ivan", new Date(), "4324324", "sdfgsdfgsdfgsdfgdf", UserRole.DOCTOR, accountRepository.save(new Account("doctor", "doctor")), departmentRepository.save(new Department("ABC"))));
                    nurseRepository.save(new Nurse("Masha", "Ivan", new Date(), "432434324", "gfdgdsfgsdfgsfdg", UserRole.NURSE, accountRepository.save(new Account("nurse", "nurse"))));
                    Patient patient = patientRepository.save(new Patient("Dasha", "Ivan", new Date(), "5465464564", "gfdgfdgsdfgdfgfd", UserRole.PATIENT, accountRepository.save(new Account("patient", "patient")), PatientStatus.HEALTHY, "asdasdsd"));

                    medicalHistoryRepository.save(new MedicalHistory(patient, doctor, "фывывфы", "ыфвывфы"));
                    medicalHistoryRepository.save(new MedicalHistory(patient, doctor, "231231", "ewqewqeq"));
                    medicalHistoryRepository.save(new MedicalHistory(patient, doctor, "dsaddas", "r34324"));
                } catch (Exception e) {
                    
                }


            }

        };

    }
}


