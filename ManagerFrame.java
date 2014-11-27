import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Andre
 */
public class ManagerFrame extends JFrame
{
   //Jie Chen
   private Hotel hotel;       
   //display calendar
   private final CalendarPanelJC westPanel;
   private JTextArea textArea = new JTextArea();

   private JScrollPane scrollPane = new JScrollPane(textArea); 
   ManagerFrame(Hotel h)
   {
      hotel = h;
      final JFrame frame = new JFrame();
      frame.setTitle("MaGeC Hotel Manager Interface");
      frame.setSize(700,350);

      //title
      JPanel northPanel = new JPanel();
      JLabel label = new JLabel("Manager Interface");
      northPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
      label.setFont(new Font("Serif", Font.PLAIN, 24));
      northPanel.add(label);

      //display results window
      JPanel eastPanel = new JPanel();
      eastPanel.setLayout(new BorderLayout());
      textArea.setEditable(false);

      DefaultCaret caret = (DefaultCaret) textArea.getCaret();
      caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
      
      eastPanel.add(scrollPane, BorderLayout.CENTER);

      westPanel = new CalendarPanelJC();
      westPanel.addListener(new ChangeListener(){

         public void stateChanged(ChangeEvent e)
         {
            setView();
            repaint();
         }
      });
      
      //buttons
      JPanel bottomPanel = new JPanel();
      JButton loadButton = new JButton("Load");
      loadButton.addActionListener(new ActionListener()
      {
         //Lihao Ge
         public void actionPerformed(ActionEvent e)
         {
            //Jie Chen
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(ManagerFrame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ObjectInputStream in = null;
                try
               {
                  in = new ObjectInputStream(new FileInputStream(selectedFile));
                  hotel = (Hotel) in.readObject();
                  in.close();
               } catch (IOException exception)
               {
                  JOptionPane.showMessageDialog(null, exception + "io");
               }
                 catch (ClassNotFoundException exception)
               {
                    JOptionPane.showMessageDialog(null, exception + "class");
               }
            }
         }
      });
      JButton saveAndQuitButton = new JButton("Save and Quit");
      saveAndQuitButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            
            if(fileChooser.showSaveDialog(ManagerFrame.this) == JFileChooser.APPROVE_OPTION)
            {
                try
            {
               File file = fileChooser.getSelectedFile();
               ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
               out.writeObject(hotel);
               out.close();
               System.exit(0);
            }
                catch (IOException exception)
            {
              JOptionPane.showMessageDialog(null, exception + "io exception");
            }
            
         }
            }}
      );

      bottomPanel.add(loadButton);
      bottomPanel.add(saveAndQuitButton);

      //finished frame
      frame.add(northPanel, BorderLayout.NORTH);
      frame.add(westPanel, BorderLayout.WEST);
      frame.add(eastPanel, BorderLayout.CENTER);
      frame.add(bottomPanel, BorderLayout.SOUTH);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //debate on exit
      frame.setVisible(true);
   }

   public void setView()
   {
      Calendar date = Calendar.getInstance();
      date.set(westPanel.getYY(),  westPanel.getMM(), westPanel.getDD());

      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
      String t = sdf.format(date.getTime()) + "\n\n";
      ArrayList<Reservation> rlist = hotel.getReservations(date.getTime());
      t += "Booked Rooms:\n";
      for(Reservation r : rlist)
      {
         t+="Room #" + r.getRoomNumber() 
                  + " Guest ID: " + r.getID() 
                  + " Price: $" + r.getPrice() 
                  + '\n';
      }

      t+="\nAvailable Rooms: \n";

      for(Room r : hotel.getRooms())
      {
         if(! r.getReservations().getReservationByDate(date.getTime()).hasNext())
            t+="Room #" + r.getRoomNumber() + '\n';
      }
      textArea.setText(t);


   }
}
