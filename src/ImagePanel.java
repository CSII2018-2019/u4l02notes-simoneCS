import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	//what variables are needed?
	private int width; 
	private int height; 
	
	BufferedImage image = null;
	
	public ImagePanel(String fn){
		//add code here
		image = readImageFile(this, fn); //fn = file name 
		width = image.getWidth(); 
		height = image.getHeight();
	}
	
	public static BufferedImage readImageFile(Object requestor, String fileName){
		BufferedImage image = null;
		try {
			InputStream input = requestor.getClass().getResourceAsStream(fileName);
			image = ImageIO.read(input);
		} catch (IOException e){
			String message = "The image file " + fileName + " could not be opened.";
			JOptionPane.showMessageDialog(null, message);
		}
		return image;
	}
	
	public void paintComponent(Graphics g) { 
		g.setFont(new Font ("TimesRoman", Font.BOLD, 15));
		g.drawImage(image, 0, 0, null); 
		g.drawString(" what should I say....? ", 35, 25);
	}
	
	public Dimension getPreferredSize() { 
		Dimension size = new Dimension(width, height); 
		return size; 
	}

}
