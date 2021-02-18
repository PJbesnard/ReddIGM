package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteCommentService {

    @Autowired
    private VoteCommentRepository repository;

    public VoteComment save(VoteComment vote) {
        return repository.save(vote);
    }

    public Optional<VoteComment> getById(Long id) {
        return repository.findById(id);
    }

    public List<VoteComment> getAll() {
        List<VoteComment> votes = (List<VoteComment>) repository.findAll();
        return votes;
    }
}
