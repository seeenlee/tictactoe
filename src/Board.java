import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Board {
    final int LENGTH = 3;
    private int[][] state;
    private boolean over = false;
    private boolean player1 = true;
    Scanner s = new Scanner(System.in);
    private int third = 200;

    public Board(){
        state = new int[LENGTH][LENGTH];
    }

    public void start(){
        printGameState();
        while(!over){
//            place(takeInput());
//            printGameState();
            over = checkWinCon();
        }
        System.out.println("Game is over!");
    }

    private boolean checkWinCon() {
        // Horizontal Check Win Con
        for (int row = 0; row < 3; row++){
            int rowTotal = 0;
            for (int col = 0; col < 3; col++){
                rowTotal += state[row][col];
                if (3 == Math.abs(rowTotal) ){
                    return true;
                }
            }
        }

        // Vertical Check Win Con
        for (int col = 0; col <3; col++){
            int colTotal = 0;
            for (int row = 0; row < 3; row++){
                colTotal += state[row][col];
                if (Math.abs(colTotal) == 3){
                    return true;
                }
            }
        }

        // Left Diagonal Check Win Con
        int ldtotal = 0;
        for (int num = 0; num < 3; num++){
            ldtotal += state[num][num];
            if(Math.abs(ldtotal) == 3){
                return true;
            }
        }

        // Right Diagonal Check Win Con
        int rdtotal = 0;
        for (int num = 0; num <3; num++){
            rdtotal += state[num][2 - num];
            if(Math.abs(rdtotal) == 3){
                return true;
            }
        }
        return false;
    }


    private void place(int input){
        input--;
        int row = input/3;
        int column = input % 3;
        if(player1) {
            state[row][column] = 1;
        }
        else{
            state[row][column] = -1;
        }
        player1 = !player1;
    }

    private int takeInput() {
        System.out.println("Please enter a number to place your marker");
        int userInput = s.nextInt();
        s.nextLine();
        return userInput;
    }

    public void printGameState(){
        for (int row = 0; row < 3; row++){
            for (int col =0; col < 3; col++){
                switch (state[row][col]) {
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
        //while(!over){
            if(player1){
                state[me.getY() / third][me.getX() / third] = 1;
                player1 = !player1;
            }
            else{
                state[me.getY() / third][me.getX() / third] = -1;
                player1 = !player1;
            }
            printGameState();
            over = checkWinCon();
            if (over){
                System.out.println("Game is over!");
                System.exit(0);
            }
        //}
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        for (int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                switch(state[row][col]){
                    case 0:
                        break;
                    case 1:
                        g.setColor(Color.GREEN);
                        g.fillRect(col * third, row * third, third, third);
                        break;
                    case -1:
                        g.setColor(Color.RED);
                        g.fillRect(col*third, row*third, third, third);
                }
            }
        }
    }
}