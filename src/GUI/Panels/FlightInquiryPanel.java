package GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class FlightInquiryPanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JTextField result;
    private JButton listByFlight;
    //    private JButton listByAirline;
    private JButton listByDestination;
    private JButton backButton;
    private JTextArea displayList;

    public FlightInquiryPanel() {
        setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        //searchButton panel contains label, text field and button
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1, 3));
        searchPanel.add(new JLabel("Enter Flight Code"));
        searchField = new JTextField();
        searchPanel.add(searchField);
        searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        //Set up the area where the results will be displayed.
        result = new JTextField();
        result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        result.setEditable(false);
        result.setHorizontalAlignment(SwingConstants.LEFT);

        //set up south panel containing 2 previous areas
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(result);
        southPanel.add(searchPanel);


        //add north panel containing some buttons
        JPanel northPanel = new JPanel(new GridLayout(1, 4));
        listByFlight = new JButton("List By Flight");
//        listByAirline = new JButton("List By Airline");
        listByDestination = new JButton("List By Destination");
        backButton = new JButton("Back");
//        northPanel.add(backButton);
        northPanel.add(listByFlight);
//        northPanel.add(listByAirline);
        northPanel.add(listByDestination);
        northPanel.add(backButton);

        displayList = new JTextArea(15, 20);
        displayList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        displayList.setEditable(false);
        JScrollPane scrollList = new JScrollPane(displayList);

        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
        add(scrollList, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        new TestFrame(new FlightInquiryPanel()).setVisible(true);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JTextField getResult() {
        return result;
    }

    public void setResult(JTextField result) {
        this.result = result;
    }

//    public JButton getListByAirline() {
//        return listByAirline;
//    }

//    public void setListByAirline(JButton listByAirline) {
//        this.listByAirline = listByAirline;
//    }

    public JButton getListByFlight() {
        return listByFlight;
    }

    public void setListByFlight(JButton listByFlight) {
        this.listByFlight = listByFlight;
    }

    public JButton getListByDestination() {
        return listByDestination;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JTextArea getDisplayList() {
        return displayList;
    }

    public void setDisplayList(JTextArea displayList) {
        this.displayList = displayList;
    }

}
