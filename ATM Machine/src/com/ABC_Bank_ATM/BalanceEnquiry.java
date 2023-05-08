package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    JButton button_back;
    JLabel label_display_amount;
    String pin_number;
    DBConnection dbcon;
    PreparedStatement pstmt;
    ResultSet rs;

    public BalanceEnquiry(String pin_number) {
        this.pin_number = pin_number;
        dbcon= new DBConnection();


        label_display_amount = new JLabel();
        label_display_amount.setFont(new Font("System", Font.BOLD, 16));

        button_back = new JButton("BACK");


        label_display_amount.setBounds(100, 100, 400, 35);
        add(label_display_amount);

        button_back.setBounds(150, 200, 150, 35);
        add(button_back);
        button_back.addActionListener(this);

        int balance=0;
        try {

            pstmt = dbcon.con.prepareStatement("select * from Bank where Pin_Number=?");
            pstmt.setString(1, pin_number);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("Amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("Amount"));
                }
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        label_display_amount.setText("Your Current Account Balance is Rs "+balance);

        setLayout(null);
        setSize(550,500);
        setVisible(true);
        setTitle("Balance Enquiry Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Transactions(pin_number);
    }
}
