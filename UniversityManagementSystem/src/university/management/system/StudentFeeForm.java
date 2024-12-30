package university.management.system;

import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class StudentFeeForm extends JFrame implements ActionListener {

    Choice choicerollnoID;
    JComboBox<String> courseBox, departmentBox, semesterBox;
    JLabel totalAmount, textName, textfName;
    JButton pay, update, back;

    StudentFeeForm() {
        // Frame background
        getContentPane().setBackground(new Color(210, 252, 251));

        // Adding Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/fee.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400, 50, 500, 300);
        add(img);

        // Roll Number Label
        JLabel rollNumber = new JLabel("Select Roll Number");
        rollNumber.setBounds(40, 60, 150, 20);
        rollNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(rollNumber);

        // Roll Number Choice
        choicerollnoID = new Choice();
        choicerollnoID.setBounds(200, 60, 150, 20);
        add(choicerollnoID);

        // Fetch roll numbers from database
        try  {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery("select rollnoID from student");
            while (rs.next()) {
                choicerollnoID.add(rs.getString("rollnoID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Name Label
        JLabel name = new JLabel("Name");
        name.setBounds(40, 100, 150, 20);
        add(name);

        textName = new JLabel();
        textName.setBounds(200, 100, 150, 20);
        add(textName);

        // Father Name Label
        JLabel fname = new JLabel("Father Name");
        fname.setBounds(40, 140, 150, 20);
        add(fname);

        textfName = new JLabel();
        textfName.setBounds(200, 140, 150, 20);
        add(textfName);

        // Course Label
        JLabel courseLabel = new JLabel("Course");
        courseLabel.setBounds(40, 180, 150, 20);
        add(courseLabel);

        String[] course = {"BTech", "BBA", "BCA", "BSC", "MSC", "MBA", "MCA", "MCom", "MA", "BA"};
        courseBox = new JComboBox<>(course);
        courseBox.setBounds(200, 180, 150, 20);
        courseBox.setBackground(Color.WHITE);
        add(courseBox);

        // Department Label
        JLabel departmentLabel = new JLabel("Branch");
        departmentLabel.setBounds(40, 220, 150, 20);
        add(departmentLabel);

        String[] department = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT"};
        departmentBox = new JComboBox<>(department);
        departmentBox.setBounds(200, 220, 150, 20);
        departmentBox.setBackground(Color.WHITE);
        add(departmentBox);

        // Semester Label
        JLabel semesterLabel = new JLabel("Semester");
        semesterLabel.setBounds(40, 260, 150, 20);
        add(semesterLabel);

        String[] semester = {"semester1", "semester2", "semester3", "semester4", "semester5", "semester6", "semester7", "semester8"};
        semesterBox = new JComboBox<>(semester);
        semesterBox.setBounds(200, 260, 150, 20);
        semesterBox.setBackground(Color.WHITE);
        add(semesterBox);

        // Total Amount Label
        JLabel totalLabel = new JLabel("Total Payable");
        totalLabel.setBounds(40, 300, 150, 20);
        add(totalLabel);

        totalAmount = new JLabel();
        totalAmount.setBounds(200, 300, 150, 20);
        add(totalAmount);

        // Buttons
        update = new JButton("Update");
        update.setBounds(30, 380, 100, 25);
        update.addActionListener(this);
        add(update);

        pay = new JButton("Pay");
        pay.setBounds(150, 380, 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(270, 380, 100, 25);
        back.addActionListener(this);
        add(back);

        // Add item listener for roll number choice
        choicerollnoID.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                updateStudentDetails();
            }
        });

        // Frame settings
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(null);
        setVisible(true);

        // Initialize student details
        if (choicerollnoID.getItemCount() > 0) {
            updateStudentDetails();
        }
    }

    private void updateStudentDetails() {
        try  {
            Conn c = new Conn();
            String query = "select * from student where rollnoID = '" + choicerollnoID.getSelectedItem() + "'";
            ResultSet rs = c.statement.executeQuery(query);
            if (rs.next()) {
                textName.setText(rs.getString("name"));
                textfName.setText(rs.getString("fname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            String course = (String) courseBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            try  {
                Conn c = new Conn();
                ResultSet rs = c.statement.executeQuery("select * from fee where course = '" + course + "'");
                if (rs.next()) {
                    totalAmount.setText(rs.getString(semester));
                } else {
                    JOptionPane.showMessageDialog(null, "Fee details not found for the selected course.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == pay) {
            String rollno = choicerollnoID.getSelectedItem();
            String course = (String) courseBox.getSelectedItem();
            String branch = (String) departmentBox.getSelectedItem();
            String semester = (String) semesterBox.getSelectedItem();
            String total = totalAmount.getText();

            if (total.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please update the fee details first.");
                return;
            }

            try  {
                Conn c = new Conn();
                String query = "insert into feecollege values('" + rollno + "', '" + course + "', '" + branch + "', '" + semester + "', '" + total + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Fee submitted successfully.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
