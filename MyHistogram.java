import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Project #3
 * CS 2334, Section 010
 * Mar 26, 2016
 * <P>
 * Class for making the histogram
 * </P>
 */
public class MyHistogram {

	 /** 
	   * Creates a frame and a histogram
	   * @param LinkedHashMap
	   * @param double
	   */
	public MyHistogram(LinkedHashMap entry, double amount) {
        int width = 256;
        int height = 256;
        int[][] data = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = (int) (256 * Math.random());
            }
        }
        for (int c = 0; c < data.length; c++) {
            for (int r = 0; r < data[c].length; r++) {
                double value = data[c][r];
                if (entry.containsKey(value)) {
                    amount = (double) entry.get(value);
                    amount++;
                } else {
                    amount = 1;
                }
                entry.put(value, amount);
            }
        }
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(entry)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	/**
	 * Project #3
	 * CS 2334, Section 010
	 * Mar 26, 2016
	 * <P>
	 * Class within a class for drawing out the histogram components
	 * </P>
	 */
    protected class Graph extends JPanel {
    	
    	 /** So compiler doesn't yell at us*/
    	private static final long serialVersionUID = 1L;
		
    	 /** minimum width of the histogram bars*/
    	protected static final int MIN_BAR_WIDTH = 4;
    	
    	 /** Map containing an integer for key and an integer for value.*/
        private Map<Integer, Integer> mapHistory;

        /** 
  	   * Draws out the histogram
  	   * @param Map<Integer, Integer>
  	   */
        public Graph(Map<Integer, Integer> mapHistory) {
            this.mapHistory = mapHistory;
            int width = (mapHistory.size() * MIN_BAR_WIDTH) + 11;
            Dimension minSize = new Dimension(width, 128);
            Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }

        
        /** 
  	   * paints the histogram
  	   * @param Graphics
  	   */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mapHistory != null) {
                int xOffset = 5;
                int yOffset = 5;
                int width = getWidth() - 1 - (xOffset * 2);
                int height = getHeight() - 1 - (yOffset * 2);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                        / (float) mapHistory.size()));
                int maxValue = 0;
                for (Integer key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    maxValue = Math.max(maxValue, value);
                }
                int xPos = xOffset;
                for (Integer key : mapHistory.keySet()) {
                    int value = mapHistory.get(key);
                    int barHeight = Math.round(((float) value
                            / (float) maxValue) * height);
                    g2d.setColor(new Color(key, key, key));
                    int yPos = height + yOffset - barHeight;
                    //Rectangle bar = new Rectangle(xPos, yPos, barWidth, barHeight);
                    Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.draw(bar);
                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
}
