package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FastCash extends JFrame implements ActionListener {

    JLabel label_heading;

    JButton button_5h,button_1t,button_2t,button_3t,button_5t,button_10t,button_back;
    String pin_number;

    DBConnection dbcon;

    PreparedStatement pstmt;
    PreparedStatement pstmt1;
    ResultSet rs;
    public FastCash(String pin_number)
    {
        dbcon= new DBConnection();
        this.pin_number= pin_number;
        label_heading = new JLabel("SELECT WITHDRAW AMOUNT");
        label_heading.setFont(new Font("System", Font.BOLD, 26));


        button_5h = new JButton("Rs.500");
        button_1t = new JButton("Rs.1000");
        button_2t = new JButton("Rs.2000");
        button_3t = new JButton("Rs.3000");
        button_5t = new JButton("Rs.5000");
        button_10t = new JButton("Rs.10000");
        button_back= new JButton("BACK");

        add(label_heading);
        label_heading.setBounds(235,40,700,35);

        add(button_5h);
        button_5h.setBounds(190,100,150,35);
        button_5h.setFont(new Font("System", Font.BOLD, 16));
        button_5h.addActionListener(this);


        button_1t.setBounds(390,100,200,35);
        button_1t.setFont(new Font("System", Font.BOLD, 16));
        add(button_1t);
        button_1t.addActionListener(this);

        button_2t.setBounds(190,200,150,35);
        add(button_2t);
        button_2t.setFont(new Font("System", Font.BOLD, 16));
        button_2t.addActionListener(this);

        button_3t.setBounds(390,200,200,35);
        add(button_3t);
        button_3t.setFont(new Font("System", Font.BOLD, 16));
         button_3t.addActionListener(this);

        button_5t.setBounds(190,300,150,35);
        add(button_5t);
        button_5t.setFont(new Font("System", Font.BOLD, 16));
        button_5t.addActionListener(this);

        button_10t.setBounds(390,300,200,35);
        add(button_10t);
        button_10t.setFont(new Font("System", Font.BOLD, 16));
        button_10t.addActionListener(this);

        button_back.setBounds(300,400,150,35);
        button_back.setFont(new Font("System", Font.BOLD, 16));
        add(button_back);
        button_back.addActionListener(this);



        setLayout(null);
        setSize(850,800);
        setVisible(true);
        setTitle("Fast Cash Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new FastCash("");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==button_back)
        {
            //new Transactions(pin_number);
        }else
        {
            String amount = ((JButton)ae.getSource()).getText().substring(3);
            try{
               pstmt=dbcon.con.prepareStatement("select * from Bank where Pin_Number=?");
               pstmt.setString(1,pin_number);
               rs= pstmt.executeQuery();
               int balance=0;
               while (rs.next()) {
                   if (rs.getString("type").equals("Deposit")) {
                       balance += Integer.parseInt(rs.getString("Amount"));
                   } else {
                       balance -= Integer.parseInt(rs.getString("Amount"));
                   }
               }
               if(ae.getSource()!=button_back && balance <Integer.parseInt(amount))
               {
                   JOptionPane.showMessageDialog(null,"Insufficient Balance");
                   return;
               }
               String type="Withdraw";
                Date date= new Date(1620398045000L);
               pstmt1=dbcon.con.prepareStatement("insert into Bank values(?,?,?,?)");
               pstmt1.setString(1,pin_number);
               pstmt1.setDate(2,date);
               pstmt1.setString(3,type);
               pstmt1.setString(4,amount);

               pstmt1.executeUpdate();
               JOptionPane.showMessageDialog(null,"Rs. "+amount+" Debited Successfully");
               new Transactions(pin_number);

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }

    }
}