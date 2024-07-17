package learn.learning_jpa.repository;

import learn.learning_jpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Integer>{

    
}
 