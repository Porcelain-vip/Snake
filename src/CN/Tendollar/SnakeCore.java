package CN.Tendollar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SnakeCore {
    public List<Node> snakeBody = Collections.synchronizedList(new ArrayList<>());
    //最开始赋予贪吃蛇一个方向
    Direction direction = Direction.RIGHT;
    Direction tmpDirection = direction;

    public SnakeCore() {
        snakeBody.add(new Node(8, 30, Color.black));
        snakeBody.add(new Node(23, 30, Color.black));
    }

    public boolean isTouchingFood(Node food) {
        return this.getHead().getRectangle().intersects(food.getRectangle());
    }

    public boolean isTouchingItself() {

        if (snakeBody.size() >= 4) {
            Node head = this.getHead();
            for (int i = 3; i < snakeBody.size(); ++i) {
                if (head.getxCoordinate() == snakeBody.get(i).getxCoordinate() &&
                        head.getyCoordinate() == snakeBody.get(i).getyCoordinate()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawBody(Graphics2D graphics2D) {
        for (Node node : snakeBody)
            node.drawNode(graphics2D);
    }

    public void move() {
        synchronized (this) {
            addHead();
            removeTail();
        }
    }

    public Node initFood() {
        int xCoordinate = new Random().nextInt(780) + 8;
        int yCoordinate = new Random().nextInt(585) + 30;
        return new Node(xCoordinate, yCoordinate, Color.blue);
    }

    public Node getHead() {
        return snakeBody.get(0);
    }

    public synchronized void removeTail() {
        snakeBody.remove((snakeBody.size() - 1));
    }

    public synchronized void addHead() {

        Node head = this.getHead();

        switch (this.tmpDirection) {
            case LEFT:
                if (this.direction == Direction.RIGHT) {
                    if (head.getxCoordinate() > 788) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate() + Node.NODE_WIDTH,
                                        head.getyCoordinate(),
                                        Color.RED));
                    }
                } else {
                    this.direction = Direction.LEFT;
                    if (head.getxCoordinate() < 8) {
                        System.exit(0);
                    }
                    this.snakeBody.add(0,
                            new Node(head.getxCoordinate() - Node.NODE_WIDTH,
                                    head.getyCoordinate(),
                                    Color.GREEN));
                }
                break;

            case RIGHT:
                if (this.direction == Direction.LEFT) {
                    if (head.getxCoordinate() < 8) {
                        System.exit(0);
                    }
                    this.snakeBody.add(0,
                            new Node(head.getxCoordinate() - Node.NODE_WIDTH,
                                    head.getyCoordinate(),
                                    Color.CYAN));
                } else {
                    this.direction = Direction.RIGHT;
                    if (head.getxCoordinate() > 788) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate() + Node.NODE_WIDTH,
                                        head.getyCoordinate(),
                                        Color.MAGENTA));
                    }
                }
                break;

            case UP:
                if (this.direction == Direction.DOWN) {
                    if (head.getyCoordinate() > 615) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate(),
                                        head.getyCoordinate() + Node.NODE_HEIGHT,
                                        Color.GREEN));
                    }
                } else {
                    this.direction = Direction.UP;
                    if (head.getyCoordinate() < 30) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate(),
                                        head.getyCoordinate() - Node.NODE_HEIGHT,
                                        Color.BLUE));
                    }
                }
                break;

            case DOWN:
                if (this.direction == Direction.UP) {
                    if (head.getyCoordinate() < 30) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate(),
                                        head.getyCoordinate() - Node.NODE_HEIGHT,
                                        Color.RED));
                    }
                } else {
                    this.direction = Direction.DOWN;
                    if (head.getyCoordinate() > 615) {
                        System.exit(0);
                    } else {
                        this.snakeBody.add(0,
                                new Node(head.getxCoordinate(),
                                        head.getyCoordinate() + Node.NODE_HEIGHT,
                                        Color.black));
                    }
                }
                break;
        }
    }
}
