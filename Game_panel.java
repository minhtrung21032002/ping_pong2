
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

    public class Game_panel extends JPanel implements Runnable {



        static final int GAME_WIDTH = 1000; // setting the game_width window
    
        static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5)); // setting the game_height window

    
        static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT); // passing dimension to prefredded size in game_panel

    
        static final int BALL_DIAMETER = 20; 
    
        static final int PADDLE_WIDTH = 25;
    
        static final int PADDLE_HEIGHT = 100;
    
        Thread gameThread; // game loop
    
        Image image; // using image
    
        Graphics graphics; // using graphics
    
        Random random; // use in ball for random x,y movement
    
        Paddle paddle1;
    
        Paddle paddle2;
    
        Ball ball;
    
        Score score;
    
        
    
        Game_panel(){
    
            newPaddles();
    
            newBall();


    
    
            this.setFocusable(true); // focus on retrive keyboard action
    
            this.addKeyListener(paddle1);  // retrive keyboard action of paddle1
            this.addKeyListener(paddle2); // retrive keyboard action of paddle 2
    
            this.setPreferredSize(SCREEN_SIZE); // setting dimension of screen
    
            score = new Score(GAME_WIDTH,GAME_HEIGHT);
    
            gameThread = new Thread(this); // game loop
    
            gameThread.start();
    
        }
    
        
        // this is use to create a ball
        public void newBall() {
    
            random = new Random();
    
            ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER); // Using the set the intial 
    
        }
        // use to create the paddle

        public void newPaddles() {
    
            paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
    
            paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    
        }
        // draw function of ball, paddle
        public void paint(Graphics g) {
    
            image = createImage(getWidth(),getHeight());
    
            graphics = image.getGraphics();
    
            paddle1.draw(graphics);
    
            paddle2.draw(graphics);
    
            ball.draw(graphics);
     
            score.draw(graphics);

            g.drawImage(image,0,0,this);

            
    
        }
    
        
        // update cordinate of x,y or paddle or ball
    
        public void move() {
    
            paddle1.move();
    
            paddle2.move();
    
            ball.move();
    
        }
    
        public void checkCollision() {
    
            
    
            //bounce ball off top & bottom window edges
    
            if(ball.y <=0) {
    
                ball.setYDirection(-ball.yVelocity); // if the ball moving in y direction meanning out of ball, we set the opposite direction meaning moving opposite
    
            }
    
            if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) { // if the ball moving in y direction meanning out of ball, we set the opposite direction meaning moving opposite
    
                ball.setYDirection(-ball.yVelocity);
    
            }
    
            //bounce ball off paddles
    
            if(ball.intersects(paddle1)) {
    
                ball.xVelocity = ball.xVelocity*-1; // flip the ball to other side of x direction
    
                ball.xVelocity++; 

        
                ball.yVelocity = ball.yVelocity*-1; // flip the ball to other sign of y direction

                ball.yVelocity++; 
    
                ball.setXDirection(ball.xVelocity);
    
                ball.setYDirection(ball.yVelocity);
    
            }
    
            if(ball.intersects(paddle2)) {
    
                ball.xVelocity = ball.xVelocity*-1;
    
                ball.xVelocity++; //optional for more difficulty

                ball.yVelocity = ball.yVelocity*-1;

                ball.yVelocity++;
    
                ball.setXDirection(-ball.xVelocity); // in this case this because we need to opposite the sign
    
                ball.setYDirection(ball.yVelocity);
    
            }
    
            //stops paddles at window edges
    
            if(paddle1.y<=0) // meaning that if padddle y coordinate is less than 0, move up out , setting y to 0
    
                paddle1.y=0;
    
            if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
    
                paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT; // meaning that if padddle y coordinate is greater than  move up out , setting y to 0,
    
            if(paddle2.y<=0) // the same paddle 1
    
                paddle2.y=0;
    
            if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) // the same paddle 1
    
                paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
    
            //give a player 1 point and creates new paddles & ball
    
            if(ball.x <=0) {
    
                score.player2++;
    
                newPaddles();
    
                newBall();
    
                System.out.println("Player 2: "+score.player2);
    
            }
    
            if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
    
                score.player1++;
    
                newPaddles();
    
                newBall();
    
                System.out.println("Player 1: "+score.player1);
    
            }
    
        }
    
        public void run() {
    
              //game loop
    
            long lastTime = System.nanoTime();
    
            double amountOfTicks =60.0;
    
            double ns = 1000000000 / amountOfTicks;
    
            double delta = 0;
    
            while(true) {
    
                long now = System.nanoTime();
    
                delta += (now -lastTime)/ns;
    
                lastTime = now;
    
                if(delta >=1) {
    
                    move(); // update postion of ball and paddle 
    
                    checkCollision(); // detect collision , still using update postion of ball and paddle to solve the collision problem
    
                    repaint(); // repaint graphics 
    
                    delta--;
    
                }
    
            }
            
    
        }
         } 
        
    
    
