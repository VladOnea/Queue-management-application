package Model;

import BusinessLogic.Simulation;
import Model.Client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{
    private int id;
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingTime;
    private int numberInQueue=0;
    private AtomicBoolean runningAtomic;

    public Queue(AtomicInteger waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Queue(int id,int maxCapacity, AtomicBoolean runningAtomic){
        this.id=id;
        this.runningAtomic=runningAtomic;
        waitingTime=new AtomicInteger(0);
        clients=new ArrayBlockingQueue<Client>(maxCapacity);
    }

    public void addClient(Client newClient){
        numberInQueue++;
        clients.offer(newClient);
        waitingTime.addAndGet(newClient.getServiceTime());
    }
    @Override
    public void run() {
        while(runningAtomic.get()){
            Client client=clients.peek();
            if(client==null){
                try{
                    Thread.sleep(Simulation.PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    Thread.sleep(client.getServiceTime()* Simulation.PERIOD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingTime.addAndGet(-client.getServiceTime());
                clients.remove();
                numberInQueue--;
            }
        }
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    @Override
    public String toString() {
        String string="Queue "+id+": ";
        for(Client client:clients)
            string+=client+", ";
        string=string.substring(0,string.length()-2); // to put a point at the end of queues
        string+=".";
        return string;
    }

    public int getWaitingTime() {
        return waitingTime.get();
    }
}

