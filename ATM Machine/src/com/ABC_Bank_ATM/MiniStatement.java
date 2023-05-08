package com.ABC_Bank_ATM;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ScatteringByteChannel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiniStatement extends JFrame implements ActionListener {
    JButton button_back;
    JLabel label_heading,label_subheading,label_card;

    DBConnection dbcon;
    PreparedStatement pstmt;
    PreparedStatement pstmt1;
    ResultSet rs;
    String pin_number;

   public  MiniStatement(String pin_number) {
       this.pin_number = pin_number;
       dbcon = new DBConnection();

       label_heading = new JLabel("ABC Bank of Nepal");
       label_heading.setFont(new Font("System", Font.BOLD, 26));


       add(label_heading);
       label_heading.setBounds(150, 20, 300, 30);

       label_subheading = new JLabel("Mini Statement");
       label_subheading.setFont(new Font("System", Font.BOLD, 16));

       label_subheading.setBounds(190, 80, 300, 20);
       add(label_subheading);

       label_card = new JLabel();
       label_card.setBounds(150, 120, 300, 20);
       label_card.setFont(new Font("System", Font.BOLD, 16));
       add(label_card);

       button_back= new JButton("Back");
       add(button_back);
       button_back.setBounds(430,20,100,30);
       button_back.addActionListener(this);


       try {
           pstmt = dbcon.con.prepareStatement("select * from Login where Pin_Number=?");
           pstmt.setString(1, pin_number);

           rs = pstmt.executeQuery();
           while (rs.next()) {
               label_card.setText("Card Number: " + rs.getString("Card_Number"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       int balance = 0;
       try {

           pstmt1 = dbcon.con.prepareStatement("select * from Bank where Pin_Number=?");
           pstmt1.setString(1, pin_number);
           rs = pstmt1.executeQuery();
           if (rs.next()) {
               //rs.previous();
               DefaultTableModel tablemodel = new DefaultTableModel();
               JTable table = new JTable(tablemodel);
               tablemodel.addColumn("Pin Number");
               tablemodel.addColumn("Date");
               tablemodel.addColumn("Type");
               tablemodel.addColumn("Amount");

               do {
                   tablemodel.addRow(new Object[]{
                           rs.getString(1),
                           rs.getString(2),
                           rs.getString(3),
                           rs.getString(4),

                   });
               } while (rs.next());
               int vertical_scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
               int horizontal_scroll = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

               JScrollPane scrollPane = new JScrollPane((table), vertical_scroll, horizontal_scroll);

               add(scrollPane);

               scrollPane.setBounds(100, 150, 500, 300);
           }
       }catch (Exception ex)
       {
           ex.printStackTrace();
       }
       setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650,600);
        setVisible(true);
        setTitle("Mini Statement Page");



    }

    public static void main(String[] args) {
        new MiniStatement("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Transactions(pin_number);
    }
}
