package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class GUI extends JFrame implements ActionListener, MouseListener{

    private int numAMale;
    private int numAFemale;
    private int numChildA8;
    private int numChildU8;
    
    private JLabel instructions;
    private JLabel numAMaleLabel;
    private JLabel numAFemaleLabel;
    private JLabel numChildA8Label;
    private JLabel numChildU8Label;
    
    private JTextField numAMaleInput;
    private JTextField numAFemaleInput;
    private JTextField numChildA8Input;
    private JTextField numChildU8Input;
    
    public GUI(){
        super("Create a Hamper");
        setupGUI();
        setSize(200,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
    }
    
    public void setupGUI(){
        
        instructions = new JLabel("Enter Hamper Info");
        numAMaleLabel = new JLabel("# of Adult Males:");
        numAFemaleLabel = new JLabel("# of Adult Females:");
        numChildA8Label = new JLabel("# Child Above 8:");
        numChildU8Label = new JLabel("# Child Under 8:");
        
        numAMaleInput = new JTextField("e.g. 1", 15);
        numAFemaleInput = new JTextField("e.g. 1", 15);
        numChildA8Input = new JTextField("e.g. 1", 15);
        numChildU8Input = new JTextField("e.g. 0", 15);    
        
        numAMaleInput.addMouseListener(this);
        numAFemaleInput.addMouseListener(this);
        numChildA8Input.addMouseListener(this);
        numChildU8Input.addMouseListener(this);
        
        JButton submitInfo = new JButton("Generate Hamper");
        submitInfo.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(instructions);
        clientPanel.add(numAMaleLabel);
        clientPanel.add(numAMaleInput);
        clientPanel.add(numAFemaleLabel);
        clientPanel.add(numAFemaleInput);
        clientPanel.add(numChildA8Label);
        clientPanel.add(numChildA8Input);
        clientPanel.add(numChildU8Label);
        clientPanel.add(numChildU8Input);
        submitPanel.add(submitInfo);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    
    public void actionPerformed(ActionEvent event){
        numAFemale = Integer.parseInt(numAFemaleInput.getText());
        numAMale = Integer.parseInt(numAMaleInput.getText());
        numChildA8 = Integer.parseInt(numChildA8Input.getText());
        numChildA8 = Integer.parseInt(numChildU8Input.getText());
        
        if(validateInput()){
            String petID = idProcessing();
            JOptionPane.showMessageDialog(this, "Created Hamper For: \n" + petID);
        }
    }
    
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(numAMaleInput))
            numAMaleInput.setText("");

        if(event.getSource().equals(numAFemale))
            numAFemaleInput.setText("");

        if(event.getSource().equals(numChildA8Input))
            numChildA8Input.setText("");

        if(event.getSource().equals(numChildU8Input))
            numChildU8Input.setText("");
                
    }
    
    public void mouseEntered(MouseEvent event){}

    public void mouseExited(MouseEvent event){}

    public void mousePressed(MouseEvent event){}

    public void mouseReleased(MouseEvent event){}
    
    private String idProcessing(){

        String message = new String(
            "Adult Males: "+ String.valueOf(numAMale) + "\n"+
            "Adult Females: "+String.valueOf(numAFemale) + "\n"+
            "Child Above 8" + String.valueOf(numChildA8) + "\n"+
            "Child Under 8" + String.valueOf(numChildU8)
        );
        
        return message;
    }    
    
    private boolean validateInput(){
        
        boolean allInputValid = true;
        
        if(numAMale < 0){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, numAMale + " is an invalid Adult Male input.");
        }
        
        if(numAFemale < 0){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, numAFemale + " is an invalid Adult Female input.");
        }

        if(numChildA8 < 0){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, numChildA8 + " is an invalid Child Above 8 input.");
        }

        if(numChildU8 < 0){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, numChildU8 + " is an invalid Child Under 8 input.");
        }
        
        return allInputValid;
        
    }

    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);        
        });
    }
        
}