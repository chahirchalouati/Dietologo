/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.MedicineNotFoundException;
import StudioMedico.Model.Medicine;
import StudioMedico.Repository.MedicineRepository;
import java.util.List;
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
@RequestMapping("/medicine")
public class MedicineRestController {

    @Autowired
    MedicineRepository medicineRepository;

    @GetMapping()
    public ResponseEntity< List<Medicine>> list() {
        return ResponseEntity.ok().body(medicineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> get(@PathVariable Long id) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(()
                -> new MedicineNotFoundException("Medicine  Not Found :" + id));
        return ResponseEntity.ok().body(medicine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Medicine medicine) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.save(medicine);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Medicine  Not Found :" + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Medicine medicine) {
        medicineRepository.save(medicine);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (medicineRepository.existsById(id)) {
            medicineRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Medicine  Not Found :" + id, HttpStatus.NOT_FOUND);
    }

}
