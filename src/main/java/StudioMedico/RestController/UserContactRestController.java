package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.ContactNotFoundException;
import StudioMedico.Model.UserContact;
import StudioMedico.Repository.UserContactRepository;
import StudioMedico.Repository.UserRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contact")
public class UserContactRestController {

    @Autowired
    UserContactRepository userContactRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public ResponseEntity<List<UserContact>> list() {
        return ResponseEntity.ok().body(userContactRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserContact> get(@PathVariable Long id) {
        UserContact userContact = userContactRepository.findById(id).orElseThrow(
                ()-> new ContactNotFoundException("Contact  Not Found :" + id));
        return ResponseEntity.ok().body(userContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody UserContact userContact) {

        if (userContactRepository.existsById(id)) {

            userContact.setIdUserContact(id);
            userContactRepository.save(userContact);
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> postAsAdmin(@RequestBody @Valid UserContact userContact) {
        userContact.setUser(userRepository.getOne(Long.valueOf("1")));
        userContactRepository.save(userContact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userContactRepository.existsById(id)) {
            userContactRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.notFound().build();
    }
}
