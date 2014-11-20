/**
 @author Jie Chen
 */
import java.util.*;

public class Hotel
{
   public Hotel()
   {
      rooms = new ArrayList<Room>();
      users = new ArrayList<User>();
   }

   public Iterator<Room> roomIterator()
   {
      return new Iterator<Room>()
               {
         private int n = 0;
         @Override
         public boolean hasNext()
         {
            // TODO Auto-generated method stub
            return n < rooms.size();
         }

         @Override
         public Room next()
         {
            // TODO Auto-generated method stub
            if(hasNext())return rooms.get(n++);
            else return null;
         }

         @Override
         public void remove()
         {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException();
         }

               };
   }

   public Iterator<User> userIterator()
   {
      return new Iterator<User>()
               {
         int n = 0;
         @Override
         public boolean hasNext()
         {
            // TODO Auto-generated method stub
            return n < users.size();
         }

         @Override
         public User next()
         {
            // TODO Auto-generated method stub
            if(hasNext())return users.get(n++);
            else return null;
         }

         @Override
         public void remove()
         {
            throw new UnsupportedOperationException();

         }

               };
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

   
   public String getAvailableRooms(Calendar startDate, Calendar endDate, int roomType)
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   private ArrayList<Room> rooms;
   private ArrayList<User> users;
}
