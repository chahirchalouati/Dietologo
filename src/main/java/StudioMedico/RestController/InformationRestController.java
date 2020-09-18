package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.UserInformationException;
import StudioMedico.Model.UserInformation;
import StudioMedico.Repository.UserInformationRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/information")
public class InformationRestController {

    @Autowired
    UserInformationRepository userInformationRepository;

    @GetMapping()
    public ResponseEntity<List<UserInformation>> list() {
        return ResponseEntity.ok().body(userInformationRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInformation> get(@PathVariable Long id) {
        UserInformation information = userInformationRepository.findById(id)
                .orElseThrow(() -> new UserInformationException("Information not found for :" + id));
        return ResponseEntity.ok().body(information);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody UserInformation information) {
        if (userInformationRepository.existsById(id)) {
            information.setIdUserInformation(id);
            userInformationRepository.save(information);
            return ResponseEntity.ok().body(id);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> postAsAdmin(@RequestBody @Valid UserInformation information) {
        userInformationRepository.save(information);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (userInformationRepository.existsById(id)) {
            userInformationRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return ResponseEntity.notFound().build();
    }
}
