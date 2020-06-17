import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable{
	
	int minutes = 0;
	int seconds = 0;

	@Override
	public void run() {
		while(true)
		{
			try {
				setText("Time " + minutes + " : " + seconds);
				if (seconds<10 && minutes<10)
					setText("Time " + "0" + minutes + " : " + "0" + seconds);
				else if (seconds<10)
					setText("Time " + minutes + " : " + "0" + seconds);
				else if (minutes<10)
					setText("Time " + "0" + minutes + " : " + seconds);
				setFont(new Font("Arial", Font.BOLD, 30));
				setHorizontalAlignment(JLabel.CENTER);
				setVerticalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.black));
				
				Thread.sleep(1000);
				seconds++;
				if (seconds == 60) {
					seconds = 0;
					minutes++;
				}				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
