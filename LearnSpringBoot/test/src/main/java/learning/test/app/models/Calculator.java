package learning.test.app.models;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int sum (int num1, int num2){
        return num1 + num2;
    } 
    
}