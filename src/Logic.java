import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;

    private boolean gameOver = false;
    private boolean gameStarted = false; // 👈 baru
    private int score = 0;
    private JLabel scoreLabel;

    public Logic() {
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<>();

        // Label skor
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        scoreLabel.setForeground(Color.BLACK);

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes();
            }
        });

        gameLoop = new Timer(1000 / 60, this);

        // 🚫 Belum start di sini — tunggu pencet spasi pertama
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        if (gameOver || !gameStarted) return; // 👈 stop kalau belum mulai

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        // jatuh ke bawah
        if (player.getPosY() + player.getHeight() >= frameHeight - 75) {
            triggerGameOver();
        }

        // tabrakan
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                triggerGameOver();
                break;
            }
        }

        // hitung skor (pipa bagian atas saja)
        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && pipe.getImage() == upperPipeImage) {
                if (pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                    pipe.setPassed(true);
                    score++;
                    scoreLabel.setText("Score: " + score);
                }
            }
        }
    }

    private void triggerGameOver() {
        if (gameOver) return;
        gameOver = true;
        gameLoop.stop();
        pipesCooldown.stop();
        if (view != null) view.repaint();
        System.out.println("GAME OVER");
    }

    private void restartGame() {
        System.out.println("Restart Game");

        gameOver = false;
        gameStarted = false; // 👈 reset ke belum mulai
        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: 0");

        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        if (view != null) view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true; // 👈 mulai game
                gameLoop.start();
                pipesCooldown.start();
            }
            if (!gameOver) {
                player.setVelocityY(-10);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    public void keyReleased(KeyEvent e) {}
}
