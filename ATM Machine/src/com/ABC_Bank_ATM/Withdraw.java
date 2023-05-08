package com.ABC_Bank_ATM;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdraw extends JFrame implements ActionListener {

    JLabel label_amount;
    JTextField textField__amount;

    JButton button_withdraw, button_back;

    DBConnection dbcon;
    PreparedStatement pstmt;
    PreparedStatement pstmt1;
    String pin_number;

    ResultSet rs;

    public Withdraw(String pin_number) {
        dbcon = new DBConnection();
        this.pin_number = pin_number;

        label_amount = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW:");
        label_amount.setFont(new Font("System", Font.BOLD, 16));

        textField__amount = new JTextField();
        textField__amount.setFont(new Font("Raleway", Font.BOLD, 22));

        button_withdraw = new JButton("WITHDRAW");
        button_withdraw.setFont(new Font("Raleway", Font.BOLD, 14));

        button_back = new JButton("BACK");
        button_back.setFont(new Font("Raleway", Font.BOLD, 14));


        label_amount.setBounds(190, 100, 400, 35);
        add(label_amount);

        textField__amount.setBounds(190, 150, 320, 25);
        add(textField__amount);

        button_withdraw.setBounds(390, 200, 150, 35);
        add(button_withdraw);
        button_withdraw.addActionListener(this);

        button_back.setBounds(390, 250, 150, 35);
        add(button_back);
        button_back.addActionListener(this);


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 800);
        setVisible(true);
        setTitle("Withdraw Page");
    }

    public static void main(String[] args) {
        //new Withdraw("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String Type = "Withdraw";
        String amount_withdraw = textField__amount.getText();
        if (ae.getSource() == button_withdraw) {
            if (textField__amount.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
            } else {
                try {
                    pstmt = dbcon.con.prepareStatement("select * from Bank where Pin_Number=?");
                    pstmt.setString(1, pin_number);
                    rs = pstmt.executeQuery();
                    int balance = 0;
                    while (rs.next()) {
                        if (rs.getString("Type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("Amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("Amount"));
                        }
                    }
                    if (ae.getSource() != button_back && balance < Integer.parseInt(amount_withdraw)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    Date date = new Date(1620398045000L);
                    pstmt1 = dbcon.con.prepareStatement("insert into Bank values(?,?,?,?)");
                    pstmt1.setString(1, pin_number);
                    pstmt1.setDate(2, date);
                    pstmt1.setString(3, Type);
                    pstmt1.setString(4, amount_withdraw);

                    pstmt1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Rs. " + amount_withdraw + " Debited Successfully");
                    new Transactions(pin_number);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }else  if(ae.getSource()==button_back)
        {
            new Transactions(pin_number);
        }
    }
}


