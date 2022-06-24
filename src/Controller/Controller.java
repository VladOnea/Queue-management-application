package Controller;

import BusinessLogic.Simulation;
import GUI.SimulationFrame;

import javax.swing.*;



public class Controller {
    SimulationFrame simulationFrame;

    public Controller() {
        simulationFrame = new SimulationFrame();
        Simulation.simulationFrame = simulationFrame;
        simulationFrame.getBtnStartSimulation().addActionListener(e -> {
            int maxTimeSimulation,maxTimeArrival,minTimeArrival,minTimeService,maxTimeService,clientsNumber,queuesNumber;
            try {
                maxTimeSimulation = Integer.parseInt(simulationFrame.getTextFieldSimulationInterval().getText());
                maxTimeArrival = Integer.parseInt(simulationFrame.getTextFieldMaxArrTime().getText());
                minTimeArrival = Integer.parseInt(simulationFrame.getTextFieldMinArrTime().getText());
                maxTimeService = Integer.parseInt(simulationFrame.getTextFieldMaxSerTime().getText());
                minTimeService = Integer.parseInt(simulationFrame.getTextFieldMinSerTime().getText());
                clientsNumber = Integer.parseInt(simulationFrame.getTextFieldClients().getText());
                queuesNumber = Integer.parseInt(simulationFrame.getTextFieldQueues().getText());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(),"Invalid number format!","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            new Thread(new Simulation(maxTimeSimulation,maxTimeArrival,minTimeArrival,minTimeService,maxTimeService,clientsNumber,queuesNumber)).start();
        });
    }
}
