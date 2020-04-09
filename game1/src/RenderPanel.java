import java.awt.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 700);

        if (Game.over) {
            String string = "Game Over";
            g.setColor(Color.white);
            g.setFont(new Font("Impact", Font.PLAIN, 24));
            g.drawString(string, getWidth() / 2 + string.length() / 2 - 50, getHeight() / 2 - 50);
            g.setFont(new Font("Helvetica", Font.PLAIN, 12));
            String string2 = "Score: " + Game.score;
            g.drawString(string2, getWidth() / 2 + string2.length() / 2 - 20, getHeight() / 2 - 25);
            String string3 = "(Press Space to play again)";
            g.drawString(string3, getWidth() / 2 + string3.length() / 2 - 80, getHeight() / 2);
        }
        else {
            g.setColor(Color.red);
            g.fillRect(0, Game.redBarY, Game.redBarWidth, 1);

            g.setColor(Color.white);
            g.fillRect(Game.x1, Game.y1, 1 * Game.SCALE, 1 * Game.SCALE);
            g.fillRect(0, 50, 5 * Game.SCALE, 1 * Game.SCALE);
            g.fillRect(Game.platformX, Game.platformY, Game.platformWidth, 1 * Game.SCALE);

            String string = "Score: " + Game.score;
            g.setColor(Color.white);
            g.setFont(new Font("Helvetica", Font.PLAIN, 12));
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 15);
        }
    }
}