import acm.graphics.*;
import acm.program.*;

public class ImageRotater extends GraphicsProgram {
	
	public void run() {
		GImage oldImage = new GImage(imageFile);
		double scaleSize = (getWidth() * .4) / oldImage.getWidth();
		oldImage.scale(scaleSize);
		add(oldImage, getWidth()/4 - oldImage.getWidth()/2, (getHeight() - oldImage.getHeight())/2);
		GImage newImage = rotateLeft(oldImage);
		scaleSize = (getWidth() * .4) / newImage.getWidth();
		newImage.scale(scaleSize);
		add(newImage, 3*getWidth()/4 - newImage.getWidth()/2, (getHeight() - newImage.getHeight())/2);
	}

	private GImage rotateLeft(GImage image){
		int[][] oldPixels = image.getPixelArray();
		int width = oldPixels[0].length;
		int height = oldPixels.length;
		int newPixels[][] = new int[width][height];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				newPixels[width - j - 1][i] = oldPixels[i][j];
			}
		}
		return new GImage(newPixels);
	}
	
	private static final String imageFile = "";
}

