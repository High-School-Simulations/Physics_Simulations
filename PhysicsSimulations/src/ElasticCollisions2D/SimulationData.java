package ElasticCollisions2D;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class SimulationData {
    boolean started;
    Color c1, c2;
    int x1, y1, x2, y2;
    Ellipse2D ball1, ball2;
    double ux1, uy1, ux2, uy2;
    
    public SimulationData() {
    	c1 = getColor();
    	c2 = getColor();
    	started = false;
    	x1 = 0; y1 = 0; x2 = 553; y2 = 327;
    	ux1 = 10; uy1 = 10; ux2 = -10; uy2 = -10;
    	ball1=new Ellipse2D.Double(x1, y1, 40, 40);
        ball2=new Ellipse2D.Double(x2, y2, 40, 40);
    }
    
    public Color getColor() {
        int a1 = (int) (Math.random() * 170);
        int b1 = (int) (Math.random() * 170);
        int c1 = (int) (Math.random() * 170);
        return(new Color(a1, b1, c1));
    }
    
    public void refresh() {
    	x1 = 0; y1 = 0; x2 = 553; y2 = 327;
        ux1 = 10; uy1 = 10; ux2 = -10; uy2 = -10;
    }
}