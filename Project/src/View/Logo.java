package View;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Logo {

	private JLabel logoLabel;
	private ImageIcon logoIcon;
	
	
	public Logo(int width, int height) {
		
		BufferedImage logo = getImage("/Datab-Image-Logo.png");
		
		this.logoIcon = resizeImage(logo, width, height);
		this.logoLabel = new JLabel(resizeImage(logo, width, height));
	}
	
	public JLabel getLogoLabel() {
		return this.logoLabel;
	}
	
	public ImageIcon getLogoIcon() {
		return this.logoIcon;
	}
	
	private BufferedImage getImage(String path) {
		
		java.net.URL url = getClass().getResource(path);
		if (url != null)
			try {
				return (ImageIO.read(url));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	private ImageIcon resizeImage(BufferedImage initialImage, int width, int height) {
		
		if (initialImage == null)
			return null;
		
		Image newImage = initialImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		
		return new ImageIcon(newImage);
	}
}
