package GUI;

import Model.Client;
import Model.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SimulationFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldClients;
    private JTextField textFieldQueues;
    private JTextField textFieldMinArrTime;
    private JTextField textFieldMinSerTime;
    private JTextField textFieldMaxArrTime;
    private JTextField textFieldMaxSerTime;
    private JTextField textFieldSimulationInterval;
    private JTextArea textArea;
    private JButton btnStartSimulation;

    public SimulationFrame() {
        setVisible(true);
        setTitle("Queue management application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblClients = new JLabel("Number of clients");
        lblClients.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblClients.setBounds(34, 45, 112, 12);
        contentPane.add(lblClients);

        textFieldClients = new JTextField();
        textFieldClients.setBounds(34, 72, 96, 18);
        contentPane.add(textFieldClients);
        textFieldClients.setColumns(10);

        textFieldQueues = new JTextField();
        textFieldQueues.setColumns(10);
        textFieldQueues.setBounds(34, 148, 96, 19);
        contentPane.add(textFieldQueues);

        JLabel lblNumberOfQueues = new JLabel("Number of queues");
        lblNumberOfQueues.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNumberOfQueues.setBounds(34, 125, 112, 13);
        contentPane.add(lblNumberOfQueues);

        textFieldMinArrTime = new JTextField();
        textFieldMinArrTime.setColumns(10);
        textFieldMinArrTime.setBounds(34, 277, 96, 19);
        contentPane.add(textFieldMinArrTime);

        JLabel lblMinArrTime = new JLabel("Minimum arrival time");
        lblMinArrTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMinArrTime.setBounds(34, 250, 112, 13);
        contentPane.add(lblMinArrTime);

        textFieldMinSerTime = new JTextField();
        textFieldMinSerTime.setColumns(10);
        textFieldMinSerTime.setBounds(34, 343, 96, 19);
        contentPane.add(textFieldMinSerTime);

        JLabel lblMinSerTime = new JLabel("Minimum service time");
        lblMinSerTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMinSerTime.setBounds(34, 316, 123, 13);
        contentPane.add(lblMinSerTime);

        textFieldMaxArrTime = new JTextField();
        textFieldMaxArrTime.setColumns(10);
        textFieldMaxArrTime.setBounds(173, 277, 96, 19);
        contentPane.add(textFieldMaxArrTime);

        JLabel lblMaxArrTime = new JLabel("Maximum arrival time");
        lblMaxArrTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMaxArrTime.setBounds(173, 250, 112, 13);
        contentPane.add(lblMaxArrTime);

        textFieldMaxSerTime = new JTextField();
        textFieldMaxSerTime.setColumns(10);
        textFieldMaxSerTime.setBounds(173, 343, 96, 19);
        contentPane.add(textFieldMaxSerTime);

        JLabel lblMaxSerTime = new JLabel("Maximum service time");
        lblMaxSerTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblMaxSerTime.setBounds(173, 316, 123, 13);
        contentPane.add(lblMaxSerTime);

        textFieldSimulationInterval = new JTextField();
        textFieldSimulationInterval.setColumns(10);
        textFieldSimulationInterval.setBounds(34, 207, 96, 19);
        contentPane.add(textFieldSimulationInterval);

        JLabel lblSimulationInterval = new JLabel("Simulation interval");
        lblSimulationInterval.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblSimulationInterval.setBounds(34, 184, 112, 13);
        contentPane.add(lblSimulationInterval);

        btnStartSimulation = new JButton("Start simulation");
        btnStartSimulation.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnStartSimulation.setBounds(92, 395, 122, 21);
        contentPane.add(btnStartSimulation);

        textArea = new JTextArea();
        textArea.setBounds(306, 32, 670, 410);
        contentPane.add(textArea);

        repaint();
        revalidate();
    }

    public void updateList(ArrayList<Client> clients, ArrayList<Model.Queue> queues, int currentTime, FileWriter file){
        String string="Current time: "+currentTime+"\n";
        string+=clients+"\n";
        for(Queue queue:queues)
            string+=queue+"\n";
        try {
            file.write(string+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.setText(string);
    }

    public JButton getBtnStartSimulation() {
        return btnStartSimulation;
    }

    public JTextField getTextFieldClients() {
        return textFieldClients;
    }

    public JTextField getTextFieldQueues() {
        return textFieldQueues;
    }

    public JTextField getTextFieldMinArrTime() {
        return textFieldMinArrTime;
    }

    public JTextField getTextFieldMinSerTime() {
        return textFieldMinSerTime;
    }

    public JTextField getTextFieldMaxArrTime() {
        return textFieldMaxArrTime;
    }

    public JTextField getTextFieldMaxSerTime() {
        return textFieldMaxSerTime;
    }

    public JTextField getTextFieldSimulationInterval() {
        return textFieldSimulationInterval;
    }
}
