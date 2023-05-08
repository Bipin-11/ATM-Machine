package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class Signup extends JFrame implements ActionListener {
    JLabel application_form, personal_details, name, father_name, dob, gender, email, marital_status, address, city, province_no,
    religion,category,income;
    JTextField textField_name, textField_fn,textField_email, textField_address, textField_city;

    JDateChooser dateChooser;

    JComboBox comboBoxprovince,comboBoxgender,comboBoxmarital,comboBox_religion,comboBox_category,comboBox_income;

    JButton buttonnext,button_back;

    DBConnection dbcon;
    PreparedStatement pstmt;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String form_number = "" + Math.abs(first4);

    public Signup() {

        dbcon = new DBConnection();
        dateChooser = new JDateChooser();


        application_form = new JLabel("APPLICATION FORM Number:"+form_number);
        application_form.setFont(new Font("Raleway", Font.BOLD, 38));

        personal_details = new JLabel("Page 1: Personal Details");
        personal_details.setFont(new Font("Raleway", Font.BOLD, 22));

        name = new JLabel("Full Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));

        father_name = new JLabel("Father's Name:");
        father_name.setFont(new Font("Raleway", Font.BOLD, 20));

        dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));

        gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));

        email = new JLabel("Email Address:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));

        marital_status = new JLabel("Marital Status:");
        marital_status.setFont(new Font("Raleway", Font.BOLD, 20));

        address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));

        city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));

        province_no = new JLabel("Province Number:");
        province_no.setFont(new Font("Raleway", Font.BOLD, 20));

        religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));

        category= new JLabel("Category:");
        category.setFont(new Font("Raleway", Font.BOLD, 20));

        income= new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 20));

        textField_name = new JTextField();
        textField_name.setFont(new Font("Raleway", Font.BOLD, 14));

        textField_fn = new JTextField();
        textField_fn.setFont(new Font("Raleway", Font.BOLD, 14));


        textField_email = new JTextField();
        textField_email.setFont(new Font("Raleway", Font.BOLD, 14));

        textField_address = new JTextField();
        textField_address.setFont(new Font("Raleway", Font.BOLD, 14));

        textField_city = new JTextField();
        textField_city.setFont(new Font("Raleway", Font.BOLD, 14));



        comboBoxgender= new JComboBox();
        comboBoxgender.addItem("Male");
        comboBoxgender.addItem("Female");

        comboBoxmarital= new JComboBox();
        comboBoxmarital.addItem("Married");
        comboBoxmarital.addItem("Unmarried");
        comboBoxmarital.addItem("Other");

        comboBoxprovince= new JComboBox();
        comboBoxprovince.addItem("Province 1");
        comboBoxprovince.addItem("Province 2");
        comboBoxprovince.addItem("Province 3");
        comboBoxprovince.addItem("Province 4");
        comboBoxprovince.addItem("Province 5");
        comboBoxprovince.addItem("Province 6");
        comboBoxprovince.addItem("Province 7");

        comboBox_religion= new JComboBox();
        comboBox_religion.addItem("Hindu");
        comboBox_religion.addItem("Buddhist");
        comboBox_religion.addItem("Muslim");
        comboBox_religion.addItem("Kirant");
        comboBox_religion.addItem("Christan");
        comboBox_religion.addItem("Other");


        comboBox_category= new JComboBox();
        comboBox_category.addItem("General");
        comboBox_category.addItem("OBC");
        comboBox_category.addItem("SC");
        comboBox_category.addItem("ST");
        comboBox_category.addItem("Other");

        comboBox_income= new JComboBox();
        comboBox_income.addItem("Null");
        comboBox_income.addItem("Upto 1,00,000");
        comboBox_income.addItem("Upto 5,00,000");
        comboBox_income.addItem("Upto 10,00,000");
        comboBox_income.addItem("Above 10,00,000");




        buttonnext = new JButton("Next");
        buttonnext.setFont(new Font("Raleway", Font.BOLD, 14));

        button_back=new JButton("Back");
        button_back.setFont(new Font("Raleway", Font.BOLD, 14));



        add(application_form);
        application_form.setBounds(140, 20, 700, 40);

        add(personal_details);
        personal_details.setBounds(290, 80, 600, 30);

        add(name);
        name.setBounds(100, 140, 150, 30);
        textField_name.setBounds(300, 140, 400, 30);
        add(textField_name);

        father_name.setBounds(100, 190, 200, 30);
        add(father_name);
        textField_fn.setBounds(300, 190, 400, 30);
        add(textField_fn);

        add(dob);
        dob.setBounds(100, 240, 200, 30);
        add(dateChooser);
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(300, 240, 400, 29);


        add(gender);
        gender.setBounds(100,290,200,30);
        add(comboBoxgender);
        comboBoxgender.setBounds(300,290,400,30);


        add(email);
        email.setBounds(100,340,200,30);
        add(textField_email);
        textField_email.setBounds(300,340,400,30);

        add(marital_status);
        marital_status.setBounds(100,390,200,30);
        add(comboBoxmarital);
        comboBoxmarital.setBounds(300,390,400,30);

        add(address);
        address.setBounds(100,440,400,30);
        add(textField_address);
        textField_address.setBounds(300,440,400,30);

        add(city);
        city.setBounds(100,490,200,30);
        add(textField_city);
        textField_city.setBounds(300,490,400,30);

        add(province_no);
        province_no.setBounds(100,540,200,30);
        add(comboBoxprovince);
        comboBoxprovince.setBounds(300,540,400,30);

        add(religion);
        religion.setBounds(100, 590, 100, 30);
        comboBox_religion.setBounds(300, 590, 400, 30);
        add(comboBox_religion);

        category.setBounds(100, 640, 200, 30);
        add(category);
        comboBox_category.setBounds(300, 640, 400, 30);
        add(comboBox_category);

        add(income);
        income.setBounds(100, 680, 200, 30);
        add(comboBox_income);
        comboBox_income.setBounds(300, 680, 400, 29);



        add(buttonnext);
        buttonnext.setBounds(700,730,80,30);
        buttonnext.addActionListener(this);

        add(button_back);
        button_back.setBounds(620,730,80,30);
        button_back.addActionListener(this);


        setLayout(null);
        setSize(850, 800);
        setVisible(true);
        setTitle("Signup Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //new Signup();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {

        Date sqldate= new Date(1620398045000L);
        if(ae.getSource()==buttonnext)
        {
            try
            {
                pstmt=dbcon.con.prepareStatement("insert into Signup1 values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pstmt.setString(1,form_number);
                pstmt.setString(2,textField_name.getText());
                pstmt.setString(3,textField_fn.getText());
                pstmt.setDate(4,sqldate);
                pstmt.setString(5,comboBoxgender.getSelectedItem().toString());
                pstmt.setString(6,textField_email.getText());
                pstmt.setString(7,comboBoxmarital.getSelectedItem().toString());
                pstmt.setString(8,textField_address.getText());
                pstmt.setString(9,textField_city.getText());
                pstmt.setString(10,comboBoxprovince.getSelectedItem().toString());
                pstmt.setString(11,comboBox_religion.getSelectedItem().toString());
                pstmt.setString(12,comboBox_category.getSelectedItem().toString());
                pstmt.setString(13,comboBox_income.getSelectedItem().toString());

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    new SignupTwo(form_number);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                }


            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }else if(ae.getSource()==button_back)
        {
            new Login();
        }
    }
}
