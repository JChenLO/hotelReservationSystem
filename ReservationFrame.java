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

public class ReservationFrame
{
   private Guest guest;
   private Hotel hotel;
   private JTextField checkinField;
   private JTextField checkoutField;
   private ButtonGroup group;
   private final SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
   private JList roomList;
   private final JFrame frame = new JFrame();
   Calendar inDate = Calendar.getInstance();
   Calendar outDate= Calendar.getInstance();  
   
       ReservationFrame(Hotel h, Guest g)
       {
          hotel = h;
           guest = g;

           frame.setTitle("MaGeC Hotel Reservation Interface");
           frame.setSize(600,600);
           
           //Control - may need to factorize to seperate panel
           JPanel northPanel = new JPanel();
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
           
           JPanel northSouthPanel = new JPanel();
           JLabel roomTypeLabel = new JLabel("Room Type:");
           JRadioButton luxButton = new JRadioButton("$200");
           JRadioButton ecoButton = new JRadioButton("$80");
           group = new ButtonGroup();
           group.add(luxButton);
           group.add(ecoButton);
           group.setSelected(luxButton.getModel(), true);
          
           northSouthPanel.add(roomTypeLabel);
           northSouthPanel.add(luxButton);
           northSouthPanel.add(ecoButton);
           
           northPanel.add(northCenterPanel, BorderLayout.CENTER);

           Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
           northPanel.setBorder(lineBorder1);
           
           northPanel.add(northSouthPanel, BorderLayout.SOUTH);

           
           //View
           final JPanel centerPanel = new JPanel();
           final JLabel confirmLabel = new JLabel();
           centerPanel.setLayout(new BorderLayout());
           setRoomView();
           centerPanel.add(roomList, BorderLayout.CENTER);
           centerPanel.add(confirmLabel, BorderLayout.SOUTH);
           Border lineBorder2 = BorderFactory.createLineBorder(Color.GRAY);
           centerPanel.setBorder(lineBorder2);
           centerPanel.setBackground(Color.WHITE);
       
           //buttons
           JPanel bottomPanel = new JPanel();
           JButton confirmButton = new JButton("Confirm");
           confirmButton.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   /*make reservations */
                   Room room = (Room) roomList.getSelectedValue();
                   
                   Reservation r = new Reservation(inDate.getTime(), outDate.getTime(), guest.getID());
                   room.addReservation(r);
                   confirmLabel.setText("made reservation on " + room.toString());
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
          // frame.pack();
           frame.setVisible(true);
       }
       
       
       private void setRoomView()
       {
          
          ArrayList<Room> rooms;
          try
        {
           inDate.setTime(dt.parse(checkinField.getText()));
           outDate.setTime(dt.parse(checkinField.getText()));
        } catch (ParseException e1)
        {
           // TODO Auto-generated catch block
           e1.printStackTrace();
        }
          for(Enumeration<AbstractButton> bs = group.getElements() ; bs.hasMoreElements();){
             AbstractButton b = bs.nextElement();
             if(b.isSelected()){
                String roomType = b.getText().substring(1);
                rooms = hotel.getAvailableRooms(inDate, outDate, Integer.parseInt(roomType));
                
                
                   roomList = new JList(rooms.toArray());
                   if(roomList.getModel().getSize() > 0)roomList.setSelectedIndex(0);
             }
          }
          
       }
       
   }


