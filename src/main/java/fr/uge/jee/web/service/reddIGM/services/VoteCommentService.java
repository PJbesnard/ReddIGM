package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import fr.uge.jee.web.service.reddIGM.repositories.VoteCommentRepository;
import fr.uge.jee.web.service.reddIGM.utils.OrderType;
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
        return (List<VoteComment>) repository.findAll();
    }

    public List<VoteComment> getAllByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<VoteComment> getAllByUserIdOrdered(long userId, OrderType order) {
        if (order == OrderType.ASCENDING) {
            return repository.findAllByUserIdOrderByCreationDateAsc(userId);
        } else {
            return repository.findAllByUserIdOrderByCreationDateDesc(userId);
        }
    }
}
