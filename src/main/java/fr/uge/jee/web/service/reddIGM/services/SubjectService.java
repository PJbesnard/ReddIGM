package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.Subject;
import fr.uge.jee.web.service.reddIGM.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public Subject save(Subject subject) {
        return repository.save(subject);
    }

    public Optional<Subject> getById(Long id) {
        return repository.findById(id);
    }

    public List<Subject> getAll() {
        List<Subject> subjects = (List<Subject>) repository.findAll();
        return subjects;
    }
}
