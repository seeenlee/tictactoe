import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Board {
    final int LENGTH = 3;
    private int[][] boardState;
    private boolean isGameOver = false;
    private boolean player1 = true;
    Scanner s = new Scanner(System.in);
    private int third = 200;
    private boolean starting = true;
    private static Image letsplay;
    private static Image x;
    private static Image o;
    private static Image ggez;


    public Board(){
        boardState = new int[LENGTH][LENGTH];
    }

    public void start(){
        printGameState();
        while(!isGameOver){
            isGameOver = checkWinCon();
        }
        System.out.println("Game is over!");
    }

    private boolean checkWinCon() {
        // Horizontal Check Win Con
        for (int row = 0; row < 3; row++){
            int rowTotal = 0;
            for (int col = 0; col < 3; col++){
                rowTotal += boardState[row][col];
                if (3 == Math.abs(rowTotal) ){
                    return true;
                }
            }
        }

        // Vertical Check Win Con
        for (int col = 0; col <3; col++){
            int colTotal = 0;
            for (int row = 0; row < 3; row++){
                colTotal += boardState[row][col];
                if (Math.abs(colTotal) == 3){
                    return true;
                }
            }
        }

        // Left Diagonal Check Win Con
        int ldtotal = 0;
        for (int num = 0; num < 3; num++){
            ldtotal += boardState[num][num];
            if(Math.abs(ldtotal) == 3){
                return true;
            }
        }

        // Right Diagonal Check Win Con
        int rdtotal = 0;
        for (int num = 0; num <3; num++){
            rdtotal += boardState[num][2 - num];
            if(Math.abs(rdtotal) == 3){
                return true;
            }
        }
        return false;
    }

    public void printGameState(){
        for (int row = 0; row < 3; row++){
            for (int col =0; col < 3; col++){
                switch (boardState[row][col]) {
                    case 1:
                        System.out.print("X");
                        break;
                    case -1:
                        System.out.print("O");
                        break;
                    default:
                        System.out.print(" ");
                        break;
                }
                if (col < 2){
                    System.out.print("|");
                }
            }
            if (row < 2) {
                System.out.print("\n");
                System.out.println("------");
            }
        }
        System.out.print("\n");
    }

    public void handleClick(MouseEvent me) {
        System.out.println("You just clicked: "+me);
        if (starting) {
            starting = false;
        } else {
            if(!isGameOver) {
                if(player1){
                    if (boardState[me.getY() / third][me.getX() / third] == 0){
                        boardState[me.getY() / third][me.getX() / third] = 1;
                        player1 = !player1;
                    }
                }
                else{
                    if (boardState[me.getY() / third][me.getX() / third] == 0){
                        if (boardState[me.getY() / third][me.getX() / third] == 0){
                            boardState[me.getY() / third][me.getX() / third] = -1;
                            player1 = !player1;
                        }
                    }
                }
                printGameState();
                isGameOver = checkWinCon();
                if (isGameOver){
                    System.out.println("Game is over!");
                }
            }
        }
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        setUpImages();
        if (starting) {
            g.drawImage(letsplay.getScaledInstance(600, 600, Image.SCALE_SMOOTH), 0, 0, null);
        } else {
            //if (!isGameOver) {
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        switch (boardState[row][col]) {
                            case 0:
                                break;
                            case 1:
//                                g.setColor(Color.GREEN);
//                                g.fillRect(col * third, row * third, third, third);
                                g.drawImage(x.getScaledInstance(200, 200, Image.SCALE_SMOOTH), col * third, row * third, null);
                                break;
                            case -1:
//                                g.setColor(Color.RED);
//                                g.fillRect(col * third, row * third, third, third);
                                g.drawImage(o.getScaledInstance(200, 200, Image.SCALE_SMOOTH), col * third, row * third, null);
                                break;
                            default:
                                System.out.println("Shouldn't be here!");
                        }
                    }
                }
            //}
            if (isGameOver) {
                g.drawImage(ggez.getScaledInstance(400, 200, Image.SCALE_SMOOTH), 100, 200, null);
            }
        }
    }

    private void setUpImages() {
        if(letsplay == null) {
            try {
                letsplay = ImageIO.read(new File("letsplay.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(x == null) {
            try {
                x = ImageIO.read(new File("x.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(o == null) {
            try {
                o = ImageIO.read(new File("o.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(ggez == null) {
            try {
                ggez = ImageIO.read(new File("ggez.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}