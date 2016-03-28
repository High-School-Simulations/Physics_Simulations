package ElasticCollisions2D;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    SimulationData simObj;
    JSlider sliders[] = new JSlider[8];
    JLabel labels[] = new JLabel[8];
    JButton startButton;
	
	ChangeListener cl = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
            if(e.getSource() == sliders[0]) {
                labels[0].setText("x1 = " + sliders[0].getValue());
                if(!(simObj.ball2.intersects(new Rectangle(sliders[0].getValue(), simObj.y1, 40, 40))))
                	simObj.x1 = sliders[0].getValue();
            }
            else if(e.getSource() == sliders[1]) {
                labels[1].setText("y1 = " + sliders[1].getValue());
                if(!(simObj.ball2.intersects(new Rectangle(simObj.x1, sliders[1].getValue(), 40, 40))))
                	simObj.y1 = sliders[1].getValue();
            }
            else if(e.getSource() == sliders[2]) {
                labels[2].setText("x2 = " + sliders[2].getValue());
                if(!(simObj.ball1.intersects(new Rectangle(sliders[2].getValue(), simObj.y2, 40, 40))))
                	simObj.x2 = sliders[2].getValue();
            }
            else if(e.getSource() == sliders[3]) {
                labels[3].setText("y2 = " + sliders[3].getValue());
                if(!(simObj.ball1.intersects(new Rectangle(simObj.x2, sliders[3].getValue(), 40, 40))))
                	simObj.y2 = sliders[3].getValue();
            }   
            else if(e.getSource() == sliders[4]) {
                labels[4].setText("ux1 = " + sliders[4].getValue());
                simObj.ux1 = sliders[4].getValue();
            }
            else if(e.getSource() == sliders[5]) {
                labels[5].setText("uy1 = " + sliders[5].getValue());
                simObj.uy1 = sliders[5].getValue();
            }
            else if(e.getSource() == sliders[6]) {
                labels[6].setText("ux2 = " + sliders[6].getValue());
                simObj.ux2 = sliders[6].getValue();
            }
            else if(e.getSource() == sliders[7]) {
                labels[7].setText("uy2 = " + sliders[7].getValue());
                simObj.uy2 = sliders[7].getValue();
            }
        }
    };
    
    public ControlPanel(SimulationData obj) {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {}
    	
    	simObj = obj;
    	Border loweredEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
 		setBorder(loweredEtched);
    	
    	sliders[0] = new JSlider(0, 553, 0);
        sliders[1] = new JSlider(0, 327, 0);
        sliders[2] = new JSlider(0, 553, 553);
        sliders[3] = new JSlider(0, 327, 327);
        
        for(int a = 4; a < 6; a++)
            sliders[a] = new JSlider(-15, 15, 10);
        for(int a = 6; a < 8; a++)
            sliders[a] = new JSlider(-15, 15, -10);
        
        labels[0] = new JLabel("x1 = 0");
        labels[1] = new JLabel("y1 = 0");
        labels[2] = new JLabel("x2 = 553");
        labels[3] = new JLabel("y2 = 327");
        labels[4] = new JLabel("ux1 = 10");
        labels[5] = new JLabel("uy1 = 10");
        labels[6] = new JLabel("ux2 = -10");
        labels[7] = new JLabel("uy2 = -10");
        startButton = new JButton("Start");
        
        setLayout(new GridBagLayout());
		
        GridBagConstraints gbc = new GridBagConstraints();
		int ycount = 0;
		
        for(int a = 0; a < 8;a += 2) {
        	gbc.gridx = 0;
        	gbc.gridy = ycount;
            add(labels[a], gbc);
            gbc.gridx = 1;
            add(labels[a+1], gbc);
            ycount++;
            gbc.gridx = 0;
            gbc.gridy = ycount;
            add(sliders[a], gbc);
            gbc.gridx = 1;
            add(sliders[a+1], gbc);
            ycount++;
        }
        
        gbc.gridx = 0;
        gbc.gridy = ycount;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15,0,0,0);
        add(startButton, gbc);
        
        for(int a = 0; a < 8; a++)
            sliders[a].addChangeListener(cl);
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(simObj.started == false) {
                	simObj.started = true;
               	 	startButton.setText("Stop");
               	 	for(int a = 0; a < 8; a++)
               	 		sliders[a].setEnabled(false);
                }
                else {
                	simObj.started = false;
                    startButton.setText("Start");
                    for(int a = 0; a < 8; a++)
                        sliders[a].setEnabled(true);
                    refresh();
                }
            }
        });
        
        setPreferredSize(new Dimension(600,237));
    }
    
    public void refresh()
    {
        sliders[0].setValue(0);
        sliders[1].setValue(0);
        sliders[2].setValue(553);
        sliders[3].setValue(327);
        for(int a = 4; a < 6; a++)
            sliders[a].setValue(10);
        for(int a = 6; a < 8; a++)
            sliders[a].setValue(-10);
        labels[0].setText("x1 = 0");
        labels[1].setText("y1 = 0");
        labels[2].setText("x2 = 553");
        labels[3].setText("y2 = 327");
        labels[4].setText("ux1 = 10");
        labels[5].setText("uy1 = 10");
        labels[6].setText("ux2 = -10");
        labels[7].setText("uy2 = -10");
    
        simObj.refresh();
    }
}
