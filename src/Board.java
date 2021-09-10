import java.util.Scanner;

public class Board {
    final int LENGTH = 3;
    private int[][] state;
    private boolean over = false;
    private boolean player1 = true;
    Scanner s = new Scanner(System.in);

    public Board(){
        state = new int[LENGTH][LENGTH];
    }

    public void start(){
        printGameState();
        while(!over){
            place(takeInput());
            if (player1){
                player1 = false;
            }
            else{
                player1 = true;
            }
            printGameState();
            over = checkWinCon();
        }
    }

    private boolean checkWinCon() {

        return false;
    }

    private void place(int input){
        //Index from 0-8
        input--;
        int row = input/3;
        int column = input % 3;
        if(player1) {
            state[row][column] = 1;
        }
        else{
            state[row][column] = -1;
        }

        /*
        if (player1){
            switch (input){
                case 1:
                    state[0][0] = 1;
                    break;
                case 2:
                    state[0][1] = 1;
                    break;
                case 3:
                    state[0][2] = 1;
                    break;
                case 4:
                    state[1][0] = 1;
                    break;
                case 5:
                    state[1][1] = 1;
                    break;
                case 6:
                    state[1][2] = 1;
                    break;
                case 7:
                    state[2][0] = 1;
                    break;
                case 8:
                    state[2][1] = 1;
                    break;
                case 9:
                    state[2][2] = 1;
                    System.out.println("Why is this running");
                    break;
            }
        }
        else{
            if (!player1){
                switch (input){
                    case 1:
                        state[0][0] = 0;
                    case 2:
                        state[0][1] = 0;
                    case 3:
                        state[0][2] = 0;
                    case 4:
                        state[1][0] = 0;
                    case 5:
                        state[1][1] = 0;
                    case 6:
                        state[1][2] = 0;
                    case 7:
                        state[2][0] = 0;
                    case 8:
                        state[2][1] = 0;
                    case 9:
                        state[2][2] = 0;
                }
            }
        }

         */
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
}