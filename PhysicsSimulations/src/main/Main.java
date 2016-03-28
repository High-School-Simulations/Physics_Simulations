package main;

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import ElasticCollisions2D.MainPanel;

public class Main {
	static final int simCount = 1;
	static JPanel simulations[] = new JPanel[simCount];
	static String simulationNames[] = {"Simple Elastic Collisions in 2D"};
	static JLabel labels[] = new JLabel[simCount];
	static JButton buttons[] = new JButton[simCount];
	static JButton back = new JButton("Back");
	static JFrame frame;
	
	static ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.getContentPane().removeAll();
			frame.setLayout(new BorderLayout());
			for(int a = 0; a < simCount; a++) {
				if(e.getSource() == buttons[a]) {
					frame.add(new JScrollPane(simulations[a]),BorderLayout.CENTER);
					frame.add(back, BorderLayout.SOUTH);
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.validate();
					return;
				}
			}
		}
	};
	
	public static void main(String args[]) {
		
		simulations[0] = new MainPanel();
		
		for(int a = 0; a < simCount; a++) {
			labels[a] = new JLabel(simulationNames[a]);
			buttons[a] = new JButton("Start");
			buttons[a].addActionListener(al);
		}
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				initMainList();
			}
		});
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {}
		
		frame = new JFrame("Physics Simulations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMainList();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void initMainList() {
		
		JLabel mLabel = new JLabel("List of Simulations");
		mLabel.setFont(new Font("Callibri", Font.BOLD, 20));
		Font font = mLabel.getFont();
		@SuppressWarnings("unchecked")
		Map<TextAttribute, Integer> attributes = (Map<TextAttribute, Integer>) font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		mLabel.setFont(font.deriveFont(attributes));
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		frame.add(mLabel, gbc);
		gbc.gridwidth = 1;
		
		int ycount = 1;
		
		for(int a = 0; a < simCount; a++) {
			gbc.gridx = 0;
        	gbc.gridy = ycount;
            frame.add(labels[a], gbc);
            gbc.gridx = 1;
            frame.add(buttons[a], gbc);
            ycount++;
		}
		
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.validate();
	}
}