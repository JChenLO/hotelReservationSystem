
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
      hotel.createAccount(new Guest(1234,"jchen","1234"));
      StartFrame frame = new StartFrame(hotel);
      
   }
   
}
