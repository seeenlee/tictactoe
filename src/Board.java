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
            player1 = !player1;
            printGameState();
            over = checkWinCon();
        }
        System.out.println("Game is over!");
    }

    private boolean checkWinCon() {
//        if(state[0][0] + state[0][1] + state[0][2] == 3 || state[0][0] + state[0][1] + state[0][2] == -3 ) {
//            return true;
//        }
        // Horizontal Check Win Con
        int row1, row2, row3, col1, col2, col3, diag1, diag2;
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