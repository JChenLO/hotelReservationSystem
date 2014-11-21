/**
 @author Jie Chen
 */
import java.util.*;

public class Hotel
{
   public Hotel()
   {
      rooms = new ArrayList<Room>();
      //10 economic rooms
      for(int i = 0; i < 10; i++){
         rooms.add(new Room(i, 80, new Reservations() ));
      }
      //10 luxurious rooms
      for(int i = 10; i < 20; i++){
         rooms.add(new Room(i, 200, new Reservations() ));
      }
      users = new ArrayList<User>();
   }

   public Iterator<Room> roomIterator()
   {
      return rooms.iterator();
   }

   public Iterator<User> userIterator()
   {
      return users.iterator();
   }

   public void createAccount(User user)
   {
      users.add(user);
   }
   
   
   public void addRoom(Room r)
   {
      rooms.add(r);
   }
   
   public User findUserByID(int id)
   {
      Iterator<User> it = userIterator();
      
      while(it.hasNext())
         {
            User user = it.next();
            if(user.getID() == id)return user;
         }
      return null;
   }
   

   public boolean authentification(int id, String pin)
   {
      Iterator<User> it = userIterator();
      
      while(it.hasNext())
         {
            User user = it.next();
            if(user.getID() == id)return user.getPassword().equals(pin);
         }
      return false;
   }

   
   public ArrayList<Room> getAvailableRooms(Calendar startDate, Calendar endDate, int roomType)
   {
      Iterator<Room> it = roomIterator();
      ArrayList<Room> availableRooms = new ArrayList<Room>();
      while(it.hasNext())
      {
         Room room = it.next();
         if(room.isType(roomType) && room.isAvailable(startDate, endDate)) {
            availableRooms.add(room);
         }
      }
      return availableRooms;
   }
   
   private ArrayList<Room> rooms;
   private ArrayList<User> users;
}
