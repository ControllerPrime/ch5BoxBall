import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author James Sepanak
 * @version 2017.10.21
 */

public class BallDemo   
{
    private int height = 500;
    private int width = 600;
    private int offset = 10;
    
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo(int numBalls)
    {
        myCanvas = new Canvas("Ball Demo", width, height);
        bounce(numBalls);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int totalBalls)
    {
        int ground = height - offset;   // position of the ground line
        int ceiling = offset;
        int LWall = offset;
        int RWall = width - offset;
        
        //makes the aray for how many balls where pasted from user
        boxBall balls[] = new boxBall[totalBalls];
        
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(LWall, ground, RWall, ground);
        myCanvas.drawLine(LWall, ceiling, RWall, ceiling);
        myCanvas.drawLine(LWall, ceiling, LWall, ground);
        myCanvas.drawLine(RWall, ceiling, RWall, ground);
        
        //creates the balls
        for ( int i = 0; i<totalBalls; i++) {
        //crate and show the balls
        //BoxBall ball = new BoxBall(Color.BLUE, ceiling, leftWall, rightWall, floor, 1, myCanvas);
        balls[i] = new boxBall(ground, LWall, ceiling, RWall, myCanvas);
        balls[i].draw();
    }
        
        //boxBall ball = new boxBall(ground, LWall, ceiling, RWall, myCanvas);
        //ball.draw();
        
        // crate and show the balls
        //boxBall ball = new boxBall(ground, RWall, LWall, ceiling, myCanvas);
        //ball.draw();
        //boxBall ball2 = new boxBall(70, 80, 20, Color.RED, ground, RWall, myCanvas);
        //ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);   // small delay
            for (int i=0; i<totalBalls; i++) { //moves the balls
            balls[i].move();
        }
        //ball.move();
            //ball2.move();
            // stop once ball has travelled a certain distance on x axis
            /*if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }*/
        }
    }
}
