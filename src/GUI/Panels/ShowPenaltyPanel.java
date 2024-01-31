package GUI.Panels;
import javax.swing.*;
import java.awt.*;

public class ShowPenaltyPanel extends JPanel{
    JLabel notice;
    private JButton payButton;
    private JButton backButton;
    private int[] penalty;
    public ShowPenaltyPanel(){
        setLayout(null);
        penalty = new int[]{0, 0, 0};
        notice = new JLabel(String.format("<html>You will be charged %d£ for your baggage size,<br>" +
                "and %d£ for your baggage weight.<br><br><br><br>" +
                "You will be charged a total extra fee of %d£.<br>" +
                "</html>", penalty[0], penalty[1], penalty[2]) );
        notice.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        notice.setBounds(20, 0, 400, 300);

        payButton = new JButton(String.format("Pay %d£", penalty[2]));
        payButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        payButton.setBounds(210, 300, 180, 60);
        backButton = new JButton("Back");
        backButton.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
        backButton.setBounds(0, 300, 180, 60);

        add(notice);
        add(payButton);
        add(backButton);
    }

    public JButton getPayButton() {
        return payButton;
    }
    public void setPenalty(int[] penalty) {
        this.penalty = penalty;
    }
    public JButton getBackButton() {
        return backButton;
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
        testFrame.add(new ShowPenaltyPanel());
        testFrame.setVisible(true);
        testFrame.setResizable(false);
    }

    public void updateTexts(int[] penalty) {
        notice.setText(String.format("<html>You will be charged %d£ for your baggage size,<br>" +
                "and %d£ for your baggage weight.<br><br><br><br>" +
                "You will be charged a total extra fee of %d£.<br>" +
                "</html>", penalty[0], penalty[1], penalty[2]) );
        payButton.setText(String.format("Pay %d£", penalty[2]));
    }
}
