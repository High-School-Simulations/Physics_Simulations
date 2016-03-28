package ElasticCollisions2D;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class AnimationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SimulationData simObj;
	
	public AnimationPanel(SimulationData obj) {
		simObj = obj;
		Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(loweredEtched);
		setPreferredSize(new Dimension(600, 373));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    simObj.ball1.setFrame(simObj.x1 + 3, simObj.y1 + 3, 40, 40);
	    simObj.ball2.setFrame(simObj.x2 + 3, simObj.y2 + 3, 40, 40);
	    g2.setPaint(simObj.c1);
	    g2.fill(simObj.ball1);
        g2.setPaint(simObj.c2);
        g2.fill(simObj.ball2);
    }
}
