package BusinessLogic;

import GUI.SimulationFrame;
import Model.Client;
import Model.Queue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation implements Runnable {
    public static final int PERIOD = 300;
    private int maxTimeSimulation = 100;
    private int maxTimeArrival = 50;
    private int minTimeArrival = 1;
    private int minTimeService = 2;
    private int maxTimeService = 5;
    private int clientsNumber = 100;
    private int queuesNumber = 7;
    private ArrayList<Queue> queues;
    private ArrayList<Client> generatedClients;
    private AtomicBoolean runningAtomic = new AtomicBoolean(true);
    public static SimulationFrame simulationFrame;

    public Simulation(int maxTimeSimulation, int maxTimeArrival, int minTimeArrival, int minTimeService, int maxTimeService, int clientsNumber, int queuesNumber) {
        this.maxTimeSimulation = maxTimeSimulation;
        this.maxTimeArrival = maxTimeArrival;
        this.minTimeArrival = minTimeArrival;
        this.minTimeService = minTimeService;
        this.maxTimeService = maxTimeService;
        this.clientsNumber = clientsNumber;
        this.queuesNumber = queuesNumber;

        generateClients();
       // System.out.println(generatedClients);
        queues=new ArrayList<>();

        for (int i = 0; i < queuesNumber; i++) {
            Queue queue = new Queue(i+1,clientsNumber, runningAtomic);
            queues.add(queue);
            Thread thread = new Thread(queue);
            thread.start();
        }
    }

    public void generateClients() {
        Random random = new Random();
        generatedClients = new ArrayList<>();
        for (int i = 0; i < clientsNumber; i++) {
            int arrivalTime = random.nextInt(maxTimeArrival - minTimeArrival + 1) + minTimeArrival;
            int serviceTime = random.nextInt(maxTimeService - minTimeService + 1) + minTimeService;
            generatedClients.add(new Client(i + 1, arrivalTime, serviceTime));
        }
        Collections.sort(generatedClients);
    }

    @Override
    public void run() {
        int currentTime = 0;
        ArrayList<Client> toDelete = new ArrayList<>();
        FileWriter file;
        try {
            file=new FileWriter("logs.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        boolean running = true;
        while (currentTime < maxTimeSimulation && running) {
            for (Client client : generatedClients) {
                if (client.getArrivalTime() <= currentTime) {
                    dispatchClient(client);
                    toDelete.add(client);
                }
            }
            generatedClients.removeAll(toDelete);
            int activeClients = 0;
            if (generatedClients.size() == 0) {
                for (Queue queue : queues)
                    activeClients += queue.getNumberInQueue();
                if (activeClients == 0)
                    running = false;
            }
            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulationFrame.updateList(generatedClients,queues,currentTime,file);
            currentTime++;
            runningAtomic.set(running);
        }
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatchClient(Client client) {
        Queue minWaitingTime=queues.get(0);
        for(Queue queue:queues)
            if(queue.getWaitingTime()<minWaitingTime.getWaitingTime())
                minWaitingTime=queue;
        minWaitingTime.addClient(client);
    }
}
