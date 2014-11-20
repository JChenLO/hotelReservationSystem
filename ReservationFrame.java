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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

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
   
       ReservationFrame(Hotel h, Guest g)
       {
          hotel = h;
           guest = g;
           final JFrame frame = new JFrame();
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
           JRadioButton ecoButton = new JRadioButton("$100");
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
           JPanel centerPanel = new JPanel();
           JTextArea roomTextArea = new JTextArea();
           roomTextArea.setSize(centerPanel.getSize());
           centerPanel.add(roomTextArea);

           String rooms = getAvailableRooms();
           roomTextArea.setText(rooms);
           
           //buttons
           JPanel bottomPanel = new JPanel();
           JButton confirmButton = new JButton("Confirm");
           confirmButton.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   /*make reservations */
                   
               }
           });
           JButton transactionDoneButton = new JButton("Transaction Done");
           transactionDoneButton.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   /* done dispose frame */
                   
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
       
       
       private String getAvailableRooms()
       {
          Calendar inDate = Calendar.getInstance();
          Calendar outDate= Calendar.getInstance();     
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
                return hotel.getAvailableRooms(inDate, outDate, Integer.parseInt(roomType));
             }
          }
          return "No Room Available, please change date or room type and try again";
       }
       
   }


