/**
@author     Nooreldeen Abdallah
href= "mailto:nooreldeen.abdallah@ucalgary.ca">nooreldeen.abdallah@ucalgary.ca</a>
@version    1.14
@since      1.13
 */

package edu.ucalgary.ensf409;


//Todo: Multiple families

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class FamInfoPanel extends JFrame implements ActionListener, MouseListener{

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

    private int famNumber;
    private int numOfFamilies;
    private static Order request;
    
    // constructor
    public FamInfoPanel(int famNumber,int numFams){

        super("Create a Hamper for Family #" + famNumber);
        this.famNumber = famNumber;
        this.numOfFamilies = numFams;
        setupGUI();


        setSize(250,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        
    }
    
    // displays GUI
    public void setupGUI(){
        
        instructions = new JLabel("Enter Hamper Info for Family # " + famNumber);
        //numHampersLabel = new JLabel("# of Hampers to Create:");
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
        
        JButton submitInfo = new JButton("Generate Hamper and Print Form");
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
    
    // runs when generate hamper button is clicked
    public void actionPerformed(ActionEvent event){
        try{
            numAFemale = Integer.parseInt(numAFemaleInput.getText());
            numAMale = Integer.parseInt(numAMaleInput.getText());
            numChildA8 = Integer.parseInt(numChildA8Input.getText());
            numChildU8 = Integer.parseInt(numChildU8Input.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "All values must be Integers");
        }
        
        if(validateInput()){

            generateOrder();

            if (famNumber == numOfFamilies){ // this means we need to start creating hampers now 
                OrderForm orderForm = new OrderForm();
                try {
					request.generateHampers();
                    orderForm.formToTxtFile(request, "orderform.txt");
				} catch (ItemNotFoundException e) {
                    JOptionPane.showMessageDialog(this,"Hamper Can't be Created due to a shortage in the Inventory!\n" );
                    orderForm.formToText("orderform.txt");
                    setVisible(false);
                    dispose();
				}
                
            }

            String msg = dispMsg();
            JOptionPane.showMessageDialog(this, "Created Hamper Info: \n" + msg);
            setVisible(false);
            dispose();
        }
    }
    
    //clears inputs so user can type
    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(numAMaleInput))
            numAMaleInput.setText("");

        if(event.getSource().equals(numAFemaleInput))
            numAFemaleInput.setText("");

        if(event.getSource().equals(numChildA8Input))
            numChildA8Input.setText("");

        if(event.getSource().equals(numChildU8Input))
            numChildU8Input.setText("");                
    }
    
    // ignores random mouse clicks
    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event){}
    

    // displays input for debugging
    private String dispMsg(){

        String message = new String(
            "Adult Males: "+ String.valueOf(numAMale) + "\n"+
            "Adult Females: "+String.valueOf(numAFemale) + "\n"+
            "Child Above 8: " + String.valueOf(numChildA8) + "\n"+
            "Child Under 8: " + String.valueOf(numChildU8) + "\n"
        );
        
        return message;
    }    
    
    // checks all the obtained values and shows an error 
    // if an invalid input is obtained 
    private boolean validateInput(){
        
        boolean allInputValid = true;
        
        if(numAMale < 0 ){
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

        if(numAMale == 0 && numAFemale == 0 && numChildA8 == 0 && numChildU8 == 0){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, "Family should have at least one person");
        }
        
        return allInputValid;
    }

    private void generateOrder(){
        Family family = new Family(numAMale, numAFemale, numChildA8, numChildU8);
        if(famNumber == 1){
            request = new Order(family);
        }
        else{
            request.addFamily(family);
        }
    }

    // command line args are ignored
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            new FamInfoPanel(0, 0).setVisible(true);        
        });
    }
        
}
