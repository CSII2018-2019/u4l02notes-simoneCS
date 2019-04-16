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
		//g.drawString(" what should I say....? ", 35, 25); 
	}
	
	public Dimension getPreferredSize() { 
		Dimension size = new Dimension(width, height); 
		return size; 
	}
	
	public void convertToGrayscle() { 
		for (int x= 0; x < width; x++) { 
			for (int y= 0; y < height; y++) { 
				//get value for 1 pixel 
				int p = image.getRGB(x,y);
				
				int a = (p>>24) & 0xff; //notation for &
				int r = (p>>16) & 0xff; 
				int g = (p>>8) & 0xff; 
				int b = (p>>0) & 0xff; 
				
				//calculate average 
				//leave alpha value alone it's transparency 
				//int avg = (r + g + b)/3; 
				
				//reset our pixel 
				//would be avg if doing grayscale 
				p = (a <<24) | (255 - r<<16) | (2555 - b<<8) | (255 - g<<0); //so different shades of grey 
				image.setRGB(x, y, p); 
			}
		}
	}
	
	public void convertToSepia() { 
		for (int x= 0; x < width; x++) { 
			for (int y= 0; y < height; y++) { 
				
				int p = image.getRGB(x,y); 
				
				int a = (p>>24) & 0xff; 
				int r = (p>>16) & 0xff; 
				int g = (p>>8) & 0xff; 
				int b = (p>>0) & 0xff;
				
				int newRed = (int) (0.393 * r + 0.769 * g + 0.189*b); 
				int newGreen = (int)(0.349 * r + 0.686 * g + 0.168* b); 
				int newBlue = (int) (0.272 * r + 0.534 * g + 0.131*b); 
				
				if(newRed > 255) { 
					newRed = 255; 
				}
				
				if(newGreen > 255) { 
					newGreen = 255; 
				}
				
				if(newBlue > 255) { 
					newBlue = 255; 
				}
				
				p = (a <<24) | (newRed <<16) | (newGreen<<8) | (newBlue <<0);  
				image.setRGB(x, y, p);
	}
		}
	}
	
	public void convertToSomething() { 
		for (int x= 0; x < width; x++) { 
			for (int y= 0; y < height; y++) { 
				
				int p = image.getRGB(x,y); 
				
				int a = (p>>21) & 0xff; 
				int r = (p>>16) & 0xff; 
				int g = (p>>7) & 0xff; 
				int b = (p>>0) & 0xff;
				
				int newRed = (int) (0.990 * r + 0.769 * g + 0.559*b); 
				int newGreen = (int)(0.698 * r + 0.686 * g + 0.168* b); 
				int newBlue = (int) (0.272 * r + 0.21 * g + 0.704*b); 
				
				if(newRed > 255) { 
					newRed = 255; 
				}
				
				if(newGreen > 255) { 
					newGreen = 255; 
				}
				
				if(newBlue > 255) { 
					newBlue = 255; 
				}
				
				p = (a <<24) | (newRed <<16) | (newGreen<<8) | (newBlue <<0);  
				image.setRGB(x, y, p);
	}
		}
	}
}
