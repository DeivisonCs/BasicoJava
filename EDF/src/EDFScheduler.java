import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import enums.TaskStats;

import exceptions.NotEscalableException;

public class EDFScheduler {
    private int currentTime;

    public List<String> schedule(List<Task> tasks) throws NotEscalableException{

        // Calcula se o conjunto de tarefa é escalonável pelo EDF
        if(!isPossible(tasks))
            throw new NotEscalableException();

        int hyperperiod = this.calculateHyperperiod(tasks);
        List<String> eventLog = new ArrayList<>();
        List<Task> readyTasks = new ArrayList<>();
        Task prevTask = null;
        this.currentTime = 0;

        while (currentTime <= hyperperiod) {

            // Seleciona as tarefas com base no tempo de chegada 
            readyTasks = inQueue(tasks, readyTasks); 
            
            // Caso não tenha nenhuma tarefa para executar
            if (readyTasks.isEmpty()) {
                eventLog.add("Time: " + this.currentTime + ", Task: ");
                this.currentTime++;

                continue;
            }
            
            // Busca a tarefa a ser executada seguindo as especificações do EDF
            Task selectedTask = toExecute(readyTasks, currentTime);

            // Verifica se ouve preempção e grava o log
            wasPreempted(prevTask, selectedTask, eventLog);

            selectedTask.remainingTime--;

            // Verifica se a tarefa finalizou
            if (isTaskCompleted(selectedTask)){
                selectedTask.STATS = TaskStats.COMPLETED;
                eventLog.add("\t Task: " + selectedTask.name + ", Stats: " + selectedTask.STATS.getStats());
                selectedTask.periodInitialTime += selectedTask.period;
                selectedTask.remainingTime = selectedTask.executionTime;
                selectedTask.deadline += selectedTask.initialDeadline;
                
            }

            // Guarda qual foi a última tarefa executando
            prevTask = selectedTask;

            // Limpa a lista de prontos
            clearReadyQueue(readyTasks);
                
            this.currentTime++;
        }

        return eventLog;
    }

    private List<Task> inQueue(List<Task> tasks, List<Task> queue) {
        for (Task task : tasks) {
            if (task.periodInitialTime <= this.currentTime) {
                queue.add(task);
                task.STATS = TaskStats.READY;
            }
        }

        return queue;
    }

    private void clearReadyQueue(List<Task> queue){
        queue.clear();
    }

    private Task toExecute(List<Task> readyTasks, int currentTime){
        Task selectedTask = readyTasks.get(0);

        for (Task task : readyTasks) {
            if (task.deadline < selectedTask.deadline && currentTime >= task.periodInitialTime) {
                selectedTask = task;    
            }
        }

        if(selectedTask.executionTime != selectedTask.remainingTime)
            selectedTask.STATS = TaskStats.RUNNING;
        else
            selectedTask.STATS = TaskStats.START_RUNNING;

        return selectedTask;
    }

    private boolean isTaskCompleted(Task selectedTask){
        return selectedTask.remainingTime == 0;
    }

    private void wasPreempted(Task prevTask, Task selectedTask, List<String> eventLog){

        if(prevTask != null && selectedTask != prevTask && prevTask.STATS != TaskStats.COMPLETED){
            prevTask.STATS = TaskStats.PREEMPTED;
            eventLog.add("Time: " + this.currentTime + ", Task: " + prevTask.name + ", Stats: " + prevTask.STATS.getStats() + "\n\t Task: " + selectedTask.name + ", Stats: " + selectedTask.STATS.getStats());
        }else{   
            eventLog.add("Time: " + this.currentTime + ", Task: " + selectedTask.name + ", Stats: " + selectedTask.STATS.getStats());
        }
    }

    private boolean isPossible(List<Task> tasks) {
        double U = 0.0;

        for(Task x: tasks) 
            U += (x.executionTime/(double)x.deadline);
        

        System.out.println("Utilização Total do Processador: " + U);

        if(U > 1) return false;

        return true;
    }       
    // U = (executionTime_1/deadline_1) + (executionTime_2/deadline_2)...


    private int calculateHyperperiod(List <Task> tasks){
        List<BigInteger> deadlineList = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++){
            deadlineList.add(new BigInteger(String.valueOf(tasks.get(i).period)));
        }
        
        BigInteger mmc = calculateMMC(deadlineList);

        return mmc.intValue();
    }

    public BigInteger calculateMDC(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    // Função para calcular o MMC
    public BigInteger calculateMMC(List<BigInteger> numeros) {
        BigInteger mmc = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            mmc = mmc.multiply(numeros.get(i)).divide(calculateMDC(mmc, numeros.get(i)));
        }
        return mmc;
    }

}