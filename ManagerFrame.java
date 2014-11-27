import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Andre
 */
public class ManagerFrame extends JFrame
{
   // Lihao Ge
   private Reservations reservationList;
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
            String fileName="reservations.txt";
            File file=new File(fileName);
            BufferedReader br = null;
            Date start = new Date();
            Date end = new Date();
            int id = 0;
            int roomnum = 0;
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            try {
               br=new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException ev) {
               ev.printStackTrace();
            }
            StringBuffer sb=new StringBuffer();
            String line=null;
            String[] rec=null;

            try {
               while((line=br.readLine())!=null){
                  rec=line.split(";");
                  start=df.parse(rec[0]);
                  end = df.parse(rec[1]);
                  id =Integer.parseInt(rec[2]);
                  roomnum =Integer.parseInt(rec[3]);
                  reservationList.add(new Reservation(start,end,id,roomnum));
               }

            } catch (ParseException e1) {
               e1.printStackTrace();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      });
      JButton saveAndQuitButton = new JButton("Save and Quit");
      saveAndQuitButton.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            //Lihao Ge
            reservationList.save();
            System.exit(0);
         }
      });

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
