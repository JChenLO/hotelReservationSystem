/**
 @author Jie Chen
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.SimpleFormatter;

import javax.swing.*;
import javax.swing.GroupLayout.Group;
import javax.swing.border.Border;

//context program for strategy pattern
public class PrintFrame
{
   private Hotel hotel;
   private Guest guest;
   private int transactionID;
    PrintFrame(Hotel h, Guest g, int id)
    {
       hotel = h;
       guest = g;
       transactionID = id;
       
        final JFrame frame = new JFrame();
        frame.setTitle("MaGeC Hotel Print");
        frame.setSize(600,400);
        
        //title
        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Hotel Account Print");
        northPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        northPanel.add(label);


        
        //input new user information
        JPanel centerPanel = new JPanel();
        JRadioButton simpleButton = new JRadioButton("Print Simple Receipt");
        JRadioButton comprehensiveButton = new JRadioButton("Print Comprehensive Receipt");
        
        ButtonGroup group = new ButtonGroup();
        group.add(simpleButton);
        group.add(comprehensiveButton);
        
           
        centerPanel.add(simpleButton);
        centerPanel.add(comprehensiveButton);
      
        Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY);
        centerPanel.setBorder(lineBorder1);
        
        //submit button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(centerPanel, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        bottomPanel.add(textArea, BorderLayout.CENTER);
        
        JButton closeButton = new JButton("close");
        bottomPanel.add(closeButton, BorderLayout.SOUTH);
        closeButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e)
           {
              frame.dispose();
           }
        });
        simpleButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               ReceiptFormatter r = new SimpleReceipt(hotel, guest, transactionID);
                textArea.setText(r.format() );
            }
        });
        
        comprehensiveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {  ReceiptFormatter r = new ComprehensiveReceipt(hotel, guest);
                textArea.setText(r.format());
            }
        });
        

        //finished frame
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
   }
}
