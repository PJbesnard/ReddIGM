package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.Post;
import fr.uge.jee.web.service.reddIGM.models.VotePost;
import fr.uge.jee.web.service.reddIGM.repositories.VotePostRepository;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
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
        return (List<VotePost>) repository.findAll();
    }

    public List<VotePost> getAllByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<VotePost> getAllByUserIdOrdered(long userId, OrderType order) {
        if (order == OrderType.ASCENDING) {
            return repository.findAllByUserIdOrderByCreationDateAsc(userId);
        } else {
            return repository.findAllByUserIdOrderByCreationDateDesc(userId);
        }
    }
}
