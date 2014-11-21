/**
 @author Jie Chen
 */
import java.util.*;

public class Room
{
   public Room(int n, int c, Reservations r)
   {
      roomNumber = n;
      cost = c;
      reservations = r;
   }
   
   public void setReservation(Reservations list)
   {
      reservations = list;
   }
   
   /**
    
    */
   public void addReservation(Reservation r)
   {
      reservations.add(r);
   }
   
   public boolean isType(int type)
   {
      return cost == type;
   }

   public boolean isAvailable(Calendar startDate, Calendar endDate)
   {
      Iterator<Reservation> it = reservations.getReservations();
      while(it.hasNext())
      {
         Reservation r = it.next();
         Date sDate = r.getStartDate();
         Date eDate = r.getendDate();
         if( (sDate.after(startDate.getTime()) && sDate.before(endDate.getTime()))
                  ||  (eDate.after(startDate.getTime()) && eDate.before(endDate.getTime())))
                  {
                     return false;
                  }
      }
      
      return true;
   }
   
   public String toString()
   {
      return "Room " + roomNumber + " Price = " + cost;
   }
   private int cost;
   private int roomNumber;
   private Reservations reservations;
   private final int LUX = 200;
   private final int ECO = 80;
   
}
