/**
@author     Nooreldeen Abdallah
href= "mailto:nooreldeen.abdallah@ucalgary.ca">nooreldeen.abdallah@ucalgary.ca</a>
@version    2.0
@since      1.8
 */

package edu.ucalgary.ensf409;


//Todo: Multiple families

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class GUI extends JFrame implements ActionListener, MouseListener{

    private int numFams;

    
    private JLabel instructions;
    private JLabel numFamsLabel;
    
    private JTextField numFamsInput;

    
    // constructor
    public GUI(){
        super("Main GUI");
        setupGUI();

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
    }
    
    // displays GUI
    public void setupGUI(){
        
        instructions = new JLabel("");
        numFamsLabel = new JLabel("# of Familes to Create Hampers for:");

        
        numFamsInput = new JTextField("e.g. 1", 15); 
        
        numFamsInput.addMouseListener(this);

        
        JButton submitInfo = new JButton("Input Families Info");
        submitInfo.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        
        headerPanel.add(instructions);
        clientPanel.add(numFamsLabel);
        clientPanel.add(numFamsInput);
        submitPanel.add(submitInfo);
        
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }
    
    // Redirects to hamper generation panel
    public void actionPerformed(ActionEvent event){
        try{
            numFams = Integer.parseInt(numFamsInput.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "All values must be Integers");
        }
        
        if(validateInput()){
            JOptionPane.showMessageDialog(this, "Num Fams: " + numFams + "\nPlease fill out the Hamper Info in order \n(i.e., Family #1 first)");
            genHampers();

            setVisible(false); //you can't see me!
            dispose(); //Destroy the JFrame object
        }
    }
    
    //clears inputs so user can type
    public void mouseClicked(MouseEvent event){
        if(event.getSource().equals(numFamsInput)){
            numFamsInput.setText("");
        }      
    }
    
    // ignores random mouse clicks
    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event){}
    
    
    // checks all the obtained values and shows an error 
    // if an invalid input is obtained 
    private boolean validateInput(){

        if(numFams <= 0 ){
            JOptionPane.showMessageDialog(this, numFams + " is an invalid Number of Families.");
        }
        
        return numFams >= 1;
    }

    //Opens generate hamper panel
    private void genHampers(){
        for( int i = 1; i <= numFams; i++){
            int j = i;
            EventQueue.invokeLater(() -> { 
                new FamInfoPanel(j, numFams).setVisible(true);
            });
        }
    }

    
    // command line args are ignored
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);        
        });
    }
        
}