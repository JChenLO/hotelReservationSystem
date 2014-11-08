/**
 * A Guest Account
 * @author Andre Mak
 */

public class Guest extends User
{
    public Guest(int id, String name, String password)
    {
        super(id, name, password);
    }
    
    public void makeReservation(String startDate, String endDate, int id, int roomNumber)
    {
        Reservation r = new Reservation(startDate, endDate, id, roomNumber);
        //list.add(r);
    }
    
    public void cancelReservation(int id)
    {
        //list.cancel(id);
    }
    
    public void viewReservation(int id)
    {
        //list.getReservationByUser(id);
    }
}
