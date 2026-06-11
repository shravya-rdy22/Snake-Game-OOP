import java.awt.*;
import java.util.LinkedList;

public class Snake {

    LinkedList<Point> body;

    char direction = 'R';

    public Snake() {

        body = new LinkedList<>();

        body.add(new Point(5, 5));
        body.add(new Point(4, 5));
        body.add(new Point(3, 5));
    }

    public void move() {

        Point head = body.getFirst();

        Point newHead = new Point(head);

        switch (direction) {

            case 'U':
                newHead.y--;
                break;

            case 'D':
                newHead.y++;
                break;

            case 'L':
                newHead.x--;
                break;

            case 'R':
                newHead.x++;
                break;
        }

        body.addFirst(newHead);

        body.removeLast();
    }

    public void grow() {

        body.addLast(
                new Point(body.getLast())
        );
    }

    public boolean eat(Food food) {

        return body.getFirst().equals(food.position);
    }

    public boolean checkCollision(
            int width,
            int height) {

        Point head = body.getFirst();

        if (head.x < 0 ||
            head.y < 0 ||
            head.x >= width ||
            head.y >= height) {

            return true;
        }

        for (int i = 1; i < body.size(); i++) {

            if (head.equals(body.get(i))) {

                return true;
            }
        }

        return false;
    }

    public void changeDirection(int key) {

        if (key == 38 && direction != 'D')
            direction = 'U';

        if (key == 40 && direction != 'U')
            direction = 'D';

        if (key == 37 && direction != 'R')
            direction = 'L';

        if (key == 39 && direction != 'L')
            direction = 'R';
    }

    public void draw(Graphics g) {

        for (int i = 0; i < body.size(); i++) {

            Point p = body.get(i);

            if (i == 0) {

                g.setColor(Color.YELLOW);

                g.fillRect(
                        p.x * 20,
                        p.y * 20,
                        20,
                        20);

            } else {

                g.setColor(Color.GREEN);

                g.fillRect(
                        p.x * 20,
                        p.y * 20,
                        20,
                        20);
            }
        }
    }
}