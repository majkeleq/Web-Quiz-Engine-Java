package engine.businesslayer.User;

import engine.persistancelayer.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
public class UserController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserRepository repository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(path = "/api/register")
    public String register(@Valid @RequestBody UserDTO request) {
        User user = convertDTOToUser(request);
        if (repository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken");
        } else {
            repository.save(user);

            return "New user successfully registered";
        }
    }

    User convertDTOToUser(UserDTO dto) {
        var user = new User();
        user.setUsername(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
