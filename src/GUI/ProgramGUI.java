package GUI;

import Algorithm.PenaltyRule;
import Algorithm.ReferenceCodeRule;
import DataStructure.Exceptions.*;
import DataStructure.*;
import GUI.Panels.*;

import javax.swing.*;
import java.awt.*;

/**
 * The main GUI for the program.
 */
public class ProgramGUI extends JFrame {
    private final WelcomePanel welcomePanel;
    private final CheckInPanel checkInPanel;
    private final SuccessPanel successPanel;
    private final FailPanel failPanel;
    private final AlreadyCheckedInPanel alreadyCheckedInPanel;
    private final BaggageNoticePanel baggageNoticePanel;
    private final BaggageCheckPanel baggageCheckPanel;
    private final ShowPenaltyPanel showPenaltyPanel;
    private final FlightInquiryPanel flightInquiryPanel;
    private PassengerList passengerList;
    private FlightList flightList;
    private FlightDetailsList flightDetailsList;
    private Passenger passenger;
    private Flight flight;
    public static final int SCREEN_WIDTH = 400;
    public static final int SCREEN_HEIGHT = 400;

    public ProgramGUI(PassengerList passengerList, FlightList flightList, FlightDetailsList flightDetailsList) {
        this.passengerList = passengerList;
        this.flightList = flightList;
        this.flightDetailsList = flightDetailsList;
        setTitle("Airport System");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        int x = (width - SCREEN_WIDTH) / 2;
        int y = (height - SCREEN_HEIGHT) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        JPanel cardPanel = new JPanel(new CardLayout());
        CardLayout cl = (CardLayout) cardPanel.getLayout();

        welcomePanel = new WelcomePanel();
        checkInPanel = new CheckInPanel();
        successPanel = new SuccessPanel();
        failPanel = new FailPanel();
        alreadyCheckedInPanel = new AlreadyCheckedInPanel();
        baggageNoticePanel = new BaggageNoticePanel();
        baggageCheckPanel = new BaggageCheckPanel();
        showPenaltyPanel = new ShowPenaltyPanel();
        flightInquiryPanel = new FlightInquiryPanel();

        cardPanel.add(welcomePanel, "Welcome");
        cardPanel.add(checkInPanel, "CheckIn");
        cardPanel.add(successPanel, "Success");
        cardPanel.add(failPanel, "Fail");
        cardPanel.add(alreadyCheckedInPanel, "AlreadyCheckedIn");
        cardPanel.add(baggageNoticePanel, "BaggageNotice");
        cardPanel.add(baggageCheckPanel, "BaggageCheck");
        cardPanel.add(showPenaltyPanel, "ShowPenalty");
        cardPanel.add(flightInquiryPanel, "FlightInquiry");

        welcomePanel.getCheckInButton().addActionListener(e -> {
            checkInPanel.clearTextFields();
            cl.show(cardPanel, "CheckIn");
        });

        welcomePanel.getFlightInquiryButton().addActionListener(e -> {
            cl.show(cardPanel, "FlightInquiry");
        });

        checkInPanel.getQuitButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });

        checkInPanel.getSubmitButton().addActionListener(e -> {
            String referenceNumber = checkInPanel.getReferenceNumberField().getText();
            String lastName = checkInPanel.getLastNameField().getText();
            if(!ReferenceCodeRule.checkReferenceCodeValidity(referenceNumber)){
                JOptionPane.showMessageDialog(checkInPanel, "Invalid reference code!");
                return;
            }

            int status = passengerList.checkIn(referenceNumber, lastName);
            if (status == PassengerList.PASSENGER_NOT_FOUND) {
                cl.show(cardPanel, "Fail");
            } else if (status == PassengerList.PASSENGER_NOT_CHECKED_IN) {
                passenger = passengerList.getPassenger(referenceNumber);
                flight = flightList.findByFlightCode(passenger.getFlightCode());
                baggageCheckPanel.setBaggageLimit(flight.getBaggageLimit());
                cl.show(cardPanel, "BaggageNotice");
//                cl.show(cardPanel, "Success");
            } else if (status == PassengerList.PASSENGER_CHECKED_IN) {
                cl.show(cardPanel, "AlreadyCheckedIn");
            }
        });

        successPanel.getQuitButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });

        failPanel.getQuitButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });

        failPanel.getReenterButton().addActionListener(e -> {
            checkInPanel.clearTextFields();
            cl.show(cardPanel, "CheckIn");
        });

        alreadyCheckedInPanel.getQuitButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });
        baggageNoticePanel.getQuitButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });
        baggageNoticePanel.getNextButton().addActionListener(e -> {
            baggageCheckPanel.clearTextFields();
            baggageCheckPanel.updateJLabels();
            cl.show(cardPanel, "BaggageCheck");
        });
        baggageCheckPanel.getBackButton().addActionListener(e -> {
            cl.show(cardPanel, "BaggageNotice");
        });
        baggageCheckPanel.getSubmitButton().addActionListener(e -> {
            try {
                //Integer.ParseInt throws NumberFormatException
                //getBaggage() throws WrongBaggageSizeFormatException
                passenger.setBaggage(baggageCheckPanel.getBaggage());
                passenger.setPenalty(PenaltyRule.calculateExcessPenalty(flight, passenger.getBaggage()));
                flightDetailsList.updateDetails(passenger);
                showPenaltyPanel.updateTexts(passenger.getPenalty());
                cl.show(cardPanel, "ShowPenalty");
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(baggageCheckPanel, "Invalid input!");
            } catch (WrongBaggageSizeFormatException wbs) {
                JOptionPane.showMessageDialog(baggageCheckPanel, wbs.getMessage());
            }
        });
        showPenaltyPanel.getPayButton().addActionListener(e -> {

            passenger.setCheckedIn(true);

            cl.show(cardPanel, "Success");
        });
        showPenaltyPanel.getBackButton().addActionListener(e -> {
            cl.show(cardPanel, "BaggageCheck");
        });
        flightInquiryPanel.getBackButton().addActionListener(e -> {
            cl.show(cardPanel, "Welcome");
        });
        flightInquiryPanel.getListByFlight().addActionListener(e -> {
            flightInquiryPanel.getDisplayList().setText(flightList.listByFlightCode());
        });
        flightInquiryPanel.getListByDestination().addActionListener(e -> {
            flightInquiryPanel.getDisplayList().setText(flightList.listByAirline());
        });
        flightInquiryPanel.getSearchButton().addActionListener(e -> {
            String flightCode = flightInquiryPanel.getSearchField().getText();
            try {
                Flight flight = flightList.findByFlightCode(flightCode);
                flightInquiryPanel.getResult().setText(String.format("%s  %s  %s", flight.getFlightCode(), flight.getDestination(), flight.getCarrier()));
            } catch (FlightNotFoundException ex) {
                flightInquiryPanel.getResult().setText("Flight not found!");
            }
        });

        add(cardPanel);
        setResizable(false);
    }
}
