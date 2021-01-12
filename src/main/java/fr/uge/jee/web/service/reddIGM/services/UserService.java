package fr.uge.jee.web.service.reddIGM.services;

import fr.uge.jee.web.service.reddIGM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


}
