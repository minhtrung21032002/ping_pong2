
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle implements KeyListener {
    
    int id;

	int yVelocity;

	int speed = 10;

	

	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){

		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);

		this.id=id;

	}

	

	public void keyPressed(KeyEvent e) {

		switch(id) {

		case 1:

			if(e.getKeyCode()==KeyEvent.VK_W) { // press W to increase speed which will increase y velocity

				setYDirection(-speed);

			}

			if(e.getKeyCode()==KeyEvent.VK_S) { // press s to decrease speed which will increase y velocity

				setYDirection(speed);

			}

			break;

		case 2:

			if(e.getKeyCode()==KeyEvent.VK_UP) { // using UP arrow instead of W

				setYDirection(-speed);

			}

			if(e.getKeyCode()==KeyEvent.VK_DOWN) { // using DOWN arrow

				setYDirection(speed);

			}

			break;

		}

	}

	  public void keyReleased(KeyEvent e) {

		switch(id) {

		case 1:

			if(e.getKeyCode()==KeyEvent.VK_W) {

				setYDirection(0);

			}

			if(e.getKeyCode()==KeyEvent.VK_S) {

				setYDirection(0);

			}

			break;

		case 2:

			if(e.getKeyCode()==KeyEvent.VK_UP) {

				setYDirection(0);

			}

			if(e.getKeyCode()==KeyEvent.VK_DOWN) {

				setYDirection(0);

			}

			break;

		}

	} 

	public void keyTyped(KeyEvent E){
		
	}
	

	public void setYDirection(int yDirection) {

		yVelocity = yDirection;

	}

	public void move() {

		y= y + yVelocity; // y increase => move down, y decrease move up

	}

	public void draw(Graphics g) {

		if(id==1)

			g.setColor(Color.blue);

		else

			g.setColor(Color.red);

		g.fillRect(x, y, width, height);

	}

        }
        

