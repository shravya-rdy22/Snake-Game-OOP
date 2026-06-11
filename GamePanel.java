import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    static final int WIDTH = 600;
    static final int HEIGHT = 400;
    static final int TILE_SIZE = 20;

    Snake snake;
    Food food;

    Timer timer;

    boolean running = false;
    int score = 0;

    public GamePanel() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame() {

        snake = new Snake();

        food = new Food(
                WIDTH / TILE_SIZE,
                HEIGHT / TILE_SIZE
        );

        running = true;

        timer = new Timer(120, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (running) {

            food.draw(g);
            snake.draw(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Score: " + score, 10, 25);

        } else {

            gameOver(g);
        }

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {

            snake.move();

            if (snake.eat(food)) {

                score++;
                snake.grow();
                food.generate(snake);
            }

            if (snake.checkCollision(
                    WIDTH / TILE_SIZE,
                    HEIGHT / TILE_SIZE)) {

                running = false;
            }
        }

        repaint();
    }

    public void gameOver(Graphics g) {

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        g.drawString(
                "GAME OVER",
                WIDTH / 2 - 80,
                HEIGHT / 2
        );
    }

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            snake.changeDirection(e.getKeyCode());
        }
    }
}