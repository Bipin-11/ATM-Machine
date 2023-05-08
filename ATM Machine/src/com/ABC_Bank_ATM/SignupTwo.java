package com.ABC_Bank_ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Random;

public class SignupTwo extends JFrame implements ActionListener {
    DBConnection dbcon;
    PreparedStatement pstmt1;
    PreparedStatement pstmt2;
    JLabel additional_details, religion, category, income, education, occupation, citizenship_number, senior_citizen, existing_acc,
            account_type, card_number, pin_number, card_details, pin_details, service_required, d_card, p_card;

    JComboBox comboBox_religion, comboBox_category, comboBox_income, comboBox_education, comboBox_occupation, comboBox_account;

    JRadioButton radioButton_senior1, radioButton_senior2, radioButton_existing1, radioButton_existing2;

    JTextField textField_citizen;

    JCheckBox checkBox_atm, checkBox_internet, checkBox_mobile, checkBox_cheque, checkBox_email, checkBox_statement, checkBox_click;
    JButton buttonsumit, buttoncancel;

    String form_number;
    Random ran = new Random();
    long pin4 = (ran.nextLong() % 9000L) + 1000L;
    String atm_pin = "" + Math.abs(pin4);
    long card7 = (ran.nextLong() % 90000000L) + 5040936000000000L;
    String cardno = "" + Math.abs(card7);

