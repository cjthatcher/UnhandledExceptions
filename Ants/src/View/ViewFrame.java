package View;
import java.awt.Color;

import javax.swing.JFrame;


public class ViewFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8931971865490053891L;
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
