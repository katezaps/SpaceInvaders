package spaceinvaders;

import processing.core.PApplet;

//comment minim import statements out if you aren't using audio
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class Missile
{
	// store our current position on the screen
	public int x;
	public int y;
	
	// store a reference to our graphical canvas
	public PApplet canvas;

	// create a new audio framework
	// note you can comment these lines out if you run into trouble on your own computer
	// (you won't lose points - audio can be buggy in Processing)
	Minim minim;
	AudioPlayer fireSound;
	AudioPlayer explosionSound;

	// constructor
	public Missile(int x, int y, PApplet canvas)
	{
		this.x = x;
		this.y = y;
		this.canvas = canvas;

		// create a new audio framework
		// comment this out if you aren't using audio
		minim = new Minim(canvas);
		
		// load in the sound effect for shooting a missile and store
		// it in an AudioPlayer object
		// comment this out if you aren't using audio
		fireSound = minim.loadFile("missile.mp3", 2048);
	}

	// missiles consistently move up towards the top of the screen
	public void move()
	{
		this.y -= 5;
	}
	
	// draw the missile on the screen
	public void display()
	{
		this.canvas.fill(225);
		this.canvas.rect(x,y,5,25);
	}
	
	// "reload" the missile - essentially place it on top of the player
	// and trigger the sound effect
	public void reload(int x, int y)
	{
		this.x = x;
		this.y = y;

		// comment this out if you aren't using audio
		this.fireSound.pause();
		this.fireSound.rewind();
		this.fireSound.play();
	}
}
