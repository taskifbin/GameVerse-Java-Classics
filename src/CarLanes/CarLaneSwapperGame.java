import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class CarLaneSwapperGame extends JPanel implements ActionListener {
    private Timer timer;
    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 600;
    private final int[] LANES = { 100, 200, 300 };
    private int carLane = 1;
    private int score = 0;
    private int highScore = 0;

    private Image carImage;
    private Image rockImage;
    private int carWidth;
    private int carHeight;
    private int rockSize;

    private ArrayList<Rectangle> barricades;
    private Random random = new Random();

    private JButton leftButton;
    private JButton rightButton;
    private static String selectedCar = "img/car-1.png";

    public CarLaneSwapperGame() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);

        leftButton = new JButton("🡰");
        leftButton.setBounds(120, 530, 80, 40);
        styleButton(leftButton);
        add(leftButton);

        rightButton = new JButton("🡲");
        rightButton.setBounds(200, 530, 80, 40);
        styleButton(rightButton);
        add(rightButton);

        leftButton.addActionListener(e -> moveLeft());
        rightButton.addActionListener(e -> moveRight());

        ImageIcon carIcon = new ImageIcon(selectedCar);
        carImage = carIcon.getImage();
        carHeight = 100;
        carWidth = carImage.getWidth(null) * carHeight / carImage.getHeight(null);

        ImageIcon rockIcon = new ImageIcon("img/rock.png");
        rockImage = rockIcon.getImage();
        rockSize = 60;

        barricades = new ArrayList<>();

        timer = new Timer(15, this);
        timer.start();

        new Timer(1200, e -> addBarricade()).start();
    }

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void moveLeft() {
        if (carLane > 0)
            carLane--;
    }

    private void moveRight() {
        if (carLane < 2)
            carLane++;
    }

    private void addBarricade() {
        int lane = random.nextInt(3);
        barricades.add(new Rectangle(LANES[lane] - rockSize / 2, 0, rockSize, rockSize));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Rectangle> it = barricades.iterator();
        while (it.hasNext()) {
            Rectangle b = it.next();
            b.y += 4;
            if (b.y > PANEL_HEIGHT) {
                it.remove();
                score++;
                if (score > highScore)
                    highScore = score;
            } else if (b.intersects(getCarBounds())) {
                timer.stop();
                int option = showCustomDialog();
                if (option == JOptionPane.YES_OPTION) {
                    score = 0;
                    barricades.clear();
                    carLane = 1;
                    timer.start();
                } else {
                    System.exit(0);
                }
                break;
            }
        }
        repaint();
    }

    private int showCustomDialog() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BorderLayout());

        JLabel message = new JLabel("<html><div style='text-align: center;'>Game Over!<br/>Score: " + score
                + "<br/>Continue playing?</div></html>", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 16));
        message.setForeground(Color.WHITE);
        panel.add(message, BorderLayout.CENTER);

        UIManager.put("OptionPane.background", Color.DARK_GRAY);
        UIManager.put("Panel.background", Color.DARK_GRAY);
        UIManager.put("Button.background", new Color(30, 144, 255));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("Button.border", BorderFactory.createLineBorder(Color.WHITE, 2, true));

        return JOptionPane.showConfirmDialog(this, panel, "Game Over", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    private Rectangle getCarBounds() {
        return new Rectangle(LANES[carLane] - carWidth / 2, PANEL_HEIGHT - 220, carWidth, carHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        for (int y = 0; y < PANEL_HEIGHT; y += 40) {
            for (int x = 75; x <= 325; x += 100) {
                g.fillRect(x, y, 4, 20);
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + score, 11, 21);
        g2d.drawString("High Score: " + highScore, 251, 21);
        g2d.setColor(Color.YELLOW);
        g2d.drawString("Score: " + score, 10, 20);
        g2d.drawString("High Score: " + highScore, 250, 20);

        g.drawImage(carImage, LANES[carLane] - carWidth / 2, PANEL_HEIGHT - 220, carWidth, carHeight, this);

        for (Rectangle b : barricades) {
            g.drawImage(rockImage, b.x, b.y, b.width, b.height, this);
        }
    }

    public static void main(String[] args) {
        String[] carOptions = { "img/car-1.png", "img/car-2.png", "img/car-3.png", "img/car-4.png" };

        JPanel chooser = new JPanel();
        chooser.setLayout(new GridLayout(2, 2, 10, 10));
        chooser.setBackground(Color.GRAY);

        ButtonGroup group = new ButtonGroup();

        for (String carOption : carOptions) {
            ImageIcon icon = new ImageIcon(carOption);
            Image img = icon.getImage();
            ImageIcon scaledIcon = new ImageIcon(img.getScaledInstance(-1, 100, Image.SCALE_SMOOTH));
            JToggleButton button = new JToggleButton(scaledIcon);
            button.setActionCommand(carOption);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            button.setBackground(Color.BLACK);
            group.add(button);
            chooser.add(button);
        }

        ((JToggleButton) chooser.getComponent(0)).setSelected(true);

        int result = JOptionPane.showConfirmDialog(null, chooser, "Choose Your Car", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectedCar = group.getSelection().getActionCommand();
        } else {
            System.exit(0);
        }

        JFrame frame = new JFrame("Car Lane Swapper");
        CarLaneSwapperGame game = new CarLaneSwapperGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
