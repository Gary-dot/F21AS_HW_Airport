package GUI.Panels;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for the Check In page.
 */
public class CheckInPanel extends JPanel {
    private JTextField referenceNumberField;
    private JTextField lastNameField;
    private JButton QuitButton;
    private JButton SubmitButton;

    public CheckInPanel() {
        setLayout(new GridLayout(5, 1));

        JLabel noticeLabel1 = new JLabel("Please enter your reference number:");
        noticeLabel1.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

        referenceNumberField = new JTextField(10);
        referenceNumberField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        referenceNumberField.setColumns(10);

        JLabel noticeLabel2 = new JLabel("Please enter your Lastname:");
        noticeLabel2.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

        lastNameField = new JTextField(10);
        lastNameField.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
        lastNameField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        QuitButton = new JButton("Quit");
        QuitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        SubmitButton = new JButton("Submit");
        SubmitButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        buttonPanel.add(QuitButton);
        buttonPanel.add(SubmitButton);

        add(noticeLabel1);
        add(referenceNumberField);
        add(noticeLabel2);
        add(lastNameField);
        add(buttonPanel);

    }

    public static void main(String[] args) {
        new TestFrame(new CheckInPanel()).setVisible(true);
    }

    public JButton getQuitButton() {
        return QuitButton;
    }

    public JButton getSubmitButton() {
        return SubmitButton;
    }

    public JTextField getReferenceNumberField() {
        return referenceNumberField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public void clearTextFields() {
        referenceNumberField.setText("");
        lastNameField.setText("");
    }
}
