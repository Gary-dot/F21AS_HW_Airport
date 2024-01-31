package GUI.Panels;

import DataStructure.Baggage;

import javax.swing.*;
import java.awt.*;

public class BaggageCheckPanel extends JPanel {
    private final JLabel l1;
    private JTextField baggageLimitWidthField;
    private JTextField baggageLimitLengthField;
    private JTextField baggageLimitHeightField;
    private JTextField baggageLimitWeightField;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    //    private JTextField hintField;
    private JButton submitButton;
    private JButton backButton;
    private Baggage baggageLimit;

    public BaggageCheckPanel() {
        setLayout(null);
        baggageLimit = new Baggage(0, 0, 0, 0);
        l1 = new JLabel("Please enter your baggageLimit details:");
        l1.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        l1.setBounds(20, 0, 400, 100);

        l2 = new JLabel(String.format("Baggage length: (Limit: %d in)", baggageLimit.getLength()));
        l2.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        l2.setBounds(20, 60, 250, 80);

        l3 = new JLabel(String.format("Baggage width: (Limit: %d in)", baggageLimit.getWidth()));
        l3.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        l3.setBounds(20, 110, 250, 80);

        l4 = new JLabel(String.format("Baggage height: (Limit: %d in)", baggageLimit.getHeight()));
        l4.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        l4.setBounds(20, 160, 250, 80);

        l5 = new JLabel(String.format("Baggage weight: (Limit: %d lbs)", baggageLimit.getWeight()));
        l5.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        l5.setBounds(20, 210, 250, 80);

        baggageLimitLengthField = new JTextField(3);
        baggageLimitLengthField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        baggageLimitLengthField.setBounds(300, 90, 50, 30);

        baggageLimitWidthField = new JTextField(3);
        baggageLimitWidthField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        baggageLimitWidthField.setBounds(300, 140, 50, 30);

        baggageLimitHeightField = new JTextField(3);
        baggageLimitHeightField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        baggageLimitHeightField.setBounds(300, 190, 50, 30);

        baggageLimitWeightField = new JTextField(3);
        baggageLimitWeightField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        baggageLimitWeightField.setBounds(300, 240, 50, 30);

//        hintField = new JTextField(10);
//        hintField.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
//        hintField.setForeground(Color.RED);
//        hintField.setHorizontalAlignment(JTextField.CENTER);
//        hintField.setText("Invalid input!");
//        hintField.setEditable(false);
//        hintField.setBounds(90, 280, 200, 30);

        submitButton = new JButton("Next");
        submitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        submitButton.setBounds(210, 320, 180, 40);
        backButton = new JButton("Back");
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        backButton.setBounds(0, 320, 180, 40);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(baggageLimitWidthField);
        add(baggageLimitLengthField);
        add(baggageLimitHeightField);
        add(baggageLimitWeightField);
//        add(hintField);
        add(submitButton);
        add(backButton);
    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setTitle("Airport System");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - 400) / 2;
        int y = (height - 400) / 2;
        testFrame.setLocation(x, y);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(400, 400);
        BaggageCheckPanel baggageCheckPanel = new BaggageCheckPanel();
        testFrame.add(baggageCheckPanel);
        testFrame.setVisible(true);
        testFrame.setResizable(false);
        baggageCheckPanel.getSubmitButton().addActionListener(e -> {
            try {
                baggageCheckPanel.getBaggage();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(testFrame, "Invalid input!");
            }
        });
    }

    public void setBaggageLimit(Baggage baggageLimit) {
        this.baggageLimit = baggageLimit;
    }

    public Baggage getBaggage() throws NumberFormatException {
        int length = Integer.parseInt(baggageLimitLengthField.getText().trim());
        int width = Integer.parseInt(baggageLimitWidthField.getText().trim());
        int height = Integer.parseInt(baggageLimitHeightField.getText().trim());
        int weight = Integer.parseInt(baggageLimitWeightField.getText().trim());
        return new Baggage(length, width, height, weight);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void clearTextFields() {
        baggageLimitWidthField.setText("");
        baggageLimitLengthField.setText("");
        baggageLimitHeightField.setText("");
        baggageLimitWeightField.setText("");
    }

    public void updateJLabels() {
        l2.setText(String.format("Baggage length: (Limit: %d in)", baggageLimit.getLength()));
        l3.setText(String.format("Baggage width: (Limit: %d in)", baggageLimit.getWidth()));
        l4.setText(String.format("Baggage height: (Limit: %d in)", baggageLimit.getHeight()));
        l5.setText(String.format("Baggage weight: (Limit: %d lbs)", baggageLimit.getWeight()));
    }
}
