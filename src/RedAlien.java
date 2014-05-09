package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class RedAlien extends Alien
{
	// red aliens get their own graphic
	PImage graphic;
	
	public RedAlien(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png graphic from the data folder
		// store it in our graphic instance variable
		this.graphic = this.canvas.loadImage("alien2.png");
	}
	
	// override the superclass display method
	@Override
	public void display()
	{
		// only draw if we are alive
		if (this.alive)
		{
			// image the graphic to the screen
			canvas.image(this.graphic, this.x ,this.y, this.size, this.size);
		}
	}
}
