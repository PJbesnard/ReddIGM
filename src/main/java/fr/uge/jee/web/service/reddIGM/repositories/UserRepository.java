package fr.uge.jee.web.service.reddIGM.repositories;

import fr.uge.jee.web.service.reddIGM.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("select user from Users where username=:name")
    User findByUsername(@Param("name") String username);
}
