import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;


public class StartFrame
{
   private Hotel hotel;
   StartFrame(Hotel h)
   {
      hotel = h;
      final JFrame frame = new JFrame();

      frame.setTitle("MaGeC Hotel Reservation System");
      frame.setSize(600,250);

      JPanel northPanel = new JPanel();
      JLabel label = new JLabel("Welcome to Hotel Reservation System");

      northPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
      label.setFont(new Font("Serif", Font.PLAIN, 24));
      northPanel.add(label);

      JPanel centerPanel = new JPanel();    
      centerPanel.setLayout(new BorderLayout());

      Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
      centerPanel.setBorder(lineBorder1);

      final JRadioButton guestRadio = new JRadioButton("Guest");
      final JRadioButton managerRadio = new JRadioButton("Manager");
      final ButtonGroup group = new ButtonGroup();
      group.add(guestRadio);
      group.add(managerRadio);
      group.setSelected(guestRadio.getModel(), true);
      JPanel centerUpperPanel = new JPanel();
      centerUpperPanel.add(guestRadio);
      centerUpperPanel.add(managerRadio);
      Border lineBorder2 = BorderFactory.createLineBorder(Color.GRAY);
      centerUpperPanel.setBorder(lineBorder2);

      JPanel centerMiddlePanel = new JPanel();
      JPanel centerMiddlePanel1 = new JPanel();
      JPanel centerMiddlePanel2 = new JPanel();

      JLabel idLabel = new JLabel("User ID");
      JLabel pinLabel = new JLabel("Password");
      final JTextField userIDField = new JTextField("1234");
      final JPasswordField pinField = new JPasswordField("1234");
      pinField.setColumns(8);
      centerMiddlePanel1.add(idLabel);
      centerMiddlePanel1.add(userIDField);

      centerMiddlePanel2.add(pinLabel);
      centerMiddlePanel2.add(pinField);

      final JPanel centerMiddlePanel3 = new JPanel();    
      JButton loginButton = new JButton("Login");
      loginButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            int id = Integer.parseInt(userIDField.getText());
            String pin = new String(pinField.getPassword());
            if(hotel.authentification(id,pin )) 
            {
               User user = hotel.findUserByID(id);
               assert(user.getClass() == Guest.class);
               if(guestRadio.isSelected() && user.getClass() == Guest.class) {
                  new GuestFrame((Guest) user);
               }

               else if(managerRadio.isSelected() && user.getClass() == Manager.class)
               {
                 //s new GuestFrame((Guest) user);
               }
               
               else 
                  new JDialog(frame, "Wrong user type ");
            }
            else 
               new JDialog(frame, "Mismatched User ID and password ");
         }
      });

      loginButton.setPreferredSize(new Dimension(80,40));

      centerMiddlePanel3.add(loginButton);
      centerMiddlePanel.add(centerMiddlePanel1);
      centerMiddlePanel.add(centerMiddlePanel2);
      centerMiddlePanel.add(centerMiddlePanel3);

      centerPanel.add(centerUpperPanel, BorderLayout.NORTH);
      centerPanel.add(centerMiddlePanel, BorderLayout.CENTER);

      JPanel bottomPanel = new JPanel();    
      JLabel msgLabel = new JLabel("New User? Click to sign up");
      JButton signupButton = new JButton("Sign Up");
      
      signupButton.addActionListener(new ActionListener()
      {
       
         public void actionPerformed(ActionEvent e)
         {
            new SignUpFrame(hotel);
         }
      });
      bottomPanel.add(msgLabel);
      bottomPanel.add(signupButton);


      frame.add(northPanel,BorderLayout.NORTH);
      frame.add(centerPanel,BorderLayout.CENTER);

      frame.add(bottomPanel,BorderLayout.SOUTH);

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }


}