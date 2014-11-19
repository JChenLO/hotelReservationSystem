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
   private ArrayList<Room> rooms;
   private ArrayList<User> users;
}
