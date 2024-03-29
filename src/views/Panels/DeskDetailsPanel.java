package views.Panels;

import controllers.Controllers;
import interfaces.Observer;
import model.DataStructure.Desk;
import model.DataStructure.DeskList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

import static views.Frames.ProgramGUI.titleFont;

public class DeskDetailsPanel extends JPanel implements Observer{
    private static DeskDetailsPanel instance = new DeskDetailsPanel();
    private final JPanel deskDetailsPanel;
    private JTextArea deskTextArea;
    private JLabel label;
    private DeskList deskList;
    private final ArrayList<DeskDetailsComponent> deskDetailsComponents;

    public DeskDetailsPanel() {
        deskDetailsComponents = new ArrayList<>();
        deskList = DeskList.getInstance();
        deskList.registerObserver(this);
        TitledBorder deskDetailsBorder = BorderFactory.createTitledBorder(null, "Counter Details", TitledBorder.CENTER, TitledBorder.DEFAULT_JUSTIFICATION, titleFont);
        setBorder(deskDetailsBorder);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        label = new JLabel();
        label.setText("  There are currently 0  desk(s) serving...    ");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel p = new JPanel(new BorderLayout());
        p.add(label, BorderLayout.CENTER);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        add(p);

        deskDetailsPanel = new JPanel();
        deskDetailsPanel.setLayout(new BoxLayout(deskDetailsPanel, BoxLayout.PAGE_AXIS)); // set layout
        JScrollPane scrollPane = new JScrollPane(deskDetailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    public static DeskDetailsPanel getInstance() {
        return instance;
    }

    @Override
    public void update() {
        label.setText(String.format(" There are currently %-2d desk(s) serving... ", deskList.size()));
        if(deskDetailsComponents.size() + 1 == deskList.size()) { // if a new desk is added
            Desk desk = deskList.getArrayList().getLast();
            DeskDetailsComponent deskDetailsComponent = new DeskDetailsComponent(desk);
            if(Controllers.isRunning()){
                desk.resume();
            }
            deskDetailsPanel.add(deskDetailsComponent);
            deskDetailsComponents.add(deskDetailsComponent);
        } else if(deskDetailsComponents.size() - 1 == deskList.size()) { // if a desk is removed
            deskDetailsPanel.remove(deskDetailsComponents.getLast());
            deskDetailsComponents.removeLast();
        }
        deskDetailsPanel.revalidate();
        deskDetailsPanel.repaint();
    }

//    public synchronized void updateText() {
//        DeskList deskList = DeskList.getInstance();
//        label.setText(String.format(" There are currently %d  desk(s) serving... ", deskList.size()));
//        deskTextArea.setText(deskList.toString());
//    }

    private class DeskDetailsComponent extends JPanel implements Observer {
        private final JLabel deskLabel;
        private final Desk desk;
        public DeskDetailsComponent(Desk desk) {
            this.desk = desk;
            desk.registerObserver(this); // register as an observer
            // set layout
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // add label
            deskLabel = new JLabel(toHTML(desk.toString()));
            deskLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
            add(deskLabel);
            // set maximum size
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); //limit size to one row in scrollpanel
        }

        public String toHTML(String s) {
            String[] lines = s.split("\\n");
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(String.format("&nbsp;%s<br>", line));
            }
            return "<html>" + sb + "</html>";
        }
        @Override
        public void update() {
            deskLabel.setText(toHTML(desk.toString()));
        }
    }
}