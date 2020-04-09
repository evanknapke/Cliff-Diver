import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game implements ActionListener, KeyListener {

    public static Game game;
    public static boolean over;
    public JFrame jframe;
    public RenderPanel renderPanel;
    public Timer timer = new Timer(20, this);

    public static final int SPEED = 10, SCALE = 10, GRAVITY = 8;
    public static int x, x1, y1, y2, platformX, platformY = 650, platformWidth, redBarWidth, redBarY, score = 0;
    public int gravity;
    public boolean isNewRound, isAPressed = false, isDPressed = false;
    Random rand = new Random();

    public Game() {
        jframe = new JFrame("Unnamed");
        jframe.setVisible(true);
        jframe.setSize(800, 700); // 800,700
        jframe.setResizable(false); // cant resize
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        over = false;
        redBarWidth = jframe.getWidth();
        redBarY = jframe.getHeight() - 100;
        platformX = 30;
        platformWidth = 10 * SCALE;
        x = jframe.getWidth() / 2;
        y1 = 0;
        x1 = 10;
        y2 = jframe.getHeight() / 2;
        score = 0;
        timer.start();
    }

    public void buttonHandler() {
        if ((isAPressed || isDPressed) && y1 >= redBarY) {
            return;
        } else {
            if (isAPressed) {
                x1 -= SPEED; // move left
            }
            if (isDPressed) {
                x1 += SPEED; // move right
            }
        }
    }

    public void getRedBar() {
        if (isNewRound) {
            int n = rand.nextInt(500) + 100;
            redBarY = n;
        }
    }

    public void getPlatformX() {
        if (isNewRound) {
            int n = rand.nextInt(redBarY);
            if (n < 30) {
                n = 30;
            }
            platformX = n;
        }
    }

    public void getPlatformWidth() {
        if (isNewRound) {
            int n;
            if (score < 5) {
                n = 10 * SCALE;
            } else if (score < 10) {
                n = 8 * SCALE;
            } else if (score < 15) {
                n = 6 * SCALE;
            } else if (score < 20) {
                n = 4 * SCALE;
            } else {
                n = 2 * SCALE;
            }
            platformWidth = n;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!over) {
            isNewRound = false;
            if (y1 >= platformY - 1 * SCALE && y1 <= platformY + 1 * SCALE && x1 >= platformX - 1 * SCALE && x1 <= platformX + 10 * SCALE - 4) { // contact with landing platform
                gravity = 0;
                isNewRound = true;
                score += 1;
                x1 = 10;
                y1 = 0;
            } else if (y1 >= 50 - 1 * SCALE && y1 <= 50 + 1 * SCALE && x1 >= 0 && x1 <= 5 * SCALE) { // contact with starting platform
                gravity = 0;
            } else if (y1 > jframe.getHeight()) { // falls off bottom of map
                over = true;
            } else {
                gravity = GRAVITY;
            }
            y1 += gravity;
            buttonHandler();
            getRedBar();
            getPlatformX();
            getPlatformWidth();
            renderPanel.repaint();
        } else {
            over = true;
        }
    }

    public static void main(String[] args) {
        game = new Game();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) { // moves paddle and pauses
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_D) {
            isDPressed = true;
        }
        if (i == KeyEvent.VK_A) {
            isAPressed = true;
        }
        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { // stops paddle when key is released
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_D) {
            isDPressed = false;
        }
        if (i == KeyEvent.VK_A) {
            isAPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}