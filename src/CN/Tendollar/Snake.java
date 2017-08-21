package CN.Tendollar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Snake extends JFrame {
    public static final int WINDOW_WIDTH = 811, WINDOW_HEIGHT = 638;
    BufferedImage bufferedImage = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    SnakeCore snake = null;
    Node food = null;
    //boolean stop = false;

    public Snake() {
        //NOTHING
    }

    public void launchSnake() {

        snake = new SnakeCore();
        food = snake.initFood();
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

    //创建图形化界面
    private void initGUI() {
        //设置标题
        super.setTitle("Greedy Snake Game Programed by Porcelain");
        super.setBounds(new Rectangle(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT));
        //增添键盘侦听器
        super.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        snake.tmpDirection = Direction.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.tmpDirection = Direction.RIGHT;
                        break;
                    case KeyEvent.VK_UP:
                        snake.tmpDirection = Direction.UP;
                        break;
                    default:
                        snake.tmpDirection = Direction.DOWN;
                }
            }
        });

        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    //这是最重要的一个方法了，因为要不停的刷新界面
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        //两者顺序不可颠倒
        //初始化窗口大小和颜色
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        snake.drawBody(graphics2D);
        food.drawNode(graphics2D);
        snake.move();

        g.drawImage(bufferedImage, 0, 0, null);

        if (snake.isTouchingFood(food)) {
            snake.addHead();
            food = snake.initFood();
        }

        if (snake.isTouchingItself()) {
            System.exit(0);
        }

    }
}
