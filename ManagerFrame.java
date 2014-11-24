import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Andre
 */
public class ManagerFrame
{
    // Lihao Ge
	private Reservations reservationList;
	
    ManagerFrame()
    {
        final JFrame frame = new JFrame();
        frame.setTitle("MaGeC Hotel Manager Interface");
        frame.setSize(700,350);
        
        //title
        JPanel northPanel = new JPanel();
        JLabel label = new JLabel("Manager Interface");
        northPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        northPanel.add(label);
        
        //display calendar
        JPanel westPanel = new CalendarPanel();
        
        //display results window
        JPanel eastPanel = new JPanel();
        JTextArea textArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        eastPanel.add(scrollPane);
        
        //buttons
        JPanel bottomPanel = new JPanel();
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener()
        {
            //Lihao Ge
            public void actionPerformed(ActionEvent e)
            {
                String fileName="reservations.txt";
            	File file=new File(fileName);
            	BufferedReader br = null;
            	Date start = new Date();
            	Date end = new Date();
            	int id = 0;
            	int roomnum = 0;
    			DateFormat df = new SimpleDateFormat("yyyyMMdd");
    			try {
    				   br=new BufferedReader(new FileReader(file));
    				  } catch (FileNotFoundException ev) {
    				   ev.printStackTrace();
    				  }
    			  StringBuffer sb=new StringBuffer();
    			  String line=null;
    			  String[] rec=null;
    			  
    			  try {
    				   while((line=br.readLine())!=null){
    				    rec=line.split(";");
    				    start=df.parse(rec[0]);
    				    end = df.parse(rec[1]);
    				    id =Integer.parseInt(rec[2]);
    				    roomnum =Integer.parseInt(rec[3]);
    				    reservationList.add(new Reservation(start,end,id,roomnum));
    		}
    			  
    		  } catch (ParseException e1) {
    		   e1.printStackTrace();
    		  } catch (IOException e1) {
				e1.printStackTrace();
			}
            }
        });
        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               //Lihao Ge
               reservationList.save();
            	System.exit(0);
            }
        });
        bottomPanel.add(viewButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(saveAndQuitButton);
        
        //finished frame
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(westPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //debate on exit
        frame.setVisible(true);
    }
}
