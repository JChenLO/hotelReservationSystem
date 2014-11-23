/**
 @author Jie Chen
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ReservationFrame
{
   private Guest guest;
   private Hotel hotel;
   private JTextField checkinField;
   private JTextField checkoutField;
   private ButtonGroup group;
   private final SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
   private final JFrame frame = new JFrame();
   Calendar inDate = Calendar.getInstance();
   Calendar outDate= Calendar.getInstance();  
   private final JPanel northPanel = new JPanel();
   private final JPanel centerPanel = new JPanel();
   private final JPanel bottomPanel = new JPanel();
   private int total = 0;
   ReservationFrame(Hotel h, Guest g)
   {
      hotel = h;
      guest = g;

      frame.setTitle("MaGeC Hotel Reservation Interface");
      frame.setSize(600,600);

      //Checkin checkout date
      northPanel.setLayout(new BorderLayout());

      JPanel northCenterPanel = new JPanel();
      northCenterPanel.setLayout(new GridLayout(2,2));

      JLabel checkinLabel = new JLabel("Check-in:");
      JLabel checkoutLabel = new JLabel("Check-out:");

      Calendar cal = Calendar.getInstance();
      checkinField = new JTextField(dt.format(cal.getTime()));
      cal.add(Calendar.DATE, 7); 
      checkoutField = new JTextField(dt.format(cal.getTime()));

      northCenterPanel.add(checkinLabel);
      northCenterPanel.add(checkoutLabel);
      northCenterPanel.add(checkinField);
      northCenterPanel.add(checkoutField);

      final JPanel northSouthPanel = new JPanel();
      JLabel roomTypeLabel = new JLabel("Room Type:");
      final JRadioButton luxButton = new JRadioButton("$200");
      final JRadioButton ecoButton = new JRadioButton("$80");
      group = new ButtonGroup();
      group.add(luxButton);
      group.add(ecoButton);
      group.setSelected(luxButton.getModel(), true);

      JButton showButton = new JButton("Show Available Rooms");
      showButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            try
            {
               inDate.setTime(dt.parse(checkinField.getText()));
               outDate.setTime(dt.parse(checkinField.getText()));
            } catch (ParseException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            int roomType = luxButton.isSelected() ? 200 : 80;
            hotel.setAvailableRooms(inDate, outDate, roomType);
         }
      });

      northSouthPanel.add(roomTypeLabel);
      northSouthPanel.add(luxButton);
      northSouthPanel.add(ecoButton);
      northSouthPanel.add(showButton);
      northPanel.add(northCenterPanel, BorderLayout.CENTER);
      Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
      northPanel.setBorder(lineBorder1);
      northPanel.add(northSouthPanel, BorderLayout.SOUTH);


      //View
      centerPanel.setLayout(new BorderLayout());           
      final JList<Room> jlist = new JList<Room>();
      hotel.attach(new ChangeListener(){
         public void stateChanged(ChangeEvent e){
            DefaultListModel<Room> model = new DefaultListModel<Room>();
            ArrayList<Room> rooms = hotel.getAvailableRooms();
            for(Room r : rooms){
               model.addElement(r);
            }
            //notify method
            jlist.setModel(model);
            if(model.size() > 0)jlist.setSelectedIndex(0);
         }
      });

      centerPanel.add(jlist);
      final JLabel confirmLabel = new JLabel();
      centerPanel.add(confirmLabel, BorderLayout.SOUTH);
      Border lineBorder2 = BorderFactory.createLineBorder(Color.GRAY);
      centerPanel.setBorder(lineBorder2);
      centerPanel.setBackground(Color.WHITE);

      //buttons
      JButton confirmButton = new JButton("Confirm");
      confirmButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            /*make reservations */
            Room room = jlist.getSelectedValue();
            Reservation r = new Reservation(inDate.getTime(), outDate.getTime(), guest.getID());
            total += room.getCost();
            room.addReservation(r);
            confirmLabel.setText("made reservation on " + room.toString() + "  Your total = " + total);
         }
      });
      JButton transactionDoneButton = new JButton("Transaction Done");
      transactionDoneButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            /* done dispose frame */
            frame.dispose();
         }
      });
      bottomPanel.add(confirmButton);
      bottomPanel.add(transactionDoneButton);


      frame.add(northPanel, BorderLayout.NORTH);
      frame.add(centerPanel, BorderLayout.CENTER);
      frame.add(bottomPanel, BorderLayout.SOUTH);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
      frame.setVisible(true);
   }

}


