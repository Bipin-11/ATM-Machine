package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {

    JLabel label_heading;

    JButton button_deposit,button_withdraw,button_fast_cash,button_mini_stmt,button_pin_change,button_balance_enquiry,button_exit;
    String pin_number;
    public Transactions(String pin_number)
    {
        this.pin_number= pin_number;
        label_heading = new JLabel("Please Select Your Transaction");
        label_heading.setFont(new Font("System", Font.BOLD, 26));


        button_deposit = new JButton("DEPOSIT");
        button_withdraw = new JButton("CASH WITHDRAWL");
        button_fast_cash = new JButton("FAST CASH");
        button_mini_stmt = new JButton("MINI STATEMENT");
        button_pin_change = new JButton("PIN CHANGE");
        button_balance_enquiry = new JButton("BALANCE ENQUIRY");
        button_exit= new JButton("EXIT");

        add(label_heading);
        label_heading.setBounds(235,40,700,35);

        add(button_deposit);
        button_deposit.setBounds(190,100,150,35);
        button_deposit.setFont(new Font("System", Font.BOLD, 16));
        button_deposit.addActionListener(this);


        button_withdraw.setBounds(390,100,200,35);
        button_withdraw.setFont(new Font("System", Font.BOLD, 16));
        add(button_withdraw);
        button_withdraw.addActionListener(this);

        button_fast_cash.setBounds(190,200,150,35);
        add(button_fast_cash);
        button_fast_cash.setFont(new Font("System", Font.BOLD, 16));
        button_fast_cash.addActionListener(this);

        button_mini_stmt.setBounds(390,200,200,35);
        add(button_mini_stmt);
        button_mini_stmt.setFont(new Font("System", Font.BOLD, 16));
        button_mini_stmt.addActionListener(this);

        button_pin_change.setBounds(190,300,150,35);
        add(button_pin_change);
        button_pin_change.setFont(new Font("System", Font.BOLD, 16));
        button_pin_change.addActionListener(this);

        button_balance_enquiry.setBounds(390,300,200,35);
        add(button_balance_enquiry);
        button_balance_enquiry.setFont(new Font("System", Font.BOLD, 16));
        button_balance_enquiry.addActionListener(this);

        button_exit.setBounds(300,400,150,35);
        button_exit.setFont(new Font("System", Font.BOLD, 16));
        add(button_exit);
        button_exit.addActionListener(this);



        setLayout(null);
        setSize(850,800);
        setVisible(true);
        setTitle("Transactions Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Transactions("");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==button_exit)
        {
            new Login();
        }else  if(ae.getSource()==button_deposit)
        {
            new Deposit(pin_number);
        }else if(ae.getSource()==button_withdraw)
        {
            new Withdraw(pin_number);
        }else  if(ae.getSource()==button_fast_cash)
        {
            new FastCash(pin_number);
        }else  if(ae.getSource()==button_pin_change)
        {
            new Pin_Change(pin_number);
        }else  if(ae.getSource()==button_balance_enquiry)
        {
            new BalanceEnquiry(pin_number);
        }else if(ae.getSource()==button_mini_stmt)
        {
            new MiniStatement(pin_number);
        }

    }
}