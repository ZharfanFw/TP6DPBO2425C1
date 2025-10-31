import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private Image background;

    // constructor
    public View(Logic logic) {
        this.logic = logic;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);

        setFocusable(true);
        addKeyListener(logic);


        // Tambahkan label skor ke View
        setLayout(new BorderLayout());
        add(logic.getScoreLabel(), BorderLayout.NORTH);

        background = new ImageIcon("background/background.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (background != null) {
            g.drawImage(background, 0, 0, width, height, null);
        }

        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }


        // Jika game over, tampilkan tulisan GAME OVER
        if (logic.isGameOver()) {
            Graphics2D g2 = (Graphics2D) g;
            String text = "GAME OVER";
            Font font = new Font("SansSerif", Font.BOLD, 36);
            g2.setFont(font);

            // hitung pusat
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int x = (getWidth() - textWidth) / 2;
            int y = getHeight() / 2;

            // background semi-opaque untuk kontras
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(x - 20, y - fm.getAscent(), textWidth + 40, fm.getHeight());

            // teks putih
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        // Jika belum mulai dan belum game over
        if (!logic.isGameStarted() && !logic.isGameOver()) {
            g.setColor(Color.white);
            g.setFont(new Font("SansSerif", Font.BOLD, 24));
            String msg = "Press SPACE to start";
            int textWidth = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, (getWidth() - textWidth) / 2, getHeight() / 2);
        }
    }
}