    public SignupTwo(String form_number) {
        this.form_number = form_number;

        dbcon = new DBConnection();

        additional_details = new JLabel("Page 2: Additional Details");
        additional_details.setFont(new Font("Raleway", Font.BOLD, 22));

        religion = new JLabel("Religion:");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));

        category = new JLabel("Category:");
        category.setFont(new Font("Raleway", Font.BOLD, 20));

        income = new JLabel("Income:");
        income.setFont(new Font("Raleway", Font.BOLD, 20));

        education = new JLabel("Education:");
        education.setFont(new Font("Raleway", Font.BOLD, 20));

        occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));

        citizenship_number = new JLabel("Citizenship Number:");
        citizenship_number.setFont(new Font("Raleway", Font.BOLD, 20));

        senior_citizen = new JLabel("Senior Citizen:");
        senior_citizen.setFont(new Font("Raleway", Font.BOLD, 20));

        existing_acc = new JLabel("Existing Account:");
        existing_acc.setFont(new Font("Raleway", Font.BOLD, 20));

        account_type = new JLabel("Account Type:");
        account_type.setFont(new Font("Raleway", Font.BOLD, 20));

        card_number = new JLabel("Card Number:");
        card_number.setFont(new Font("Raleway", Font.BOLD, 20));
        card_details = new JLabel("Your 16 Digit Card Number");
        card_details.setFont(new Font("Raleway", Font.BOLD, 12));
        d_card = new JLabel("XXXX-XXXX-XXXX-XXXX");
        d_card.setFont(new Font("Raleway", Font.BOLD, 20));

        pin_number = new JLabel("Pin Number:");
        pin_number.setFont(new Font("Raleway", Font.BOLD, 20));
        pin_details = new JLabel("Your 4 Digit Password");
        pin_details.setFont(new Font("Raleway", Font.BOLD, 12));
        p_card = new JLabel("XXXX");
        p_card.setFont(new Font("Raleway", Font.BOLD, 20));

        service_required = new JLabel("Service Required:");
        service_required.setFont(new Font("Raleway", Font.BOLD, 20));

        comboBox_religion = new JComboBox();
        comboBox_religion.addItem("Hindu");
        comboBox_religion.addItem("Buddhist");
        comboBox_religion.addItem("Muslim");
        comboBox_religion.addItem("Kirant");
        comboBox_religion.addItem("Christan");
        comboBox_religion.addItem("Other");


        comboBox_category = new JComboBox();
        comboBox_category.addItem("General");
        comboBox_category.addItem("OBC");
        comboBox_category.addItem("SC");
        comboBox_category.addItem("ST");
        comboBox_category.addItem("Other");

        comboBox_income = new JComboBox();
        comboBox_income.addItem("Null");
        comboBox_income.addItem("Upto 1,00,000");
        comboBox_income.addItem("Upto 5,00,000");
        comboBox_income.addItem("Upto 10,00,000");
        comboBox_income.addItem("Above 10,00,000");

        comboBox_education = new JComboBox();
        comboBox_education.addItem("Primary");
        comboBox_education.addItem("Secondary");
        comboBox_education.addItem("Bachelor");
        comboBox_education.addItem("Master");

        comboBox_occupation = new JComboBox();
        comboBox_occupation.addItem("Government");
        comboBox_occupation.addItem("Non-Government");
        comboBox_occupation.addItem("Self-Employed");
        comboBox_occupation.addItem("Business");
        comboBox_occupation.addItem("Student");
        comboBox_occupation.addItem("Retired");
        comboBox_occupation.addItem("Other");

        comboBox_account = new JComboBox();
        comboBox_account.addItem("Saving Account");
        comboBox_account.addItem("Fixed Deposit Account");
        comboBox_account.addItem("Current Account");
        comboBox_account.addItem("Recurring Deposit Account");


        checkBox_atm = new JCheckBox("ATM CARD");
        checkBox_cheque = new JCheckBox("Cheque Book");
        checkBox_statement = new JCheckBox("E-Statement");
        checkBox_mobile = new JCheckBox("Mobile Banking");
        checkBox_email = new JCheckBox("EMAIL & SMS Alerts");
        checkBox_internet = new JCheckBox("Internet Banking");


        checkBox_click = new JCheckBox("I hereby declares that the above entered details correct to th best of my knowledge.", true);
        checkBox_click.setBackground(Color.WHITE);
        checkBox_click.setFont(new Font("Raleway", Font.BOLD, 12));


        textField_citizen = new JTextField();
        textField_citizen.setFont(new Font("Raleway", Font.BOLD, 14));

        radioButton_senior1 = new JRadioButton("Yes");
        radioButton_senior2 = new JRadioButton("No");

        ButtonGroup group_senior = new ButtonGroup();
        group_senior.add(radioButton_senior1);
        group_senior.add(radioButton_senior2);

        radioButton_existing1 = new JRadioButton("Yes");
        radioButton_existing2 = new JRadioButton("No");

        ButtonGroup group_existing = new ButtonGroup();
        group_existing.add(radioButton_existing1);
        group_existing.add(radioButton_existing2);


        buttonsumit = new JButton("Sumit");
        buttonsumit.setFont(new Font("Raleway", Font.BOLD, 14));
        buttonsumit.addActionListener(this);

        buttoncancel = new JButton("Cancel");
        buttoncancel.setFont(new Font("Raleway", Font.BOLD, 14));


        add(additional_details);
        additional_details.setBounds(290, 20, 600, 30);

        add(education);
        education.setBounds(100, 70, 200, 30);
        add(comboBox_education);
        comboBox_education.setBounds(320, 70, 400, 30);


        add(occupation);
        occupation.setBounds(100, 120, 200, 30);
        add(comboBox_occupation);
        comboBox_occupation.setBounds(320, 120, 400, 30);

        add(citizenship_number);
        citizenship_number.setBounds(100, 170, 250, 30);
        add(textField_citizen);
        textField_citizen.setBounds(320, 170, 400, 30);

        add(senior_citizen);
        senior_citizen.setBounds(100, 220, 400, 30);
        add(radioButton_senior1);
        radioButton_senior1.setBounds(320, 220, 100, 30);
        add(radioButton_senior2);
        radioButton_senior2.setBounds(460, 220, 100, 30);

        add(existing_acc);
        existing_acc.setBounds(100, 270, 180, 30);
        radioButton_existing1.setBounds(320, 270, 100, 30);
        add(radioButton_existing1);
        radioButton_existing2.setBounds(460, 270, 100, 30);
        add(radioButton_existing2);

        add(account_type);
        account_type.setBounds(100, 320, 180, 30);
        add(comboBox_account);
        comboBox_account.setBounds(320, 320, 400, 30);

        add(card_number);
        card_number.setBounds(100, 370, 180, 30);
        add(card_details);
        card_details.setBounds(100, 390, 180, 30);
        add(d_card);
        d_card.setBounds(325, 370, 400, 30);

        add(pin_number);
        pin_number.setBounds(100, 420, 180, 30);
        add(pin_details);
        pin_details.setBounds(100, 440, 180, 30);
        add(p_card);
        p_card.setBounds(325, 420, 100, 30);


        add(service_required);
        service_required.setBounds(100, 490, 180, 30);

        add(checkBox_atm);
        checkBox_atm.setBounds(320, 490, 100, 30);
        add(checkBox_internet);
        checkBox_internet.setBounds(500, 490, 200, 30);

        add(checkBox_cheque);
        checkBox_cheque.setBounds(320, 510, 120, 30);
        add(checkBox_email);
        checkBox_email.setBounds(500, 510, 200, 30);

        add(checkBox_mobile);
        checkBox_mobile.setBounds(320, 530, 130, 30);
        add(checkBox_statement);
        checkBox_statement.setBounds(500, 530, 200, 30);


        add(checkBox_click);
        checkBox_click.setBounds(100, 640, 600, 30);

        add(buttonsumit);
        buttonsumit.setBounds(250, 720, 100, 30);
        buttonsumit.addActionListener(this);

        add(buttoncancel);
        buttoncancel.setBounds(420, 720, 100, 30);
        buttoncancel.addActionListener(this);


        setLayout(null);
        setSize(850, 800);
        setVisible(true);
        setTitle("Signup Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //new SignupTwo("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected JRadioButton value
        String selectedOption1 = radioButton_senior1.isSelected() ? radioButton_senior1.getText() : radioButton_senior2.getText();
        // Get the selected JRadioButton value
        String selectedOption2 = radioButton_existing1.isSelected() ? radioButton_existing1.getText() : radioButton_existing2.getText();
        // Iterate over the JCheckBoxes and check which ones are selected
        StringBuilder sb = new StringBuilder();
        if (checkBox_atm.isSelected()) {
            sb.append(checkBox_atm.getText()).append(",");
        }
        if (checkBox_cheque.isSelected()) {
            sb.append(checkBox_cheque.getText()).append(",");
        }
        if (checkBox_mobile.isSelected()) {
            sb.append(checkBox_mobile.getText()).append(",");
        }
        if (checkBox_internet.isSelected()) {
            sb.append(checkBox_internet.getText()).append(",");
        }
        if (checkBox_email.isSelected()) {
            sb.append(checkBox_email.getText()).append(",");
        }
        if (checkBox_statement.isSelected()) {
            sb.append(checkBox_statement.getText()).append(",");
        }
            // Remove the last comma from the StringBuilder object
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            // Convert the StringBuilder object to a String
            String options = sb.toString();

            if (e.getSource() == buttonsumit) {
                try {
                    String query1="insert into Signup2 values(?,?,?,?,?,?,?,?,?,?)";
                    pstmt1 = dbcon.con.prepareStatement(query1);
                    pstmt1.setString(1, form_number);
                    pstmt1.setString(2, comboBox_education.getSelectedItem().toString());
                    pstmt1.setString(3, comboBox_occupation.getSelectedItem().toString());
                    pstmt1.setString(4, textField_citizen.getText());
                    pstmt1.setString(5, selectedOption1);
                    pstmt1.setString(6, selectedOption2);
                    pstmt1.setString(7, comboBox_account.getSelectedItem().toString());
                    pstmt1.setString(8, cardno);
                    pstmt1.setString(9, atm_pin);
                    pstmt1.setString(10, options);

                    int result1 = pstmt1.executeUpdate();

                    String query2="insert into Login values(?,?,?)";
                    pstmt2= dbcon.con.prepareStatement(query2);
                    pstmt2.setString(1,form_number);
                    pstmt2.setString(2,cardno);
                    pstmt2.setString(3,atm_pin);

                    int result2= pstmt2.executeUpdate();

                    if (result1 > 0) {
                        JOptionPane.showMessageDialog(null, "Your Card Number is: "+cardno+"\n Pin Number is: "+atm_pin);
                        new Login();

                    } else {
                        JOptionPane.showMessageDialog(null, "Fill all the required fields");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            else  if(e.getSource()==buttoncancel)
            {
                new Login();
            }
        }
    }
