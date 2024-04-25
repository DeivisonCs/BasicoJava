import java.util.ArrayList;
import java.util.List;

import exceptions.NotEscalableException;

public class App{
    public static void main(String[] args) throws NotEscalableException {
        EDFScheduler scheduler = new EDFScheduler();

        List<Task> tasks = new ArrayList<>();

        // tasks.add(new Task("T1", 0, 5, 2));
        // tasks.add(new Task("T2", 0, 9, 3));
        // tasks.add(new Task("T3", 1, 4, 2));

        // tasks.add(new Task("T4", 0, 5, 2));
        // tasks.add(new Task("T5", 0, 3, 1));
        // tasks.add(new Task("T6", 0, 4, 1));

        //tasks.add(new Task("T7", 0, 6, 2));
        //tasks.add(new Task("T8", 1, 8, 3));
        //tasks.add(new Task("T9", 3, 5, 1));

        // tasks.add(new Task("A", 0, 5, 3));
        // tasks.add(new Task("B", 2, 4, 2));
        // tasks.add(new Task("C", 4, 6, 5));

         tasks.add(new Task("A", 0, 4, 1));
         tasks.add(new Task("B", 0, 6, 2));
         tasks.add(new Task("C", 2, 10, 3));

        // tasks.add(new Task("A", 0, 5, 1));
        // tasks.add(new Task("B", 0, 7, 2));
        // tasks.add(new Task("C", 4, 12, 3));

         //tasks.add(new Task("A", 0, 6, 2));
         //tasks.add(new Task("B", 0, 9, 3));
         //tasks.add(new Task("C", 5, 15, 4));
        
        List<String> schedule = scheduler.schedule(tasks);
        System.out.println("Schedule:");
        for (String entry : schedule) {
            System.out.println(entry);
            
            // Adicionando um atraso de 1 segundo
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
    }
}