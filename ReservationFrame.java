/**
 @author Jie Chen
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ReservationFrame
{
   private Guest guest;
   private Hotel hotel;
   private int transactionID;

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

   private final JRadioButton luxButton = new JRadioButton("$200");
   private final JRadioButton ecoButton = new JRadioButton("$80");

   final JList<Room> jlist = new JList<Room>();

   private int total = 0;
   ReservationFrame(Hotel h, Guest g, int transID)
   {
      hotel = h;
      guest = g;
      transactionID = transID;
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
      checkinField.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            setAvailableRooms();
         }
      });

      cal.add(Calendar.DATE, 7); 
      checkoutField = new JTextField(dt.format(cal.getTime()));

      checkoutField.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            setAvailableRooms();
         }
      });

      northCenterPanel.add(checkinLabel);
      northCenterPanel.add(checkoutLabel);
      northCenterPanel.add(checkinField);
      northCenterPanel.add(checkoutField);

      final JPanel northSouthPanel = new JPanel();
      JLabel roomTypeLabel = new JLabel("Room Type:");
      group = new ButtonGroup();
      group.add(luxButton);
      group.add(ecoButton);
      group.setSelected(luxButton.getModel(), true);

      ecoButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            setAvailableRooms();
         }
      });

      luxButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            setAvailableRooms();
         }
      });

      northSouthPanel.add(roomTypeLabel);
      northSouthPanel.add(luxButton);
      northSouthPanel.add(ecoButton);
      northPanel.add(northCenterPanel, BorderLayout.CENTER);
      Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
      northPanel.setBorder(lineBorder1);
      northPanel.add(northSouthPanel, BorderLayout.SOUTH);


      //View
      centerPanel.setLayout(new BorderLayout());    
      hotel.attach(new ChangeListener(){
         public void stateChanged(ChangeEvent e){
            
            DefaultListModel<Room> model = new DefaultListModel<Room>();
            ArrayList<Room> rooms;
            if(isValidDate()){
                rooms = hotel.getAvailableRooms();
                for(Room r : rooms)
                {model.addElement(r);}
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
            makeReservation();
            setAvailableRooms();
         }
      });
      JButton transactionDoneButton = new JButton("Transaction Done");
      transactionDoneButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            /* output receipt*/
            new PrintFrame(hotel, guest, transactionID);
            frame.dispose();
         }
      });
      bottomPanel.add(confirmButton);
      bottomPanel.add(transactionDoneButton);


      frame.add(northPanel, BorderLayout.NORTH);
      frame.add(centerPanel, BorderLayout.CENTER);
      frame.add(bottomPanel, BorderLayout.SOUTH);
      setAvailableRooms();
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
      frame.setVisible(true);
   }

   // call Hotel.mutator 
   private void setAvailableRooms()
   {
      if(!isValidDate())return;
      int roomType = luxButton.isSelected() ? 200 : 80;
      hotel.setAvailableRooms(inDate, outDate, roomType);
      
   }

   private boolean isValidDate()
   {
      dt.setLenient(false);
      try
      {
         inDate.setTime(dt.parse(checkinField.getText()));

      } catch (ParseException e1)
      {
         JOptionPane.showMessageDialog(frame, "Invalid checkin date", "MaGeC Hotel Message",
                  JOptionPane.WARNING_MESSAGE); return false;
      }

      try
      {

         outDate.setTime(dt.parse(checkoutField.getText()));

      } catch (ParseException e1)
      {
         JOptionPane.showMessageDialog(frame, "Invalid checkout date", "MaGeC Hotel Message",
                  JOptionPane.WARNING_MESSAGE); return false;
      }
      if(inDate.get(Calendar.DATE) < Calendar.getInstance().get(Calendar.DATE)){
         JOptionPane.showMessageDialog(frame,
                  "Checkin date earlier than today. Please try again", "MaGeC Hotel Message",
                  JOptionPane.WARNING_MESSAGE); return false;
      }

      if(outDate.before(inDate)){
         JOptionPane.showMessageDialog(frame,
                  "Checkout date earlier than checkin date. Please try again", "MaGeC Hotel Message",
                  JOptionPane.WARNING_MESSAGE); return false;
      }
      inDate.add(Calendar.DATE, 60);
      if(inDate.before(outDate)){
         JOptionPane.showMessageDialog(frame, "Length of stay cannot be longer than 60 days","MaGeC Hotel Message", 
                  JOptionPane.WARNING_MESSAGE); return false;
      }
      inDate.add(Calendar.DATE, -60);
      return true;
   }
   private void makeReservation()
   {
      Room room = jlist.getSelectedValue();
      Reservation r = new Reservation(transactionID, inDate.getTime(), outDate.getTime(), guest.getID(), room.getRoomNumber());
      total += room.getCost();
      room.addReservation(r);
      JOptionPane.showConfirmDialog(frame, 
               "Make more reservations?", "MaGeC Hotel Message", JOptionPane.OK_CANCEL_OPTION );

   }

}


