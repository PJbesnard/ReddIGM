package fr.uge.jee.web.service.reddIGM.controllers;

import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/subjects/")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        return ResponseEntity.of(subjectService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAll());
    }
}
