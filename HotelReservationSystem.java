import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 @author Jie Chen
 */

/**
Class HotelReservation provide the main method for testing
 */
public class HotelReservationSystem
{
   public static void main(String[] args)
   {   
      Hotel hotel = new Hotel();
     
      // create manager
      hotel.addUser(new Manager(0, "manager", "manager"));
      
      // start gui
      new StartFrame(hotel);
      
   }
   
}
