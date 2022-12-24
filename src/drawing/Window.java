package drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Display a window for user to test the network
 */
public class Window extends JFrame implements MouseListener {
    public static int UPS = 60;
    public Panel panel;
    Scanner scanner = new Scanner(System.in);
    JLabel title = new JLabel("Draw a number!");
    JPanel buttons = new JPanel();
    JButton clearBtn = new JButton("Clear");
    JButton checkBtn = new JButton("Check");
    JButton again = new JButton("Wrong?");
    JButton submit = new JButton("Submit");

    public Window() {
        panel = new Panel();
        init();
        loop();
    }

    private void init() {
        setUpTittle();
        setUpBtn();
        setVisible(true);
        setResizable(false);
        setTitle("Neural Network");
        setSize(Panel.CELL_SIZE * 28, Panel.CELL_SIZE * 28 + 160);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        addMouseMotionListener(panel);
        checkBtn.addMouseListener(this);
        clearBtn.addMouseListener(this);
        submit.addMouseListener(this);
        again.addMouseListener(this);
        add(title);
        add(panel);
        add(buttons);
        setLocationRelativeTo(null);
    }

    private void setUpTittle() {
        title.setFont(new Font("MV Boli", Font.BOLD, 20));
        title.setHorizontalAlignment(0);
        title.setBounds(0, 0, Panel.CELL_SIZE * 28, 80);
    }

    private void setUpBtn() {
        buttons.setLayout(new FlowLayout());
        buttons.setBounds(0, Panel.CELL_SIZE * 28 + 80, Panel.CELL_SIZE * 28, 40);
        buttons.setBackground(Color.green);
        buttons.add(checkBtn);
        buttons.add(clearBtn);
        buttons.add(submit);
        buttons.add(again);
    }

    private void loop() {
        long currentUpdate, lastUpdate = System.currentTimeMillis();
        double dt = 0;
        while (true) {
            currentUpdate = System.currentTimeMillis();
            dt += (currentUpdate - lastUpdate) / 1000d;
            lastUpdate = currentUpdate;

            if (dt >= 1.0d / UPS) {
                panel.update();
                panel.render();
                dt -= 1.0 / UPS;
            }
        }
    }

    public static void main(String[] args) {
        new Window();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Get the result from the network
        if (e.getSource() == checkBtn) {
            int result = panel.check();
            title.setText("You draw number: " + result);
            System.out.println("You draw number: " + result);
        }
        // Clear the board
        if (e.getSource() == clearBtn) {
            panel.clear();
            title.setText("Draw a number!");
        }

        // If the network guesses incorrectly, submit the right number
        if (e.getSource() == submit) {
            System.out.println("Wrong?");
            System.out.print("Enter expected number: ");
            int a = scanner.nextInt();
            again.setText(String.valueOf(a));
            scanner.nextLine();
            panel.learn(a);
            System.out.println("ok");
        }

        // Shortcut to submit the same number again
        if (e.getSource() == again) {
            int num = Integer.parseInt(again.getText());
            panel.learn(num);
            System.out.println("lomzom");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
