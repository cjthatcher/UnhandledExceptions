import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class DrawingPane extends JPanel implements Observer{
	Font defaultFont;
	Ground ground;
	final int WIDTH;
	final int HEIGHT;
	Map<Integer, Color> colorMap;
	
	
	public DrawingPane()
	{
		defaultFont = new Font("Times", Font.PLAIN, 12);
		ground = Ground.getInstance();
		
		ground.addObserver(this);
		WIDTH = 800;
		HEIGHT = 800;
		
		colorMap = new HashMap<Integer, Color>();
		colorMap.put(0, Color.RED);
		colorMap.put(1, Color.BLUE);
		colorMap.put(2, Color.WHITE);
		colorMap.put(3, Color.YELLOW);
	}

	 public void paintComponent(Graphics g)
	 {
		 System.out.println("Repaint called!");
		//System.out.println("Hey, they called repaint!");
	
		 int boxWidth = WIDTH / ground.getConfig().getBoardWidth();
		 int boxHeight = HEIGHT / ground.getConfig().getBoardHeight();
		 
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.GREEN);
		g2d.setFont(defaultFont);
		
		GroundCell[][] gcArray = ground.getCellArray();
		
		for (int y = 0; y < ground.getConfig().getBoardHeight(); y++)
		{
			for (int x = 0; x < ground.getConfig().getBoardWidth(); x++)
			{
				
				//Draw the grey box
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
				
				
				//If it has a hive, draw that
				if (gcArray[y][x].getNest() != null)
				{
					int tempColor = gcArray[y][x].getNest().getColony().getIdNumber();
					g2d.setColor(colorMap.get(tempColor));
					
					g2d.fillRoundRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight, boxWidth / 5, boxHeight / 5);
				}
				
				//if it has a food pile, draw that/
				if (gcArray[y][x].getFoodPile() != null)
				{
					g2d.setColor(Color.GREEN);
					g2d.fillOval(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
					
					g2d.setColor(Color.BLACK);
					g2d.drawString(Integer.toString(gcArray[y][x].getFoodPile().getFoodAmount()), x * boxWidth, (y+1) * boxHeight);
				}
				
				//if that cell has an ant draw it
				if (gcArray[y][x].getAnt() != null && gcArray[y][x].getAnt().size() > 0)
				{
					for (Ant a : gcArray[y][x].getAnt())
					{
						g2d.setColor(colorMap.get(a.getCol().getIdNumber()));
						g2d.fillRect(x * boxWidth, y * boxHeight, boxWidth / 2, boxHeight / 2);
						
						if (a.isCarryingFood())
						{
							g2d.setColor(Color.GREEN);
							g2d.fillOval((int)((x + 0.5) * boxWidth), (int)((y + 0.5) * boxHeight), boxWidth / 2, boxHeight / 2);
						}
					}
				}
				
				//if that ground has a pheromone, draw it semitransparent
				
				if (gcArray[y][x].getPheromone() != null && gcArray[y][x].getPheromone().size() > 0)
				{
					List<Pheromone> pList =  gcArray[y][x].getPheromone();
					Collections.sort(pList, new PheromoneComparator());
					
					Pheromone p = pList.get(0);
					
					int alphaValue = (int) (((float)(p.getStrength() / ground.getConfig().getPheromoneStrength()) * 0.5f) * 255);
					
					Color baseColor = colorMap.get(p.getColony().getIdNumber());
					
					Color transParent = new Color(baseColor.getRed(),baseColor.getGreen(), baseColor.getBlue(), alphaValue);
					
					g2d.setColor(transParent);
					
					g2d.drawOval(x * boxWidth, y * boxWidth, boxWidth / 2, boxHeight);
				}
			}
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("We got notified!");
		repaint();
	}
}
