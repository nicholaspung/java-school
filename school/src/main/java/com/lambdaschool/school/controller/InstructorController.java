package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @PostMapping(value = "/instructor")
    public ResponseEntity<?> createInstructor(@Valid @RequestBody Instructor newInstructor) throws URISyntaxException {
        newInstructor = instructorService.save(newInstructor);

        return new ResponseEntity<>(null, null, HttpStatus.CREATED);
    }

    @PutMapping(value = "/instructor/{instructorid}")
    public ResponseEntity<?> updateInstructor(@Valid @RequestBody Instructor updateInstructor, @PathVariable long instructorid) throws URISyntaxException {
        instructorService.update(updateInstructor, instructorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteInstructor(@PathVariable long instructorid) {
        instructorService.delete(instructorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
