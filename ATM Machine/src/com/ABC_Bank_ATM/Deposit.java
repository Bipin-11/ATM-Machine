package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;

public class Deposit extends JFrame implements ActionListener {

    JLabel label_amount;
    JTextField textField__amount;

    JButton button_deposit,button_back;

    DBConnection dbcon;
    PreparedStatement pstmt;
    String pin_number;
    public Deposit(String pin_number)
    {
        dbcon= new DBConnection();
        this.pin_number=pin_number;

        label_amount = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT:");
        label_amount.setFont(new Font("System", Font.BOLD, 16));

        textField__amount = new JTextField();
        textField__amount.setFont(new Font("Raleway", Font.BOLD, 22));

        button_deposit = new JButton("DEPOSIT");
        button_deposit.setFont(new Font("Raleway", Font.BOLD, 14));

        button_back = new JButton("BACK");
        button_back.setFont(new Font("Raleway", Font.BOLD, 14));


        label_amount.setBounds(190,100,400,35);
        add(label_amount);

        textField__amount.setBounds(190,150,320,25);
        add(textField__amount);

        button_deposit.setBounds(390,200,150,35);
        add(button_deposit);
        button_deposit.addActionListener(this);

        button_back.setBounds(390,250,150,35);
        add(button_back);
        button_back.addActionListener(this);


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850,800);
        setVisible(true);
        setTitle("Deposit Page");
    }
    public static void main(String[] args) {
        //new Deposit("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Date utilDate = new Date(1620398045000L); // current date and time
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String type="Deposit";
        String amount=textField__amount.getText();
            if(ae.getSource()==button_deposit){
                if(textField__amount.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else
                {
                    try{
                    pstmt=dbcon.con.prepareStatement("insert into Bank values(?,?,?,?)");
                    pstmt.setString(1,pin_number);
                    pstmt.setDate(2,sqlDate);
                    pstmt.setString(3,type);
                    pstmt.setString(4,amount);

                    pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                        new Transactions(pin_number).setVisible(true);
                }catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
            }
            }else if(ae.getSource()==button_back){

                new Transactions(pin_number).setVisible(true);
        }
    }
}
