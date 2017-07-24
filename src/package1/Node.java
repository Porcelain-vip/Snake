package package1;

import java.awt.*;

@SuppressWarnings("all")
public class Node {

    public static final int NODEWIDTH = 15, NODEHEIGHT = 15;
    Color color;
    private int xCoordinate, yCoordinate;
    Rectangle rectangle = new Rectangle(xCoordinate, yCoordinate, Node.NODEWIDTH, Node.NODEHEIGHT);

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public Node(int xCoordinate, int yCoordinate, Color color) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
    }

    public void drawNode(Graphics2D graphics2D) {
        graphics2D.setColor(this.color);
        graphics2D.fillRect(this.xCoordinate, this.yCoordinate, Node.NODEWIDTH, Node.NODEHEIGHT);
    }

    public Rectangle getRectangle() {
        rectangle.x = this.xCoordinate;
        rectangle.y = this.yCoordinate;
        return rectangle;
    }
}
