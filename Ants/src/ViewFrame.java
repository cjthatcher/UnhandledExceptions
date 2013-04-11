import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;


public class ViewFrame extends JFrame{
	
	DrawingPane thisDrawingPane;
	final int WIDTH = 840;
	final int HEIGHT = 860;
	
	public ViewFrame()
	{
		setLocation(0, 0);
		setLayout(null);

		
		thisDrawingPane = new DrawingPane();
		thisDrawingPane.setSize(800, 800);
		thisDrawingPane.setLocation(10, 10);
		
		thisDrawingPane.setVisible(true);
		thisDrawingPane.setBackground(Color.BLACK);
		thisDrawingPane.setLayout(null);
		
		add(thisDrawingPane);
		
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setTitle("Ants");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public DrawingPane getDrawingPane()
	{
		return thisDrawingPane;
	}
	
}
