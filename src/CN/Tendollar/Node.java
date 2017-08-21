package CN.Tendollar;

import java.awt.*;

@SuppressWarnings("all")
public class Node {

    public static final int NODE_WIDTH = 15, NODE_HEIGHT = 15;
    private Color color;
    private int xCoordinate, yCoordinate;

    public Node(int xCoordinate, int yCoordinate, Color color) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void drawNode(Graphics2D graphics2D) {
        graphics2D.setColor(this.color);
        graphics2D.fillRect(this.xCoordinate, this.yCoordinate, Node.NODE_WIDTH, Node.NODE_HEIGHT);
    }

    public Rectangle getRectangle() {
        return new Rectangle(this.xCoordinate, this.yCoordinate, Node.NODE_WIDTH, Node.NODE_HEIGHT);
    }
}
