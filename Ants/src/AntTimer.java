import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class AntTimer 
{
	private int delay;
	private Timer time;
	
	public AntTimer()
	{
		delay= Ground.getInstance().getConfig().getMillisecondDelay(); // this is in milliseconds
		time = new Timer(delay, new timeListener());
		time.start();
	}
	
	private class timeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Ground.getInstance().updateGround();
		}
	}
}
