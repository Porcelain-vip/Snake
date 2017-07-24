package package1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class SnakeViewController extends JFrame {
    public static final int WIDTH = 811, HEIGHT = 638;
    BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    SnakeModel snakeModel = null;
    Node food = null;

    public SnakeViewController() {
        //NOTHING
    }

    public void launchSnake() {

        snakeModel = new SnakeModel();
        food = snakeModel.initFood();
        //初始化图形化界面
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initGUI();
            }
        });
        //添加一个线程，这个线程用于每隔100ms刷新一次界面
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //此方法会间接调用paint()方法
                    repaint();
                }
            }
        }).start();
    }

    private void initGUI() {
        //设置标题
        setTitle("Greedy Snake Game Programed by Porcelain");
        setBounds(new Rectangle(100, 100, WIDTH, HEIGHT));
        //增添键盘侦听器
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        snakeModel.tmpDirection = Direction.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        snakeModel.tmpDirection = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_UP:
                        snakeModel.tmpDirection = Direction.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        snakeModel.tmpDirection = Direction.DOWN;
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    //这是最重要的一个方法了，因为要不停的刷新界面
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        //两者顺序不可颠倒
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillRect(0, 0, WIDTH, HEIGHT);

        snakeModel.drawBody(graphics2D);
        food.drawNode(graphics2D);
        snakeModel.move();


        if (snakeModel.isTouchingFood(food)) {
            snakeModel.addHead();
            food = snakeModel.initFood();
        }

        if (snakeModel.isTouchingItself()) {
            System.exit(0);
        }

        g.drawImage(bufferedImage, 0, 0, null);
    }
}
