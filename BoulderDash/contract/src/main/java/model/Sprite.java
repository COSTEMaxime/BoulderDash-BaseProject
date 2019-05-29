package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	private static final int SIZE_PX = 16;
	private static BufferedImage spriteSheetObjetcs = null;
	private static BufferedImage spriteSheetPlayer = null;

	private Image[] images;
	private int imageCount;

	private char consoleImage;

	private boolean isImageLoaded;

	public Sprite(final char consoleImage, final int index, final int world)
	{
		this.imageCount = 4;
		this.images = new Image[imageCount];
		this.consoleImage = consoleImage;
		this.setImageSetObjects(index, world * imageCount, imageCount);
	}
	
	public Sprite(final int x, final int y, final int imageCount)
	{
		System.out.println("Created player");
		this.imageCount = imageCount;
		this.images = new Image[imageCount];
		this.consoleImage = '+';
		this.setImageSetPlayer(x, y, imageCount);
	}

	private void setImageSetObjects(final int x, final int y, final int imagesCount)
	{
		if (spriteSheetObjetcs == null)
		{
			try {
				spriteSheetObjetcs = ImageIO.read(new File("images/objects.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < imagesCount; i++)
		{
			this.images[i] = spriteSheetObjetcs.getSubimage(x * Sprite.SIZE_PX, (y + i) * Sprite.SIZE_PX, Sprite.SIZE_PX, Sprite.SIZE_PX);
		}
		
		this.isImageLoaded = true;
	}
	
	private void setImageSetPlayer(final int x, final int y, final int imagesCount)
	{
		if (spriteSheetPlayer == null)
		{
			try {
				spriteSheetPlayer = ImageIO.read(new File("images/player.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < imagesCount; i++)
		{
			this.images[i] = spriteSheetPlayer.getSubimage((x + i) * Sprite.SIZE_PX, y* Sprite.SIZE_PX, Sprite.SIZE_PX, Sprite.SIZE_PX);
		}
		
		this.isImageLoaded = true;
	}

	public final Image getImage(final int index) {
		return this.images[index];
	}

	public final char getConsoleImage() {
		return this.consoleImage;
	}

	public final boolean isImageLoaded() {
		return this.isImageLoaded;
	}

	public int getImageCount() {
		return imageCount;
	}
}
