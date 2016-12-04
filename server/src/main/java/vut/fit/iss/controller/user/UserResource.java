package vut.fit.iss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.service.user.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class UserResource {

    private final UserService service;

    @Autowired
    public UserResource(UserService service) {
        this.service = service;
    }

    //-------------------Retrieve a current User-------------------------------------------------
    @RequestMapping("/user")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String name = principal.getName();
        Optional<User> user = service.getByUserName(name);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return null;
    }

    //-------------------Retrieve a User-------------------------------------------------
    @RequestMapping("/user/{id}")
    public ResponseEntity<User> getStaffById(@PathVariable Long id) {
        Optional<User> user = service.getById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
