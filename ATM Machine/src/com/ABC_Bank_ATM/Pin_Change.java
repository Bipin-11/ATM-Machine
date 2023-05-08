package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Pin_Change extends JFrame implements ActionListener {
    JPasswordField passwordField_new_pin, passwordField_conform_new;
    JButton button_change, button_back;
    JLabel label_heading, label_new_pin, label_conform_new;
    String pin_number;
    DBConnection dbcon;
    PreparedStatement pstmt1;
    PreparedStatement pstmt2;
    PreparedStatement pstmt3;

    public Pin_Change(String pin_number) {
        this.pin_number = pin_number;
        dbcon = new DBConnection();

        label_heading = new JLabel("CHANGE YOUR PIN");
        label_heading.setFont(new Font("System", Font.BOLD, 16));

        label_new_pin = new JLabel("New PIN:");
        label_new_pin.setFont(new Font("System", Font.BOLD, 16));

        label_conform_new = new JLabel("Re-Enter New PIN:");
        label_conform_new.setFont(new Font("System", Font.BOLD, 16));

        passwordField_new_pin = new JPasswordField();
        passwordField_new_pin.setFont(new Font("Raleway", Font.BOLD, 25));

        passwordField_conform_new = new JPasswordField();
        passwordField_conform_new.setFont(new Font("Raleway", Font.BOLD, 25));

        button_change = new JButton("CHANGE");
        button_change.setFont(new Font("Raleway", Font.BOLD, 14));

        button_back = new JButton("BACK");
        button_back.setFont(new Font("Raleway", Font.BOLD, 14));


        label_heading.setBounds(280, 100, 800, 35);
        add(label_heading);

        label_new_pin.setBounds(180, 150, 150, 35);
        add(label_new_pin);

        label_conform_new.setBounds(180, 200, 200, 35);
        add(label_conform_new);

        passwordField_new_pin.setBounds(350, 150, 180, 25);
        add(passwordField_new_pin);

        passwordField_conform_new.setBounds(350, 200, 180, 25);
        add(passwordField_conform_new);

        button_change.setBounds(390, 300, 150, 35);
        add(button_change);
        button_change.addActionListener(this);

        button_back.setBounds(390, 350, 150, 35);
        add(button_back);
        button_back.addActionListener(this);

        setLayout(null);
        setSize(850, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pin Change Page");
    }

    public static void main(String[] args) {
        //new Pin_Change("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String new_pin = passwordField_new_pin.getText();
        String conform_new_pin = passwordField_conform_new.getText();
        if (e.getSource() == button_change) {
            if (!new_pin.equals(conform_new_pin)) {
                JOptionPane.showMessageDialog(null, "Entered pin does not matched");
                return;
            }
            if (new_pin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter new pin");
                return;
            }
            if (conform_new_pin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please conform your new pin");
                return;
            }
            try {
                pstmt1 = dbcon.con.prepareStatement("update Bank set Pin_Number=" + new_pin + " where Pin_Number=?");
                pstmt1.setString(1, pin_number);

                pstmt2 = dbcon.con.prepareStatement("update Login set Pin_Number=" + new_pin + " where Pin_Number=?");
                pstmt2.setString(1, pin_number);

                pstmt3 = dbcon.con.prepareStatement("update Signup2 set Pin_Number=" + new_pin + " where Pin_Number=?");
                pstmt3.setString(1, pin_number);

                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                new Login().setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else  if(e.getSource()==button_back)
        {
            new Transactions(pin_number);
        }
    }
}
