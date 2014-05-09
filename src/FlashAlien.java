package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class FlashAlien extends Alien
{
	// red aliens get their own graphic
	PImage graphic1;
	PImage graphic2;
	int count = 0;
	boolean switches = false;
	
	public FlashAlien(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png && alien2.png graphic from the data folder
		// store it in our graphics instance variables
		this.graphic1 = this.canvas.loadImage("alien1.png");
		this.graphic2 = this.canvas.loadImage("alien2.png");
	}
	
	// override the superclass display method
	@Override
	public void display()
	{
		// only draw if we are alive & it doesn't want to switch
		if (this.alive && !this.switches)
		{
			// image the graphic to the screen
			canvas.image(this.graphic1, this.x ,this.y, this.size, this.size);
		}
		//only draws if it's alive and it does want to switch
		else if (this.alive && this.switches)
		{
			// image the graphic to the screen
			canvas.image(this.graphic2, this.x ,this.y, this.size, this.size);
		}
		//increases count
		this.count++;
		//every 1/3 of a second it will switch
		if (count % 20 == 0 && this.switches)
			switches = false;
		//if it's not 1/3 of a second it will not switch
		else if (count % 20 == 0 && !this.switches)
			switches = true;
	}
}