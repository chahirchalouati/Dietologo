/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.ElementNotFoundException;
import StudioMedico.Model.Element;
import StudioMedico.Repository.ElementRepository;
import javax.validation.Valid;
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
@RequestMapping("/element")
public class ElementRestController {

    @Autowired
    ElementRepository elementRepository;

    @GetMapping()
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(elementRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Element e = elementRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException("Element Not Found :" + id));
        return new ResponseEntity<>(e, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Element element) {
        if (elementRepository.existsById(id)) {
            elementRepository.save(element);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Element Not Found :" + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid Element element) {
        elementRepository.save(element);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (elementRepository.existsById(id)) {
            elementRepository.deleteById(id);
            return ResponseEntity.ok().body(id);
        }
        return new ResponseEntity<>("Element Not Found :" + id, HttpStatus.NOT_FOUND);
    }

}
