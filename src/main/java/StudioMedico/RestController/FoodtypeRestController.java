/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.FoodTypeNotFoundException;
import StudioMedico.Model.TypeFood;
import StudioMedico.Repository.TypeFoodRepository;
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
@RequestMapping("/food")
public class FoodtypeRestController {

    @Autowired
    TypeFoodRepository foodRepository;

    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(foodRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) { 
        
        TypeFood food = foodRepository.findById(id)
                .orElseThrow(() -> new FoodTypeNotFoundException("TypeFood not found for :" + id));
        return ResponseEntity.ok().body(food);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody TypeFood food) {
        if (foodRepository.existsById(id)) {
            foodRepository.save(food);
            return ResponseEntity.ok().body(food);
        }
        return new ResponseEntity<>("TypeFood not found for :" + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TypeFood> post(@RequestBody TypeFood food) {
        foodRepository.save(food);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("TypeFood not found for :" + id, HttpStatus.NOT_FOUND);
    }

}
