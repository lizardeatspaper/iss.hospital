package vut.fit.iss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vut.fit.iss.config.Constants;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.service.user.UserService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(Constants.BASE_URL)
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
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

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
