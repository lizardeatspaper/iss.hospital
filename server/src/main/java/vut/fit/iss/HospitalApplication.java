package vut.fit.iss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import vut.fit.iss.domain.other.Department;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.PatientStatus;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.domain.user.staff.Administrator;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.domain.user.staff.Nurse;
import vut.fit.iss.service.other.DepartmentService;
import vut.fit.iss.service.user.UserService;
import vut.fit.iss.service.user.account.AccountService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Configuration
@SpringBootApplication
public class HospitalApplication {


    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    FilterRegistrationBean corsFilter(
            @Value("${tagit.origin:http://localhost:9000}") String origin) {
        return new FilterRegistrationBean(new Filter() {
            public void doFilter(ServletRequest req, ServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String method = request.getMethod();
                // this origin value could just as easily have come from a database
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods",
                        "POST,GET,OPTIONS,DELETE");
                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader(
                        "Access-Control-Allow-Headers",
                        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
                if ("OPTIONS".equals(method)) {
                    response.setStatus(HttpStatus.OK.value());
                } else {
                    chain.doFilter(req, res);
                }
            }

            public void init(FilterConfig filterConfig) {
            }

            public void destroy() {
            }
        });
    }

    @Bean
    CommandLineRunner init(final UserService userService, final AccountService accountService, final DepartmentService departmentService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                userService.persist(new Administrator("Vasya", "Ivan", new Date(), "1654846456", "fdsfdsfds5f5ds4f54ds65f4ds654f6sd", UserRole.ADMIN, accountService.persist(new Account("admin", "admin"))));
                userService.persist(new Doctor("Petya", "Ivan", new Date(), "4324324", "sdfgsdfgsdfgsdfgdf", UserRole.DOCTOR, accountService.persist(new Account("doctor", "doctor")), departmentService.persist(new Department("ABC"))));
                userService.persist(new Nurse("Masha", "Ivan", new Date(), "432434324", "gfdgdsfgsdfgsfdg", UserRole.NURSE, accountService.persist(new Account("nurse", "nurse"))));
                userService.persist(new Patient("Dasha", "Ivan", new Date(), "5465464564", "gfdgfdgsdfgdfgfd", UserRole.PATIENT, accountService.persist(new Account("patient", "patient")), PatientStatus.HEALTHY, "asdasdsd"));
            }

        };

    }
}



