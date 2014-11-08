import java.util.*;

public class Room
{
   public Room(int n, double c, Reservations r)
   {
      roomNumber = n;
      cost = c;
      reservations = r;
   }
   
   public setReservation(Reservations list)
   {
      reservations = list;
   }
   
   /**
    
    */
   public void addReservation(Reservation r)
   {
      reservations.add(r);
   }
   
   private double cost;
   private int roomNumber;
   private Reservations reservations;
}
