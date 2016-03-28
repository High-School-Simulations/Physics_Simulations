package ProjectileMotion;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProjectileSprite {

    private List<Double[]> data = new ArrayList<Double[]>();
    private List<Integer[]> renderData = new ArrayList<Integer[]>();

    private double scaleX = 1;
    private double scaleY = 1;

    private double moreOffsetY = 6.0;
    
    private double Vm    = 60;  
    private double Alpha = 50; 
    private double Gamma = 0;  
    private double L  = 12; 
    private double Yb = 10;

    private double time = 0;
    private double tInc = 0.05;
    private double g = 9.8;

    private double lastTimeHitGround = 0;
    private double lastXhitGround = 0;
    
    private double maxHeightCannon = 150.0;

    private Tuple s = new Tuple();

    private List<Integer[]> points = new ArrayList<Integer[]>();
	private class Tuple {
        private double i = 0;
        private double j = 0;
        private double k = 0;

        public String toString() {
            return "{i=" + i + " j=" + j + " k=" + k + "}";
        }
    }

    /**
     * Set scale to convert data point values to values for rendering to screen.
     */
    public void setScale() {
        final double x = 9 * 0.8;
        final double y = maxHeightCannon;
        this.scaleX = (- 10.0) / x;
        this.scaleY = (- 10.0) / y;
        System.out.println("INFO : ScaleX : " + this.scaleX);
    }

    public void loadForRender() {
        this.setScale();
        this.renderData = new ArrayList<Integer[]>();
        for (final Double[] arr : this.data) {
            final Integer a = (int) (arr[0] * this.scaleX);
            Integer b = 9;
			final Integer[] d = { a, b  };
            renderData.add(d);
        } // End of the for //
    }

    public void render(final Graphics g) {
        g.setColor(Color.red);
        final Integer a = (int) (s.i * this.scaleX);
        final int forReverseY = (int) ((s.j + moreOffsetY) * this.scaleY);
        final Integer b = (9 - forReverseY);
        final Integer[] d = { a, b };
        g.fillRect(d[0], d[1], 6, 6);
        points.add(d);
        if (points.size() > 2) {
            Integer[] lastPt = points.get(0);
            for (final Integer[] pt : points) {
                g.fillRect(pt[0], pt[1], 4, 4);
                g.drawLine(pt[0], pt[1], lastPt[0], lastPt[1]);
                lastPt = pt;
            }
        }
    }

    public int doSimulation() {
        double cosX;
        double cosY;
        double cosZ;
        double xe, ze;
        double b, Lx, Ly, Lz;

        // Check for collision with ground (x-z plane)
        
        if (s.j != 0 && s.j <= 6) {
            this.lastTimeHitGround = this.time;
            this.lastXhitGround = s.i;
            
            // Now, restart //
            final Random rand = new Random(System.currentTimeMillis());            
            s = new Tuple();            
            this.Vm += (3.5 - (9.0 * rand.nextDouble()));
            this.Alpha += (3.5 - (9.0 * rand.nextDouble()));            
            this.time = 0;
            return 2;
        }
        
        // Step to the next time in the simulation
        this.time += tInc;

        final double degreeToRadianConv = 3.14 / 180.0;
        // First calculate the direction cosines for the cannon orientation.
        // Projection b of cannon length L,
        // b = L times cos (90 degrees - alpha)
        b = L * Math.cos((90.0 - Alpha) * degreeToRadianConv); // projection of barrel
                                                               // onto x-z plane      
        // Cannon length, xyz.  Lx = b * cos(gamma)
        Lx = b * Math.cos(Gamma * degreeToRadianConv); // x-component of barrel length
        Ly = L * Math.cos(Alpha * degreeToRadianConv); // y-component of barrel length
        Lz = b * Math.sin(Gamma * degreeToRadianConv); // z-component of barrel length

        cosX = Lx / L;
        cosY = Ly / L;
        cosZ = Lz / L;

        // These are the x and z coordinates of the very end of the cannon
        // barrel
        // we'll use these as the initial x and z displacements
        xe = L * Math.cos((90 - Alpha) * degreeToRadianConv) * Math.cos(Gamma * degreeToRadianConv);
        ze = L * Math.cos((90 - Alpha) * degreeToRadianConv) * Math.sin(Gamma * degreeToRadianConv);

        // Now we can calculate the position vector at this time
        // x = vxt = (vm times cos(theta x) * t
        s.i = Vm * cosX * time + xe;
        s.j = (Yb + (L * Math.cos(Alpha * degreeToRadianConv))) + (Vm * cosY * time) - (0.5 * g * (time * time));
        s.k = Vm * cosZ * time + ze;
        // Cutoff the simulation if it's taking too long
        // This is so the program does not get stuck in the while loop
        if (time > 3600) {
            return 3;
        }
        System.out.println("Position Projectile : " + s + " time=" + time);
        return 0;
    } // End of the method, do simulation //

    public String infoDuringSimulation() {
        final StringBuffer buf = new StringBuffer(100);
        buf.append("Time=");
        buf.append(String.format("%.2f", this.time));
        buf.append("s");
        
        buf.append(" X=");
        buf.append(String.format("%.2f", s.i));
        buf.append("m");
        
        buf.append(" Y=");
        buf.append(String.format("%.2f", s.j));
        buf.append("m");
        
        buf.append(" lastTimeHit=");
        buf.append(String.format("%.2f", this.lastTimeHitGround));
        buf.append("s");
        
        buf.append(" lastXhit=");
        buf.append(String.format("%.2f", this.lastXhitGround));
        buf.append("m");
        return buf.toString();
    }
    
}