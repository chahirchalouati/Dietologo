/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.BloodTypeNotFoundException;
import StudioMedico.Model.BloodType;
import StudioMedico.Repository.BloodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/blood")
public class BloodTypeRestController {

    @Autowired
    BloodTypeRepository bloodTypeRepository;

    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(bloodTypeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        if (bloodTypeRepository.existsById(id)) {
            BloodType bloodType = bloodTypeRepository.findById(id).orElseThrow(
                    () -> new BloodTypeNotFoundException("Blood Type Not Found " + id));
            return ResponseEntity.ok().body(bloodType);
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody BloodType bloodType) {
        if (bloodTypeRepository.existsById(id)) {
            bloodTypeRepository.save(bloodType);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Blood Type Not Found " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody BloodType bloodType) {
        bloodTypeRepository.save(bloodType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (bloodTypeRepository.existsById(id)) {
            bloodTypeRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Blood Type Not Found " + id, HttpStatus.NOT_FOUND);
    }
}
