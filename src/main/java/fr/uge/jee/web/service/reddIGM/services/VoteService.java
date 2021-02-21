package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.Vote;
import fr.uge.jee.web.service.reddIGM.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository repository;

    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    public Optional<Vote> getById(Long id) {
        return repository.findById(id);
    }

    public List<Vote> getAll() {
        List<Vote> votes = (List<Vote>) repository.findAll();
        return votes;
    }
}
