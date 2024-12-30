package university.management.system;

import net.proteanit.sql.DbUtils;
import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class StudentDetails extends JFrame implements ActionListener {

    Choice choicerollnoID;
    JTable table;
    JButton search, print, update, add, cancel;

    StudentDetails() {
        getContentPane().setBackground(new Color(210, 252, 218));

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        choicerollnoID = new Choice();
        choicerollnoID.setBounds(180, 20, 150, 20);
        add(choicerollnoID);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while (resultSet.next()) {
                choicerollnoID.add(resultSet.getString("rollnoID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JScrollPane js = new JScrollPane(table);
        js.setBounds(0, 100, 900, 600);
        add(js);

        // Search Button
        search = new JButton("Search");
        search.setBounds(20, 70, 120, 30);
        search.setFont(new Font("Arial", Font.BOLD, 14));
        search.setBackground(Color.GRAY);
        search.setForeground(Color.BLACK);
        search.setFocusPainted(false);
        search.addActionListener(this);
        add(search);

        // Print Button
        print = new JButton("Print");
        print.setBounds(160, 70, 120, 30);
        print.setFont(new Font("Arial", Font.BOLD, 14));
        print.setBackground(Color.GRAY);
        print.setForeground(Color.BLACK);
        print.setFocusPainted(false);
        print.addActionListener(this);
        add(print);

        // Add Button
        add = new JButton("Add");
        add.setBounds(300, 70, 120, 30);
        add.setFont(new Font("Arial", Font.BOLD, 14));
        add.setBackground(Color.GRAY);
        add.setForeground(Color.BLACK);
        add.setFocusPainted(false);
        add.addActionListener(this);
        add(add);

        // Update Button
        update = new JButton("Update");
        update.setBounds(440, 70, 120, 30);
        update.setFont(new Font("Arial", Font.BOLD, 14));
        update.setBackground(Color.GRAY);
        update.setForeground(Color.BLACK);
        update.setFocusPainted(false);
        update.addActionListener(this);
        add(update);

        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(580, 70, 120, 30);
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setForeground(Color.BLACK);
        cancel.setFocusPainted(false);
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String selectedRollNo = choicerollnoID.getSelectedItem();
            if (selectedRollNo == null || selectedRollNo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a valid Roll Number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the query to match the correct column name (rollnoID)
            String query = "select * from student where rollnoID = '" + selectedRollNo + "'";
            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);

                // Check if the result set contains any rows
                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(this, "No student found with the selected Roll Number!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Display results if student(s) found
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error executing the query!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error printing the table!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == add) {
            setVisible(false);
            new AddStudent(); // Add Student action
        } else if (e.getSource() == update) {
            // Implement update functionality if needed
        } else if (e.getSource() == cancel) {
            setVisible(false); // Hide window on cancel
        }
    }




    public static void main(String[] args) {
        new StudentDetails();
    }
}
