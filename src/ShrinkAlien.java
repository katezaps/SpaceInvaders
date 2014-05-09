package spaceinvaders;

import processing.core.PApplet;
import processing.core.PImage;

public class ShrinkAlien extends Alien
{
	// blue aliens get their own graphic
	PImage graphic;
	public int prevSize = 26;
	
	public ShrinkAlien(int x, int y, PApplet canvas)
	{
		super(x,y,canvas);
		
		// laod in the alien1.png graphic from the data folder
		// store it in our graphic instance variable
		this.graphic = this.canvas.loadImage("alien1.png");
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
		//checks if the graphic needs to decrease
		if (((this.prevSize > this.size) && size > 5) || this.size == 25)
		{
			//decreases the size
			this.prevSize = this.size;
			this.size--;
		}
		//checks if the graphic needs to increase
		else if (((this.prevSize < this.size) && size <= 25) || this.size == 5)
		{
			//increases the size
			this.prevSize = this.size;
			this.size++;
		}
	}
	
	@Override
	public void move()
	{
		// only update our x position based on our direction
		this.x += this.direction;

		// if we hit the right edge we should turn around
		// and move down by one row
		if (this.x > this.canvas.width)
		{
			this.direction *= -1;
			this.x = this.canvas.width;
			this.y += 25;
		}
		
		// if we hit the left edge we should turn around
		// and move down by one row
		if (this.x < 0 - 25)
		{
			this.direction *= -1;
			this.x = 0 - 25;
			this.y += 25;
		}
	}
}