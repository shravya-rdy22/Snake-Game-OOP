import java.awt.*;
import java.util.Random;

public class Food {

    Point position;

    Random rand = new Random();

    int width;
    int height;

    public Food(int width, int height) {

        this.width = width;
        this.height = height;

        position = new Point(
                rand.nextInt(width),
                rand.nextInt(height)
        );
    }

    public void generate(Snake snake) {

        boolean valid = false;

        while (!valid) {

            position = new Point(
                    rand.nextInt(width),
                    rand.nextInt(height)
            );

            valid = true;

            for (Point p : snake.body) {

                if (p.equals(position)) {

                    valid = false;
                    break;
                }
            }
        }
    }

    public void draw(Graphics g) {

        g.setColor(Color.RED);

        g.fillOval(
                position.x * 20,
                position.y * 20,
                20,
                20
        );
    }
}