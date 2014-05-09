package spaceinvaders;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class SpaceInvaders extends PApplet 
{
	// the user controlled player
	Player thePlayer;
	int playerMovementDirection = 0, score = 0, placeX = 0, placeY = 50;

	// the user controlled missile
	Missile missile;
	
	// our aliens
	Alien[] theBlueAliens, theRedAliens, theFlashAliens, theShrinkAliens;

	//this decides whether or not the splash is needed
	boolean splash = true;

	//loads the splash image
	PImage bg;
	
	public void setup() 
	{
		// set the size of our graphics canvas
		size(800, 600);
		
		// smooth all drawing
		smooth();
		
		// draw all rectangles from their center points
		rectMode(CENTER);

		// create a new instance of our Player class
		this.thePlayer = new Player(this.width/2, this.height-25, this);
		
		// create a new instance of our Missile class
		// (the player only gets one missile - if they shoot more than one then we
		// simply take the one in flight and bring it back down so that it can fire again)
		this.missile = new Missile(0,-50,this);
		
		// creates a row of shrinking aliens
		this.theShrinkAliens = new Alien[10];
		for (int i = 0; i < this.theShrinkAliens.length; i++)
		{
			this.theShrinkAliens[i] = new ShrinkAlien(this.placeX, this.placeY, this);
			placeX+=50;
		}
		placeX = 0;
		placeY+=50;
		
		//creates a row of blue aliens
		this.theBlueAliens = new Alien[10];
		for (int i = 0; i < this.theBlueAliens.length; i++)
		{
			this.theBlueAliens[i] = new BlueAlien(this.placeX, this.placeY, this);
			placeX+=50;
		}
		placeX = 0;
		placeY+=50;
		
		//creates a row of red aliens
		this.theRedAliens = new Alien[10];
		for (int i = 0; i < this.theRedAliens.length; i++)
		{
			this.theRedAliens[i] = new RedAlien(this.placeX, this.placeY, this);
			placeX+=50;
		}
		placeX = 0;
		placeY+=50;
		
		//creates a row of flashing aliens
		this.theFlashAliens = new Alien[10];
		for (int i = 0; i < this.theFlashAliens.length; i++)
		{
			this.theFlashAliens[i] = new FlashAlien(this.placeX, this.placeY, this);
			placeX+=50;
		}
		
		// load in our font so that we can draw text to the screen
		PFont genericFont = loadFont("sansSerifFont24.vlw");
		
		// set the default font as the one we just loaded
		this.textFont( genericFont );
		
		this.bg = loadImage("bg.png");
	}

	public void draw() 
	{
		if (splash)
		{
			image(this.bg, 0, 0, 800, 600);
			if(this.mousePressed)
				splash = false;
		}
		else if(score < 40)
		{	
			// erase the background
			background(0);
			
			// title text
			fill(255);
			text("Space Invaders!", 0, 25);
			text("Score: " + score, 675, 25);
			
			// iterate over all aliens
			for (int i = 0; i < this.theShrinkAliens.length; i++)
			{
				// tell each one to move
				this.theShrinkAliens[i].move();
			
				// check each alien to see if the missile hit them
				// if so the alien will flip its own "alive" variable to false
				boolean hit = this.theShrinkAliens[i].missileHitTest(this.missile.x,  this.missile.y);
			
				// you could do something with "hit" here if you needed to react to an alien being hit
				if (hit)
					score++;
				
				// display each alien
				this.theShrinkAliens[i].display();
			}
			
			for (int i = 0; i < this.theBlueAliens.length; i++)
			{
				// tell each one to move
				this.theBlueAliens[i].move();
			
				// check each alien to see if the missile hit them
				// if so the alien will flip its own "alive" variable to false
				boolean hit = this.theBlueAliens[i].missileHitTest(this.missile.x,  this.missile.y);
			
				// you could do something with "hit" here if you needed to react to an alien being hit
				if (hit)
					score++;
				
				// display each alien
				this.theBlueAliens[i].display();
			}
			
			for (int i = 0; i < this.theRedAliens.length; i++)
			{
				// tell each one to move
				this.theRedAliens[i].move();
			
				// check each alien to see if the missile hit them
				// if so the alien will flip its own "alive" variable to false
				boolean hit = this.theRedAliens[i].missileHitTest(this.missile.x,  this.missile.y);
			
				// you could do something with "hit" here if you needed to react to an alien being hit
				if (hit)
					score++;
				
				// display each alien
				this.theRedAliens[i].display();
			}
			
			for (int i = 0; i < this.theFlashAliens.length; i++)
			{
				// tell each one to move
				this.theFlashAliens[i].move();
			
				// check each alien to see if the missile hit them
				// if so the alien will flip its own "alive" variable to false
				boolean hit = this.theFlashAliens[i].missileHitTest(this.missile.x,  this.missile.y);
			
				// you could do something with "hit" here if you needed to react to an alien being hit
				if (hit)
					score++;
				
				// display each alien
				this.theFlashAliens[i].display();

			}
			// move and display the missile
			this.missile.move();
			this.missile.display();
			
			// move and display the player
			this.thePlayer.move(this.playerMovementDirection);
			this.thePlayer.display();
		}
		else if (this.score == 40 )
		{
			background(0);
			text("YOU WON!!!!!", 335, 275);
			text("Click to Play again", 300, 325);
			if (this.mousePressed)
				splash = true;			
		}
	}

	// every time a key is pressed this method will execute
	public void keyPressed()
	{
		// user hit the 'a' key - indicate that we want to move left
		if (key == 'a')
		{
			this.playerMovementDirection = -1;
		}
		
		// user hit the 'd' key - indicate that we want to move right
		if (key == 'd')
		{
			this.playerMovementDirection = 1;
		}
		
		// user hit the 's' key - reload and fire the missile
		if (key == 's')
		{
			this.missile.reload(this.thePlayer.x, this.thePlayer.y);
		}
	}
	
	// every time a key is released this method will execute
	public void keyReleased()
	{
		// indicate that the player should stop moving
		this.playerMovementDirection = 0;
	}
	
}
