package learning.test.app.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Message {
    @Value("${userName:NotFound}")
    private String name;
    @Value("${email}")
    private String email;
    @Value("${phoneNumber}")
    private String phoneNumber;

    @Override
    public String toString() {
        return "Name: " + name +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber;
    }
}
