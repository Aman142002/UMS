package university.management.system;

import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class updateStudent extends JFrame implements ActionListener {
    JTextField textAddress, textPhone, textemail, textAadhar, textcourse, textbranch;
    JLabel textName, textfather, dobdob, textM10, textM12, empText;

    JButton submit, cancel;
    Choice choicerollnoID;

    updateStudent() {
        getContentPane().setBackground(new Color(230, 210, 252));

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 35));
        add(heading);

        JLabel empID = new JLabel("Select Roll Number");
        empID.setBounds(50, 100, 200, 20);
        empID.setFont(new Font("serif", Font.PLAIN, 20));
        add(empID);

        choicerollnoID = new Choice();
        choicerollnoID.setBounds(250, 100, 200, 20);
        add(choicerollnoID);

        // Populate roll numbers
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("SELECT rollnoID FROM student");
            while (rs.next()) {
                choicerollnoID.add(rs.getString("rollnoID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(50, 150, 100, 30);
        name.setFont(new Font("serif", Font.BOLD, 20));
        add(name);

        textName = new JLabel();
        textName.setBounds(200, 150, 150, 30);
        add(textName);

        JLabel fname = new JLabel("Father Name");
        fname.setBounds(400, 150, 200, 30);
        fname.setFont(new Font("serif", Font.BOLD, 20));
        add(fname);

        textfather = new JLabel();
        textfather.setBounds(600, 150, 150, 30);
        add(textfather);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(400, 200, 200, 30);
        dob.setFont(new Font("serif", Font.BOLD, 20));
        add(dob);

        dobdob = new JLabel();
        dobdob.setBounds(600, 200, 150, 30);
        add(dobdob);

        JLabel address = new JLabel("Address");
        address.setBounds(50,250,200,30);
        address.setFont(new Font("serif",Font.BOLD,20));
        add(address);

        textAddress = new JTextField();
        textAddress.setBounds(200,250,150,30);
        add(textAddress);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(400,250,200,30);
        phone.setFont(new Font("serif",Font.BOLD,20));
        add(phone);

        textPhone = new JTextField();
        textPhone.setBounds(600,250,150,30);
        add(textPhone);

        JLabel email = new JLabel("Email");
        email.setBounds(50,300,200,30);
        email.setFont(new Font("serif",Font.BOLD,20));
        add(email);

        textemail = new JTextField();
        textemail.setBounds(200,300,150,30);
        add(textemail);

        JLabel M10 = new JLabel("Class X (%)");
        M10.setBounds(400,300,200,30);
        M10.setFont(new Font("serif",Font.BOLD,20));
        add(M10);

        JLabel textM10 = new JLabel();
        textM10.setBounds(600,300,150,30);
        add(textM10);

        JLabel M12 = new JLabel("Class XII (%)");
        M12.setBounds(50,350,200,30);
        M12.setFont(new Font("serif",Font.BOLD,20));
        add(M12);

        JLabel textM12 = new JLabel();
        textM12.setBounds(200,350,150,30);
        add(textM12);

        JLabel AadharNo = new JLabel("Aadhar Number");
        AadharNo.setBounds(400,350,200,30);
        AadharNo.setFont(new Font("serif",Font.BOLD,20));
        add(AadharNo);

        textAadhar = new JTextField();
        textAadhar.setBounds(600,350,150,30);
        add(textAadhar);

        JLabel Qualification = new JLabel("Course");
        Qualification.setBounds(50,400,200,30);
        Qualification.setFont(new Font("serif",Font.BOLD,20));
        add(Qualification);

        textcourse = new JTextField();
        textcourse.setBounds(200,400,150,30);
        add(textcourse);


        JLabel Department = new JLabel("Branch");
        Department.setBounds(400,400,200,30);
        Department.setFont(new Font("serif",Font.BOLD,20));
        add(Department);

        textbranch = new JTextField();
        textbranch.setBounds(600,400,150,30);
        add(textbranch);

        try{
            Conn c = new Conn();
            String query = "select * from student where rollno = '"+choicerollnoID.getSelectedItem()+"'";
            ResultSet resultSet = c.statement.executeQuery(query);
            while (resultSet.next()){
                textName.setText(resultSet.getString("name"));
                textfather.setText(resultSet.getString("fname"));
                dobdob.setText(resultSet.getString("dob"));
                textAddress.setText(resultSet.getString("address"));
                textPhone.setText(resultSet.getString("phone"));
                textemail.setText(resultSet.getString("email"));
                textM10.setText(resultSet.getString("class_X"));
                textM12.setText(resultSet.getString("class_XII"));
                textAadhar.setText(resultSet.getString("aadhar"));
                empText.setText(resultSet.getString("rollno"));
                textcourse.setText(resultSet.getString("course"));
                textbranch.setText(resultSet.getString("branch"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        choicerollnoID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where rollno = '"+choicerollnoID.getSelectedItem()+"'";
                    ResultSet resultSet = c.statement.executeQuery(query);
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("name"));
                        textfather.setText(resultSet.getString("fname"));
                        dobdob.setText(resultSet.getString("dob"));
                        textAddress.setText(resultSet.getString("address"));
                        textPhone.setText(resultSet.getString("phone"));
                        textemail.setText(resultSet.getString("email"));
                        textM10.setText(resultSet.getString("class_X"));
                        textM12.setText(resultSet.getString("class_XII"));
                        textAadhar.setText(resultSet.getString("aadhar"));
                        empText.setText(resultSet.getString("rollnoID"));
                        textcourse.setText(resultSet.getString("course"));
                        textbranch.setText(resultSet.getString("branch"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        // Repeat for all labels and fields
        // Skipping repetitive UI component code for brevity
        // ...

        submit = new JButton("Update");
        submit.setBounds(250, 550, 140, 40); // Slightly increased the size for better interaction
        submit.setBackground(Color.BLACK); // Black background for contrast
        submit.setForeground(Color.WHITE); // White text for visibility
        submit.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font for better readability
        submit.setFocusPainted(false); // Remove default focus border for a cleaner look
        submit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Adds a border to make it stand out
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand to indicate interactivity
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 140, 40); // Same size as 'submit' for consistency
        cancel.setBackground(Color.BLACK); // Black background for consistency with 'submit'
        cancel.setForeground(Color.WHITE); // White text for readability
        cancel.setFont(new Font("Tahoma", Font.BOLD, 16)); // Bold and larger font for better readability
        cancel.setFocusPainted(false); // Remove default focus border for a cleaner look
        cancel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Adds a border to make it stand out
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand to indicate interactivity
        cancel.addActionListener(this);
        add(cancel);


        choicerollnoID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadStudentDetails(choicerollnoID.getSelectedItem());
            }
        });

        // Load initial data
        if (choicerollnoID.getItemCount() > 0) {
            loadStudentDetails(choicerollnoID.getSelectedItem());
        }

        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
        setVisible(true);
    }

    private void loadStudentDetails(String rollnoID) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM student WHERE rollnoID = '" + rollnoID + "'";
            ResultSet rs = c.statement.executeQuery(query);

            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textfather.setText(rs.getString("fname"));
                dobdob.setText(rs.getString("dob"));
                textAddress.setText(rs.getString("address"));
                textPhone.setText(rs.getString("phone"));
                textemail.setText(rs.getString("email"));
                textM10.setText(rs.getString("class_X"));
                textM12.setText(rs.getString("class_XII"));
                textAadhar.setText(rs.getString("aadhar"));
                empText.setText(rs.getString("rollnoID"));
                textcourse.setText(rs.getString("course"));
                textbranch.setText(rs.getString("branch"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String empid = empText.getText();
            String address = textAddress.getText();
            String phone = textPhone.getText();
            String email = textemail.getText();
            String course = textcourse.getText();
            String branch = textbranch.getText();

            try {
                String query = "UPDATE student SET address = '" + address + "', phone = '" + phone + "', email = '" + email + "', course = '" + course + "', branch = '" + branch + "' WHERE rollnoID = '" + empid + "'";
                Conn c = new Conn();
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Details Updated");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new updateStudent();
    }
}
