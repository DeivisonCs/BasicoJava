package learning.test.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import learning.test.app.models.Calculator;
import learning.test.app.models.FinMessage;
import learning.test.app.models.Message;

@Component
public class MyApp implements CommandLineRunner {

    @Autowired
    private Calculator calc;

    @Autowired
    private Message msg;

    @Autowired
    private FinMessage finMsg;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Result: " + calc.sum(3, 2));


        System.out.println("\n\n--------------- Testando a pasta application.properties com o @Values() ---------------\n");
        System.out.println(msg.toString());
        
        System.out.println("\n\n--------------- Testando a pasta application.properties com o acesso exclusivo ---------------\n");
        System.out.println(finMsg.toString());
    }
}
