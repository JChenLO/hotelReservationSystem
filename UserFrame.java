import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

/**
 GUI for guest, with make reservation and view cancel function
 */
public class UserFrame
{
   private Guest guest;
   private Hotel hotel;
   private int transactionID;
    UserFrame(Hotel h, Guest g)
    {
        hotel = h;
        guest = g;
        final Random rand = new Random();
        final JFrame frame = new JFrame();
        frame.setTitle("MaGeC Hotel User Interface");
        frame.setSize(600,200);
        
        //title
        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Welcome to MaGeC hotel: " + guest.getName());
        label.setFont(new Font("Serif", Font.PLAIN, 18));
        northPanel.add(label);   
        
        //buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        JButton makeReservationButton = new JButton("Make a Reservation");
        makeReservationButton.setFont(new Font("Serif", Font.PLAIN, 20));
        makeReservationButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

               transactionID = rand.nextInt();
               new ReservationFrame(hotel, guest,transactionID);
                /*Make a Reservationâ the guest should be able to select the check in date, check out 
                date, and the type of room. The start or end date/time should not be prior to the current date.
                The length of stay cannot be longer than 60 nights. Your system enforces these constraints and 
                generates an error for any violation. */
                
            }
        });
        JButton viewOrCancelButton = new JButton("View/Cancel a Reservation");

        viewOrCancelButton.setFont(new Font("Serif", Font.PLAIN, 20));
        viewOrCancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               new ViewCancelReservationFrame(hotel, guest);
                /* View/Cancel a reservationâ the system displays all reservations made by this guest.
                (When the guest asks for the view/cancel, he/she already logged in and thus the system does not 
                have to ask the id.) The guest may just view the reservations or select and cancel reservations. */
                
            }
        });
        bottomPanel.add(makeReservationButton);
        bottomPanel.add(viewOrCancelButton);
        
        //finished frame
        frame.add(northPanel, BorderLayout.NORTH);
        //frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //debate on exit
        frame.setVisible(true);
    }
}
