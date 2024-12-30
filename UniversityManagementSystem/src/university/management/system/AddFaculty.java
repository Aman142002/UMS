package university.management.system;

import com.toedter.calendar.JDateChooser;
import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddFaculty extends JFrame implements ActionListener {

    JTextField textName, textfather, textAddress, textPhone, textemail, textM10, textM12, textAadhar;
    JLabel empText;
    JDateChooser cdob;
    JComboBox courseBox, departmentBox;
    JButton submit, cancel;

    Random ran = new Random();
    long f4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddFaculty() {
        getContentPane().setBackground(new Color(166,164,252));

        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        // Name
        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 100, 30);
        name.setFont(new Font("serif", Font.BOLD, 20));
        add(name);

        textName = new JTextField();
        textName.setBounds(200, 150, 150, 30);
        add(textName);

        // Father Name
        JLabel fname = new JLabel("Father Name");
        fname.setBounds(400, 150, 200, 30);
        fname.setFont(new Font("serif", Font.BOLD, 20));
        add(fname);

        textfather = new JTextField();
        textfather.setBounds(600, 150, 150, 30);
        add(textfather);

        // Employee ID
        JLabel empID = new JLabel("Employee ID");
        empID.setBounds(50, 200, 200, 30);
        empID.setFont(new Font("serif", Font.BOLD, 20));
        add(empID);

        empText = new JLabel("1409" + f4);
        empText.setBounds(200, 200, 150, 30);
        empText.setFont(new Font("serif", Font.BOLD, 20));
        add(empText);

        // Date of Birth
        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(400, 200, 200, 30);
        dob.setFont(new Font("serif", Font.BOLD, 20));
        add(dob);

        cdob = new JDateChooser();
        cdob.setBounds(600, 200, 150, 30);
        add(cdob);

        // Address
        JLabel address = new JLabel("Address");
        address.setBounds(50, 250, 200, 30);
        address.setFont(new Font("serif", Font.BOLD, 20));
        add(address);

        textAddress = new JTextField();
        textAddress.setBounds(200, 250, 150, 30);
        add(textAddress);

        // Phone
        JLabel phone = new JLabel("Phone");
        phone.setBounds(400, 250, 200, 30);
        phone.setFont(new Font("serif", Font.BOLD, 20));
        add(phone);

        textPhone = new JTextField();
        textPhone.setBounds(600, 250, 150, 30);
        add(textPhone);

        // Email
        JLabel email = new JLabel("Email");
        email.setBounds(50, 300, 200, 30);
        email.setFont(new Font("serif", Font.BOLD, 20));
        add(email);

        textemail = new JTextField();
        textemail.setBounds(200, 300, 150, 30);
        add(textemail);

        // Class X
        JLabel M10 = new JLabel("Class X (%)");
        M10.setBounds(400, 300, 200, 30);
        M10.setFont(new Font("serif", Font.BOLD, 20));
        add(M10);

        textM10 = new JTextField();
        textM10.setBounds(600, 300, 150, 30);
        add(textM10);

        // Class XII
        JLabel M12 = new JLabel("Class XII (%)");
        M12.setBounds(50, 350, 200, 30);
        M12.setFont(new Font("serif", Font.BOLD, 20));
        add(M12);

        textM12 = new JTextField();
        textM12.setBounds(200, 350, 150, 30);
        add(textM12);

        // Aadhar Number
        JLabel AadharNo = new JLabel("Aadhar Number");
        AadharNo.setBounds(400, 350, 200, 30);
        AadharNo.setFont(new Font("serif", Font.BOLD, 20));
        add(AadharNo);

        textAadhar = new JTextField();
        textAadhar.setBounds(600, 350, 150, 30);
        add(textAadhar);

        // Qualification
        JLabel Qualification = new JLabel("Qualification");
        Qualification.setBounds(50, 400, 200, 30);
        Qualification.setFont(new Font("serif", Font.BOLD, 20));
        add(Qualification);

        String course[] = {"B.Tech", "BBA", "BCA", "BSC", "MSC", "MBA", "MCA", "MCom", "MA", "BA"};
        courseBox = new JComboBox(course);
        courseBox.setBounds(200, 400, 150, 30);
        courseBox.setBackground(Color.WHITE);
        add(courseBox);

        // Department
        JLabel Department = new JLabel("Department");
        Department.setBounds(400, 400, 200, 30);
        Department.setFont(new Font("serif", Font.BOLD, 20));
        add(Department);

        String department[] = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT"};
        departmentBox = new JComboBox(department);
        departmentBox.setBounds(600, 400, 150, 30);
        departmentBox.setBackground(Color.WHITE);
        add(departmentBox);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);  // Set size and position
        submit.setBackground(Color.LIGHT_GRAY);  // Similar to 'back' button background color
        submit.setForeground(Color.BLACK);  // Similar text color
        submit.setFont(new Font("Arial", Font.BOLD, 14));  // Same font style and size
        submit.addActionListener(this);
        add(submit);


        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);  // Set size and position
        cancel.setBackground(Color.LIGHT_GRAY);  // Same background as submit and back button
        cancel.setForeground(Color.BLACK);  // Same text color
        cancel.setFont(new Font("Arial", Font.BOLD, 14));  // Same font style and size
        cancel.addActionListener(this);
        add(cancel);


        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = textName.getText();
            String fname = textfather.getText();
            String empid = empText.getText();
            String dob = ((JTextField) cdob.getDateEditor().getUiComponent()).getText();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textemail.getText();
            String x = textM10.getText();
            String xii = textM12.getText();
            String aadhar = textAadhar.getText();
            String course = (String) courseBox.getSelectedItem();
            String department = (String) departmentBox.getSelectedItem();
            try {
                String q = "insert into teacher values('" + name + "', '" + fname + "','" + empid + "','" + dob + "','" + address + "','" + phone + "','" + email + "','" + x + "','" + xii + "','" + aadhar + "','" + course + "','" + department + "')";
                Conn c = new Conn();
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Details Inserted");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddFaculty();
    }
}
