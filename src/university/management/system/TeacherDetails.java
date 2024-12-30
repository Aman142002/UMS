package university.management.system;

import net.proteanit.sql.DbUtils;
import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice choice;
    JTable table;
    JButton search, print, update, add, cancel;

    TeacherDetails() {
        getContentPane().setBackground(new Color(192, 164, 252));

        JLabel heading = new JLabel("Search by Employee ID");
        heading.setBounds(20, 20, 200, 20);
        heading.setFont(new Font("serif", Font.BOLD, 18));
        add(heading);

        choice = new Choice();

        choice.setBounds(180,20,150,20);
        add(choice);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
            while (resultSet.next()){
                choice.add(resultSet.getString("empID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }
        JScrollPane js = new JScrollPane(table);
        js.setBounds(0,100,900,600);
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
            String selectedEmpId = choice.getSelectedItem();

            // Ensure valid selection
            if (selectedEmpId == null || selectedEmpId.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a valid Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Query to search teacher by empID
            String query = "SELECT * FROM teacher WHERE empID = '" + selectedEmpId + "'";

            try {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(query);

                // Check if resultSet has data
                if (resultSet.next()) {
                    // Log successful query execution
                    System.out.println("Teacher found: " + resultSet.getString("empID"));
                    table.setModel(DbUtils.resultSetToTableModel(resultSet)); // Set table model to the query result
                } else {
                    JOptionPane.showMessageDialog(this, "No teacher found with this Employee ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    // Optionally, reload the table with all records if no match is found
                    reloadTable();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error searching for teacher.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error printing the table.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == add) {
            setVisible(false);
            new AddFaculty(); // Opens Add Faculty Form
        } else if (e.getSource() == update) {
            // Implement update functionality here
            JOptionPane.showMessageDialog(this, "Update functionality not implemented yet.", "Not Implemented", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == cancel) {
            setVisible(false); // Cancels and closes the window
        }
    }

    // Reload the table with all teachers (after search or if no results are found)
    private void reloadTable() {
        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM teacher");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reloading teacher data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
