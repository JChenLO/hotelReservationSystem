import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserFrame
{
    UserFrame()
    {
        final JFrame frame = new JFrame();
        frame.setTitle("MaGeC Hotel User Interface");
        frame.setSize(600,150);
        
        //title
        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("User Interface");
        northPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        northPanel.add(label);
        
        //display
        JPanel centerPanel = new JPanel();
        
        //buttons
        JPanel bottomPanel = new JPanel();
        JButton makeReservationButton = new JButton("Make a Reservation");
        makeReservationButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                /*With “Make a Reservation” option, the guest should be able to select the check in date, check out 
                date, and the type of room. The start or end date/time should not be prior to the current date.
                The length of stay cannot be longer than 60 nights. Your system enforces these constraints and 
                generates an error for any violation. */
                
            }
        });
        JButton viewOrCancelButton = new JButton("View/Cancel a Reservation");
        viewOrCancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                /* With“View/Cancel a reservation”option, the system displays all reservations made by this guest.
                (When the guest asks for the view/cancel, he/she already logged in and thus the system does not 
                have to ask the id.) The guest may just view the reservations or select and cancel reservations. */
                
            }
        });
        bottomPanel.add(makeReservationButton);
        bottomPanel.add(viewOrCancelButton);
        
        //finished frame
        frame.add(northPanel, BorderLayout.NORTH);
        //frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //debate on exit
        frame.setVisible(true);
    }
}
