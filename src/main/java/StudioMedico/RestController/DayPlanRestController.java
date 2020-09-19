/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudioMedico.RestController;

import StudioMedico.Exception.Exceptions.DayPlanNotFoundException;
import StudioMedico.Model.DayPlan;
import StudioMedico.Repository.DayPlanRepository;
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
@RequestMapping("/dayDiet")
public class DayPlanRestController {

    @Autowired
    DayPlanRepository dayPlanRepository;

    @GetMapping()
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(dayPlanRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        DayPlan dayPlan = dayPlanRepository.findById(id).orElseThrow(
                () -> new DayPlanNotFoundException(" Plan For Day Not Found " + id));
        return new ResponseEntity<>(dayPlan, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody DayPlan dayPlan) {
        if (dayPlanRepository.existsById(id)) {
            dayPlanRepository.save(dayPlan);
            return new ResponseEntity<>(dayPlan, HttpStatus.OK);
        }
        return new ResponseEntity<>(" Plan For Day Not Found " + id, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody DayPlan dayPlan) {
        dayPlanRepository.save(dayPlan);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (dayPlanRepository.existsById(id)) {
            dayPlanRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(" Plan For Day Not Found " + id, HttpStatus.NOT_FOUND);
    }

}
