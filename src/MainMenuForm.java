import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm {
    private JPanel panelMain;
    private JButton btnPlay;
    private JButton btnExit;
    private JLabel titleLabel;

    public MainMenuForm() {
        // Buat panel utama
        panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        panelMain.setBackground(new Color(135, 206, 250));

        // Buat komponen
        titleLabel = new JLabel("Flappy Bird");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnPlay = new JButton("Mainkan Game");
        btnExit = new JButton("Keluar");
        btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tambahkan event listener di sini (sekarang tombolnya sudah ada!)
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menuFrame = (JFrame) SwingUtilities.getWindowAncestor(panelMain);
                menuFrame.dispose();

                // jalankan game
                new Thread(() -> App.main(null)).start();
            }
        });

        btnExit.addActionListener(e -> System.exit(0));

        // Masukkan ke panel
        panelMain.add(Box.createVerticalStrut(40));
        panelMain.add(titleLabel);
        panelMain.add(Box.createVerticalStrut(30));
        panelMain.add(btnPlay);
        panelMain.add(Box.createVerticalStrut(10));
        panelMain.add(btnExit);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // Buat menu & tambahkan panel-nya
        MainMenuForm menu = new MainMenuForm();
        frame.setContentPane(menu.panelMain);
        frame.setVisible(true);
    }
}
