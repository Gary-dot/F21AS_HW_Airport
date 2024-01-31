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

    public void clearTextFields() {
        referenceNumberField.setText("");
        lastNameField.setText("");
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
        testFrame.add(new CheckInPanel(), BorderLayout.CENTER);
        testFrame.setVisible(true);
//        testFrame.setResizable(false);
    }
}
