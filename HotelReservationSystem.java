import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 @author Jie Chen
 */
public class HotelReservationSystem
{
   public static void main(String[] args)
   {
      init();
      
   }
   private static void init()
   {
      // loads reservation information s
      Hotel hotel = new Hotel();
      
      hotel.addUser(new Manager(0, "manager", "manager"));
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      int result = fileChooser.showOpenDialog(null);
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
            JOptionPane.showMessageDialog(null, exception);
         }
           catch (ClassNotFoundException exception)
         {
              JOptionPane.showMessageDialog(null, exception);
         }
      }
      
      
     // hotel.createAccount(new Guest(1234,"jchen","1234"));
      //hotel.createAccount(new Manager(0,"manager","manager"));
      new StartFrame(hotel);
      
   }
   
}
