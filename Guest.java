/**
 * A Guest Account
 * @author Andre Mak
 */
import java.util.Date;

public class Guest extends User
{
    public Guest(int id, String name, String password)
    {
        super(id, name, password);
    }
    
    public void makeReservation(Date startDate, Date endDate, int id, int roomNumber, Reservations list)
    {
        Reservation r = new Reservation(startDate, endDate, id, roomNumber);
        list.add(r);
    }
    
    public void cancelReservation(Reservation r, Reservations list)
    {
        list.cancel(r);
    }
    
    public void viewReservation(int id, Reservations list)
    {
        list.getReservationByUser(id);
    }
}