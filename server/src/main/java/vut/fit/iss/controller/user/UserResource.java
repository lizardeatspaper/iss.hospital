package vut.fit.iss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/user")
    public User getCurrentUser(Principal principal) {
        String name = principal.getName();
        Optional<User> user = service.getByUserName(name);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
