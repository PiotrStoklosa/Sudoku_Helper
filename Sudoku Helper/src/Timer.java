import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Timer extends JLabel {

	private static final long serialVersionUID = -505480415486575848L;
	int minutes = 0;
	int seconds = 0;
	Timer instance = this;
	boolean state = true;
	Timer (Observer observer){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (state) {
					try {
						if (minutes>=60) {
							observer.end_of_time();
							state = false;
						}
						
						instance.setText("Time " + instance.minutes + " : " + instance.seconds);
						if (instance.seconds < 10 && instance.minutes < 10)
							instance.setText("Time " + "0" + instance.minutes + " : " + "0" + instance.seconds);
						else if (instance.seconds < 10)
							instance.setText("Time " + instance.minutes + " : " + "0" + instance.seconds);
						else if (instance.minutes < 10)
							instance.setText("Time " + "0" + instance.minutes + " : " + instance.seconds);
						instance.setFont(new Font("Arial", Font.BOLD, 30));
						instance.setHorizontalAlignment(JLabel.CENTER);
						instance.setVerticalAlignment(JLabel.CENTER);
						instance.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

						Thread.sleep(1000);
						instance.seconds++;
						if (instance.seconds == 60) {
							instance.seconds = 0;
							instance.minutes++;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
		thread.start();
		
		
			
	}

	public void off() {
		state = false;
	}
}
