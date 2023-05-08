package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label_introduction,label_cardno,label_pinno;
    JTextField textField_card;
    JPasswordField passwordField_pin;
    JButton btn_signin,btn_clear,button_signup,button_exit;

    DBConnection dbcon;
    PreparedStatement pstmt;

    ResultSet rs;

    public Login()
    {
        dbcon = new DBConnection();
        label_introduction= new JLabel("WelCome to ATM");
        label_introduction.setFont(new Font("Raleway", Font.BOLD, 38));
        label_introduction.setHorizontalAlignment(SwingConstants.LEFT);

        label_cardno= new JLabel("Card Number:");
        label_cardno.setFont(new Font("Raleway", Font.BOLD, 22));
        label_pinno= new JLabel("Pin Number:");
        label_pinno.setFont(new Font("Raleway", Font.BOLD, 22));

        textField_card= new JTextField();

        passwordField_pin= new JPasswordField();

        btn_signin= new JButton("Sign in");
        btn_signin.setFont(new Font("Raleway", Font.BOLD, 14));

        btn_clear= new JButton("Clear");
        btn_clear.setFont(new Font("Raleway", Font.BOLD, 14));

        button_signup= new JButton("Sign up");
        button_signup.setFont(new Font("Raleway", Font.BOLD, 14));

        button_exit=new JButton("Exit");
        button_exit.setFont(new Font("Raleway", Font.BOLD, 14));

        add(label_introduction);
        label_introduction.setBounds(200,40,450,40);

        add(label_cardno);
        label_cardno.setBounds(125,150,375,30);
        add(textField_card);
        textField_card.setBounds(300,150,230,30);

        add(label_pinno);
        label_pinno.setBounds(125,220,375,30);
        add(passwordField_pin);
        passwordField_pin.setBounds(300,220,230,30);

        add(btn_signin);
        btn_signin.setBounds(300,300,100,30);
        btn_signin.addActionListener(this);

        add(btn_clear);
        btn_clear.setBounds(430,300,100,30);
        btn_clear.addActionListener(this);

        add(button_signup);
        button_signup.setBounds(300,350,230,30);
        button_signup.addActionListener(this);

        add(button_exit);
        button_exit.setBounds(300,400,230,30);
        button_exit.addActionListener(this);




        setLayout(null);
        setTitle("Login Page");
        setVisible(true);
        setSize(850,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_clear)
        {
          textField_card.setText("");
          passwordField_pin.setText("");
        }else if(ae.getSource()==button_signup)
        {
            new Signup();
        }else if(ae.getSource()==btn_signin)
        {
            String pin_number=passwordField_pin.getText();
            try {
                pstmt=dbcon.con.prepareStatement("select * from Login where Card_Number=? and Pin_Number=?");
                pstmt.setString(1,textField_card.getText());
                pstmt.setString(2,pin_number);

                rs=pstmt.executeQuery();

                if(rs.next())
                {
                    JOptionPane.showMessageDialog(null,"Login Successful");
                    new Transactions(pin_number);
                }else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }else if(ae.getSource()==button_exit)
        {
            System.exit(0);
        }
    }
    public static void main(String[] args)
    {
        new Login();
    }
}
