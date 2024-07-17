package learn.learning_jpa.MyApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import learn.learning_jpa.models.User;
import learn.learning_jpa.repository.UserRep;

@Component
public class App implements CommandLineRunner {
    @Autowired
    private UserRep userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();

        user.setName("Test 2");
        user.setPassword("NewPass");

        userRepository.save(user);

        for(User u : userRepository.findAll()){
            System.out.println(u);
        }
    }
    
}
