package university.management.system;

import net.proteanit.sql.DbUtils;
import university.management.Conn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class ExaminationDetails extends JFrame implements ActionListener {

    JTextField search;
    JButton result, back;
    JTable table;
    ExaminationDetails(){

        getContentPane().setBackground(new Color(241,252,210));

        JLabel heading = new JLabel("check Result");
        heading.setBounds(350,15,400,50);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        add(heading);

        search = new JTextField();
        search.setBounds(20, 70, 200, 30); // Adjusted to align with new button placement
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        result = new JButton("Result");
        result.setBounds(240, 70, 80, 20); // Matches size and placement with other buttons
        result.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Smaller, consistent font
        result.addActionListener(this);
        add(result);

        back = new JButton("Back");
        back.setBounds(340, 70, 80, 20); // Matches size and placement with other buttons
        back.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Smaller, consistent font
        back.addActionListener(this);
        add(back);


        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,130,1000,310);
        add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row,2).toString());
            }
        });


        setSize(1000,475);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == result){
            setVisible(false);
            new Marks(search.getText());
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ExaminationDetails();
    }
}