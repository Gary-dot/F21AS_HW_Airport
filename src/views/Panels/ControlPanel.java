package views.Panels;

import views.Frames.ProgramGUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static final ControlPanel instance = new ControlPanel();
    private final JButton speedButton;
    private final JButton pauseButton;
    private final JButton exitButton;
    private final JButton toolButton;
    public static ControlPanel getInstance() {
        return instance;
    }
    private ControlPanel(){
        setBorder(BorderFactory.createTitledBorder(null, "Control Panel", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, ProgramGUI.titleFont));
        setLayout(new GridLayout(1,4, 30, 30));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        speedButton = new JButton("  x1  ");
        pauseButton = new JButton("Pause");
        toolButton = new JButton("Tools");
        exitButton = new JButton("Quit");
        speedButton.setMaximumSize(new Dimension(60, 30));
        pauseButton.setMaximumSize(new Dimension(60, 30));
        toolButton.setMaximumSize(new Dimension(60, 30));
        exitButton.setMaximumSize(new Dimension(60, 30));
        add(speedButton);
        add(pauseButton);
        add(toolButton);
        add(exitButton);
    }
    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSpeedButton() {
        return speedButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
    public JButton getToolButton() {
        return toolButton;
    }
}
