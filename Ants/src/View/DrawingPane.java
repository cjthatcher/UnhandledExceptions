package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Model.Ant;
import Model.Ground;
import Model.GroundCell;
import Model.Pheromone;
import Model.PheromoneComparator;


public class DrawingPane extends JPanel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093448193919030012L;
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
		
		BufferedImage offScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		Graphics offGraphics = offScreen.getGraphics();
		Graphics2D off2DGraphics = (Graphics2D)offGraphics;
		
		off2DGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		for (int y = 0; y < ground.getConfig().getBoardHeight(); y++)
		{
			for (int x = 0; x < ground.getConfig().getBoardWidth(); x++)
			{
				
				//Draw the grey box
				off2DGraphics.setColor(Color.gray);
				off2DGraphics.fillRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
				
				off2DGraphics.setColor(Color.black);
				off2DGraphics.drawRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
				
				
				//If it has a hive, draw that
				if (gcArray[y][x].getNest() != null)
				{
					int tempColor = gcArray[y][x].getNest().getColony().getIdNumber();
					off2DGraphics.setColor(colorMap.get(tempColor));
					
					off2DGraphics.fillRoundRect(x * boxWidth, y * boxHeight, boxWidth, boxHeight, boxWidth / 5, boxHeight / 5);
				}
				
				//if it has a food pile, draw that/
				if (gcArray[y][x].getFoodPile() != null)
				{
					if (gcArray[y][x].getFoodPile().getFoodAmount() > 0)
					{
						off2DGraphics.setColor(Color.GREEN);
						off2DGraphics.fillOval(x * boxWidth, y * boxHeight, boxWidth, boxHeight);
						
						off2DGraphics.setColor(Color.BLACK);
						off2DGraphics.drawString(Integer.toString(gcArray[y][x].getFoodPile().getFoodAmount()), x * boxWidth, (y+1) * boxHeight);
					}
				}
				
				//if that cell has an ant draw it
				if (gcArray[y][x].getAnt() != null && gcArray[y][x].getAnt().size() > 0)
				{
					for (Ant a : gcArray[y][x].getAnt())
					{
						off2DGraphics.setColor(colorMap.get(a.getCol().getIdNumber()));
						off2DGraphics.fillOval((x * boxWidth), (y * boxHeight),(int) (boxWidth * 0.7), (int)(boxHeight * 0.7));
						
						if (a.isCarryingFood())
						{
							off2DGraphics.setColor(Color.GREEN);
							off2DGraphics.fillOval((int)((x + 0.5) * boxWidth), (int)((y + 0.5) * boxHeight), boxWidth / 2, boxHeight / 2);
						}
					}
				}
				
				//if that ground has a pheromone, draw it semitransparent
				
				if (gcArray[y][x].getPheromone() != null && gcArray[y][x].getPheromone().size() > 0)
				{
					List<Pheromone> pList =  gcArray[y][x].getPheromone();
					Collections.sort(pList, new PheromoneComparator());
					
					Pheromone p = pList.get(0);
					
					//int alphaValue = (int) (((float)(p.getStrength() / ground.getConfig().getPheromoneStrength()) * 0.5f) * 255);
					
					int alphaValue = (int)(((float)p.getStrength() / ground.getConfig().getPheromoneStrength()) * 255f);
					
					Color baseColor = colorMap.get(p.getColony().getIdNumber());
					
					Color transParent = new Color(baseColor.getRed(),baseColor.getGreen(), baseColor.getBlue(), alphaValue);
					
					off2DGraphics.setColor(transParent);
					
					off2DGraphics.drawOval(x * boxWidth, y * boxWidth, boxWidth / 2, boxHeight);
				}
			}
		}
		
		g2d.drawImage(offScreen, 0, 0, null);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("We got notified!");
		repaint();
	}
}
