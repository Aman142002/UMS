package university.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    Thread t;

    Splash() {
        // Load and scale the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        add(img);

        // Set frame properties
        setUndecorated(true); // Remove the title bar
        setSize(1000, 700);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        // Start the thread
        t = new Thread(this);
        t.start();

        // Animate the window size and location
        animateSplashScreen();
    }

    private void animateSplashScreen() {
        int x = 1;
        for (int i = 2; i <= 600; i += 4, x += 1) {
            setLocation(600 - ((i + x) / 2), 350 - (i / 2));
            setSize(i + 3 * x, i + x / 2); // Adjusted height calculation for proper scaling
            try {
                Thread.sleep(10); // Add delay for smooth animation
            } catch (InterruptedException e) {
                System.err.println("Animation interrupted: " + e.getMessage());
            }
        }
    }

    public void run() {
        try {
            Thread.sleep(7000); // Keep the splash screen visible for 7 seconds
            setVisible(false);
            new university.management.system.Login(); // Transition to the Login screen
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
