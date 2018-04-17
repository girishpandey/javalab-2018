package com.girish.imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageMerger {

	public static void main(String[] args) {
		imageProcesser();
	}

	public static void imageProcesser() {
		int rows = 1; // we assume the no. of rows and cols are known and each chunk has equal width and height
		int cols = 2;
		int chunks = rows * cols;

		int chunkWidth, chunkHeight;
		int type;
		
		// fetching image files
		File[] imgFiles = new File[chunks];
		int count = 1;
		for (int i = 0; i < chunks; i++) {
			imgFiles[i] = new File("/home/girishpandey/Desktop/desktop/myimage/PP_SCAN_" + count + ".jpg");
			count++;
		}

		// creating a bufferd image array from image files
		BufferedImage[] buffImages = new BufferedImage[chunks];
		for (int i = 0; i < chunks; i++) {
			try {
				buffImages[i] = ImageIO.read(imgFiles[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		type = buffImages[0].getType();
		chunkWidth = buffImages[0].getWidth();
		chunkHeight = buffImages[0].getHeight();

		// Initializing the final image
		BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);

		int num = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
				num++;
			}
		}
		System.out.println("String Image concatenation.....");
		try {
			boolean staus = ImageIO.write(finalImg, "jpeg", new File("/home/girishpandey/Desktop/desktop/myimage/PP_SCAN_FIN.jpg"));
			if(staus) {
				System.out.println("Image concatenated sucessfully.....");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
