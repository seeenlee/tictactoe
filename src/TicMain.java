import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicMain extends JPanel{
    private final int SIZE_PANEL = 600;
    private Board board = new Board();
    private int rows, cols, mines;
    Timer timer = new Timer(1000, null);

    public static void main(String[] args){
        JFrame frame = new JFrame("tictactoe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TicMain());
        frame.pack();
        frame.setVisible(true);
        new TicMain().start();
    }

    public int getSIZE_PANEL(){
        return SIZE_PANEL;
    }

    private void start() {
        board.start();
    }

    public TicMain() {
        board = new Board();
        this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                board.handleClick(me);
                repaint();
            }
        });
        timer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
    }
}
