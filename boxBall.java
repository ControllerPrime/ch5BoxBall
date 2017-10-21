import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Expanded the original and added the left, right and ceiling so the balls do not excape.
 * Added random class for speed, size and color of the balls. 
 * Added the ability for the user to add a number of balls.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author James Sepanak
 *
 * @version 2017.10.21
 */

public class boxBall
{
    private static final int GRAVITY = 1;  // effect of gravity

    private int ballDegradation = 1;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter = 75;
    private final int rightWall;
    private final int leftWall;
    private final int ceiling;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private Canvas canvas;
    private int ySpeed = 25;                // initial downward speed
    private int xSpeed = 35;
    private int direction = 1;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public boxBall(int groundPos, int lWall, int cWall, int rWall, Canvas drawingCanvas)
    {
        //diameter = ballDiameter;
        Random rD = new Random(); //random size for diameter of the balls
        diameter = rD.nextInt(diameter) + 1;
        Random xS = new Random(); //random xspeed for the balls
        xSpeed = xS.nextInt(xSpeed) + 1;
        Random yS = new Random(); //random yspeed for the balls
        ySpeed = yS.nextInt(ySpeed) + 1;
        //color = ballColor;
        // get random color
        Random redrand = new Random(); //random red color
        int redlevel = redrand.nextInt(250) + 2;
        Random greenrand = new Random(); //random green color
        int greenlevel = greenrand.nextInt(250) + 2;
        Random bluerand = new Random(); //random blue color
        int bluelevel = bluerand.nextInt(250) + 2;
        
        color = new Color(redlevel, greenlevel, bluelevel);
        
        groundPosition = groundPos;
        canvas = drawingCanvas;
        rightWall = rWall;
        leftWall = lWall;
        ceiling = cWall;
        //xPosition = xPos;
        Random xp = new Random(); //random x position for each ball
        xPosition = xp.nextInt(rightWall - diameter - leftWall) + leftWall + 1;
        //yPosition = yPos;
        Random yp = new Random(); //random y position for each ball
        yPosition = yp.nextInt(groundPosition - diameter - ceiling) + ceiling + 1;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += (xSpeed * direction);

        // check if it has hit the ground
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = -ySpeed + ballDegradation; 
        }
        
        // check if it has hit the Right Wall
        if(xPosition >= (rightWall - diameter)) {
            xPosition = (int)(rightWall - diameter);
            direction = -1;
        }
        
        // check if it has hit the Left Wall
        if(xPosition <= leftWall + 1) {
            xPosition = leftWall + 1;
            direction = 1;
        }
        
        // check if it has hit the ceiling
        if(yPosition <= ceiling + 1) {
            yPosition = (ceiling + 1);
            ySpeed = -ySpeed + ballDegradation; 
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
