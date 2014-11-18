import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Reservations {

	private ArrayList<Reservation> reservationList;
	
	public Reservations()
	{
		reservationList = new ArrayList<Reservation>();
	}
	
	public void add(Reservation r)
	{
		reservationList.add(r);
		
		// sort the list according to the start dates
		if(reservationList.size()>1){
		Collections.sort(reservationList,new Comparator<Reservation>(){
			public int compare(Reservation r1, Reservation r2)
			{
				if(r1.getStartDate().after(r2.getStartDate()))
				return  -1;
				else
				return 1;
			}});
		}
	}
	
	
	public void cancel(Reservation r)
	{
		
		reservationList.remove(r);
		
	}
	
	public Iterator<Reservation> getReservationByUser(int id)
	{
		ArrayList<Reservation> reservesUnderSameID = new ArrayList<Reservation>();
		for(Reservation re : reservationList)
		{
			if(re.getID()==id)
			{
				reservesUnderSameID.add(re);
			}
		}
		
		return reservesUnderSameID.iterator();
	}
	
	public Iterator<Reservation> getReservationByDate(Date date)
	{
		ArrayList<Reservation> reservesUnderSameDate = reservationList;
		for(Reservation re : reservationList)
		{
           if(re.getStartDate().after(date)||date.after(re.getendDate()))
           {
        	// iterate the reservation list, remove reservations that didn't contain this date 
        	   reservesUnderSameDate.remove(re);
           }
	}
		return reservesUnderSameDate.iterator();
}

public void save()
	 {
		 try {
			 

				File file = new File("reservations.txt");

				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				for (Reservation r: reservationList)
				{
					bw.write(r.getStartDate()+";");
					bw.write(r.getendDate()+";");
					bw.write(r.getID()+";");
                    bw.write(r.getRoomNumber()+"/r/n");

				}
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
}
