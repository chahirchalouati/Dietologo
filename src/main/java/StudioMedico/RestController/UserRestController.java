package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.UserNotFoundException;
import StudioMedico.Model.User;
import StudioMedico.Repository.AuthoritieRepository;
import StudioMedico.Repository.UserRepository;
import StudioMedico.Service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritieRepository authoritieRepository;

    @GetMapping()
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not Found :" + id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody User user) {

        if (userRepository.existsById(id)) {
            user.setAuthoritie(authoritieRepository.findByAuthoritie("USER"));
            userRepository.save(user);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("User Not Found :" + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> postAsAdmin(@RequestBody @Valid User user) {
        userService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.notFound().build();
    }
}
