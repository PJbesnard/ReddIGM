package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import fr.uge.jee.web.service.reddIGM.models.VotePost;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.VotePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotePostService {

    @Autowired
    private VotePostRepository repository;

    public VotePost save(VotePost vote) {
        return repository.save(vote);
    }

    public Optional<VotePost> getById(Long id) {
        return repository.findById(id);
    }

    public List<VotePost> getAll() {
        List<VotePost> votes = (List<VotePost>) repository.findAll();
        return votes;
    }
}
