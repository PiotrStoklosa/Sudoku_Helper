import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable {

	private static final long serialVersionUID = -505480415486575848L;
	
	int minutes = 0;
	int seconds = 0;
	boolean state = true;
	@Override
	public void run() {
		while (state) {
			try {
				setText("Time " + minutes + " : " + seconds);
				if (seconds < 10 && minutes < 10)
					setText("Time " + "0" + minutes + " : " + "0" + seconds);
				else if (seconds < 10)
					setText("Time " + minutes + " : " + "0" + seconds);
				else if (minutes < 10)
					setText("Time " + "0" + minutes + " : " + seconds);
				setFont(new Font("Arial", Font.BOLD, 30));
				setHorizontalAlignment(JLabel.CENTER);
				setVerticalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

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
	public void off() {
		state = false;
	}
}
