package VectorAddition;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
 
import javax.swing.JComponent;
 
public class Transform {
 
	private static TransformingCanvas canvas;
 
	private static class TransformingCanvas extends JComponent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private double translateX;
		private double translateY;
		private double scale;
 
		@SuppressWarnings("unused")
		TransformingCanvas() {
			translateX = 0;
			translateY = 0;
			scale = 1;
			setOpaque(true);
			setDoubleBuffered(true);
		}
 
		@Override public void paint(Graphics g) {
 
			AffineTransform tx = new AffineTransform();
			tx.translate(translateX, translateY);
			tx.scale(scale, scale);
			Graphics2D ourGraphics = (Graphics2D) g;
			ourGraphics.setColor(Color.WHITE);
			ourGraphics.fillRect(0, 0, getWidth(), getHeight());
			ourGraphics.setTransform(tx);
			ourGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			ourGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);			
			ourGraphics.setColor(Color.BLACK);
			ourGraphics.drawRect(50, 50, 50, 50);
			ourGraphics.fillOval(100, 100, 100, 100);
			ourGraphics.drawString("Test Affine Transform", 50, 30);
			// super.paint(g);
		}
	}
 
	@SuppressWarnings("unused")
	private static class TranslateHandler implements MouseListener,
			MouseMotionListener {
		private int lastOffsetX;
		private int lastOffsetY;
 
		public void mousePressed(MouseEvent e) {
			lastOffsetX = e.getX();
			lastOffsetY = e.getY();
		}
 
		public void mouseDragged(MouseEvent e) {

			int newX = e.getX() - lastOffsetX;
			int newY = e.getY() - lastOffsetY;
 
			lastOffsetX += newX;
			lastOffsetY += newY;
 
			canvas.translateX += newX;
			canvas.translateY += newY;
			
			canvas.repaint();
		}
 
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
 
	@SuppressWarnings("unused")
	private static class ScaleHandler implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
				
				canvas.scale += (.1 * e.getWheelRotation());

				canvas.scale = Math.max(0.00001, canvas.scale); 
				canvas.repaint();
			}
		}
	}
 
}
