package ElasticCollisions2D;

import java.awt.Shape;
import java.awt.Dimension;
import java.awt.geom.Area;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SimulationData simObj;
	AnimationPanel anObj;
	ControlPanel cObj;
	
	Timer timer;
	
	public MainPanel()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {}
		
		simObj = new SimulationData();
		anObj = new AnimationPanel(simObj);
		cObj = new ControlPanel(simObj);
		
		timer = new Timer(50, this);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(anObj, gbc);
		gbc.gridy = 1;
		add(cObj, gbc);
		
		setPreferredSize(new Dimension(650,650));
		
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(simObj.started) {
            if(intersection(simObj.ball1, simObj.ball2)) {
                int nx = simObj.x2 - simObj.x1, ny = simObj.y2 - simObj.y1;
                double mn = Math.sqrt(Math.pow(nx, 2)+Math.pow(ny, 2));
                double unx = nx / mn, uny = ny / mn, utx = -uny, uty = unx;
                double u1n = ((simObj.ux1 * unx) + (simObj.uy1 * uny)), u1t = ((simObj.ux1 * utx) + (simObj.uy1 * uty));
                double u2n = ((simObj.ux2 * unx) + (simObj.uy2 * uny)), u2t = ((simObj.ux2*utx) + (simObj.uy2 * uty));
                double v1n = u2n, v1t = u1t;
                double v2n = u1n, v2t = u2t;
                double vx1 = ((v1n * unx) + (v1t * utx)), vy1 = ((v1n * uny) + (v1t * uty));
                double vx2 = ((v2n * unx) + (v2t * utx)), vy2 = ((v2n * uny) + (v2t * uty));
                simObj.ux1 = vx1;
                simObj.uy1 = vy1;
                simObj.ux2 = vx2;
                simObj.uy2 = vy2;
                simObj.c1=simObj.getColor();
                simObj.c2=simObj.getColor();
                cObj.sliders[4].setValue((int) simObj.ux1);
                cObj.sliders[5].setValue((int) simObj.uy1);
                cObj.sliders[6].setValue((int) simObj.ux2);
                cObj.sliders[7].setValue((int) simObj.uy2);
            }
            if(simObj.x1 > 553 || simObj.x1 < 0) {           
            	simObj.ux1 = -simObj.ux1;
            	simObj.c1 = simObj.getColor();
            	cObj.sliders[4].setValue((int) simObj.ux1);
            }
            if(simObj.y1 > 327 || simObj.y1 < 0) {   
            	simObj.uy1 = -simObj.uy1;
            	simObj.c1 = simObj.getColor();
            	cObj.sliders[5].setValue((int) simObj.uy1);
            }
            if(simObj.x2 > 553 || simObj.x2 < 0) {
            	simObj.ux2 = -simObj.ux2;
            	simObj.c2 = simObj.getColor();
            	cObj.sliders[6].setValue((int) simObj.ux2);
            }
            if(simObj.y2 > 327 || simObj.y2 < 0) {
            	simObj.uy2 = -simObj.uy2;
            	simObj.c2 = simObj.getColor();
            	cObj.sliders[7].setValue((int) simObj.uy2);
            }
            simObj.x1 += (int) simObj.ux1;
            simObj.x2 += (int) simObj.ux2;
            simObj.y1 += (int) simObj.uy1;
            simObj.y2 += (int) simObj.uy2;
            
            cObj.sliders[0].setValue(simObj.x1);
            cObj.sliders[1].setValue(simObj.y1);
            cObj.sliders[2].setValue(simObj.x2);
            cObj.sliders[3].setValue(simObj.y2);
        }
        anObj.repaint();
	}
	
	 public static boolean intersection(Shape shapeA, Shape shapeB) {
		 Area area = new Area(shapeA);
		 area.intersect(new Area(shapeB));
		 return !area.isEmpty();
	 }
}
