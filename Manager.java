import java.util.Date;

/**
 * A Manager Account
 * @author Andre Mak
 */

/**
 Manager class contains methods for manager
 */
public class Manager extends User
{
    public Manager(int id, String name, String password)
    {
        super(id, name, password);
    }
    
    public void checkReservation(Date startDate, Reservations r)
    {
        r.getReservationByDate(startDate);
    }
}
