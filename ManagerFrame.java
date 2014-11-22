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
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
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
