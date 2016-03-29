import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

/**
 * Project #3
 * CS 2334, Section 010
 * Mar 26, 2016
 * <P>
 * Class for making the pie chart
 * </P>
 */
public class MyPieChart extends JComponent {
	  
		/** So compiler doesn't yell at us*/
		private static final long serialVersionUID = 1L;
		
		/** the movie producing credits for the creator*/
		double MPC = Creator.getMPC();
		
		/** the series producing credits for the creator*/
		double SPC = Creator.getSPC();
		
		/** the series acting credits for the creator*/
		double SAC = Creator.getSAC();
		
		/** the movie acting credits for the creator*/
		double MAC = Creator.getMAC();
		
		/** the series directing credits for the creator*/
		double SDC = Creator.getSDC();
		
		/** the movie directing credits for the creator*/
		double MDC = Creator.getMDC();
		
		/** an array of slices of different colors*/
		  Slice[] slices = { new Slice(MPC, Color.black), new Slice(SPC, Color.green),
		      new Slice(SAC, Color.yellow), new Slice(MAC, Color.red), 
		      new Slice(SDC, Color.blue), new Slice(MDC, Color.pink)};

		  /** 
		   * Paints the pie
		   * @param Graphics
		   */
		  public void paint(Graphics g) {
		      drawPie((Graphics2D) g, getBounds(), slices);
		   }
		  
		  /** 
		   * Draws a pie
		   * @param Graphics2D
		   * @param Rectangle
		   * @param Slices[]
		   */
		  public static void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		      double total = 0.0;
		      for (int i = 0; i < slices.length; i++) {
		         total += slices[i].value;
		      }
		      double curValue = 0.0D;
		      int startAngle = 0;
		      for (int i = 0; i < slices.length; i++) {
		         startAngle = (int) (curValue * 360 / total);
		         int arcAngle = (int) (slices[i].value * 360 / total);
		         g.setColor(slices[i].color);
		         g.fillArc(area.x, area.y, area.width, area.height, 
		         startAngle, arcAngle);
		         curValue += slices[i].value;
		}
		   
}
}
