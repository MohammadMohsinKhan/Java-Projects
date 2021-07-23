import java.util.*;

public class tictactoe {
    static void easyMove (String matrix[][], String piece) {
        while (true) {
            Random random = new Random();
            int row = random.nextInt(3) + 1;
            int col = random.nextInt(3) + 1;
            if (matrix[row][col] == " ") {
                matrix[row][col] = piece;
                break;
            }
        }
        displayBoard(matrix);
    }

    static void playerMove (String matrix[][], String piece) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            Scanner user_coord = new Scanner(System.in);
            try {
                int r = user_coord.nextInt();
                int c = user_coord.nextInt();
                
                if ((r > 3 || r < 1) || (c > 3 || c < 1)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (matrix[r][c].equalsIgnoreCase("X") ||  matrix[r][c].equalsIgnoreCase("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    matrix[r][c] = piece;
                    displayBoard(matrix);
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
        
    }

    static boolean fillCheck(String matrix[][]) {
        int emptyCount = 0;
        for (int i = 1; i < matrix.length - 1;i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] == " ") {
                    ++emptyCount;
                }
            }
        }
        return emptyCount == 0;
    }

    static boolean winCheck(String matrix[][]) {
        boolean X = false;
        Boolean O = false;

        for (int i = 1; i < matrix.length - 1; i++) {
            if (matrix[i][1].equalsIgnoreCase("X") && matrix[i][2].equalsIgnoreCase("X") && matrix[i][3].equalsIgnoreCase("X")) {
                System.out.println("X wins");
                X = true;
                return true;
                
            } else if (matrix[i][1].equalsIgnoreCase("O") && matrix[i][2].equalsIgnoreCase("O") && matrix[i][3].equalsIgnoreCase("O")) {
                System.out.println("O wins");
                O = true;
                return true;
                
            }

            if (!X && !O) {
                if (matrix[1][i].equalsIgnoreCase("X") && matrix[2][i].equalsIgnoreCase("X") && matrix[3][i].equalsIgnoreCase("X")) {
                    System.out.println("X wins");
                    X = true;
                    return true;
                    
                } else if (matrix[1][i].equalsIgnoreCase("O") && matrix[2][i].equalsIgnoreCase("O") && matrix[3][i].equalsIgnoreCase("O")) {
                    System.out.println("O wins");
                    O = true;
                    return true;
                    
                }
            }
        }

        if (!X && !O) {
                if (matrix[1][1].equalsIgnoreCase("X") && matrix[2][2].equalsIgnoreCase("X") && matrix[3][3].equalsIgnoreCase("X")) {
                    System.out.println("X wins");
                    X = true;
                    return true;
                } else if (matrix[1][1].equalsIgnoreCase("O") && matrix[2][2].equalsIgnoreCase("O") && matrix[3][3].equalsIgnoreCase("O")) {
                    System.out.println("O wins");
                    O = true;
                    return true;
                }
        }

        if (!X && !O) {
            if (matrix[1][3].equalsIgnoreCase("X") && matrix[2][2].equalsIgnoreCase("X") && matrix[3][1].equalsIgnoreCase("X")) {
                System.out.println("X wins");
                X = true;
                return true;
            } else if (matrix[1][3].equalsIgnoreCase("O") && matrix[2][2].equalsIgnoreCase("O") && matrix[3][1].equalsIgnoreCase("O")) {
                System.out.println("O wins");
                O = true;
                return true;
            }
        }   
    
        return false;
        
    }

    static void displayBoard(String matrix[][]) {
        for (int i = 0; i < matrix.length;i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i > 0 && i <= matrix.length - 2) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner coord = new Scanner(System.in);
        String[][] board = {
            {"-","-","-","-","-","-","-","-","-"},
            {"|"," "," "," ","|"},
            {"|"," "," "," ","|"},
            {"|"," "," "," ","|"},
            {"-","-","-","-","-","-","-","-","-"}
        };
        String botMode = "easy";

        while (true) {
            System.out.print("Input command: ");
            Scanner userInput = new Scanner(System.in);
            String[] parameter = userInput.nextLine().split(" ");

            if (parameter[0].equalsIgnoreCase("exit")) {
                break;
            }

            if (parameter.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (parameter[0].equalsIgnoreCase("start") && (parameter[1].equalsIgnoreCase("user") || parameter[1].equalsIgnoreCase("easy") && (parameter[2].equalsIgnoreCase("user") || parameter[2].equalsIgnoreCase("easy")))) {
                displayBoard(board);
                while (true) {
                    int num = 1;
                    if (parameter[1].equalsIgnoreCase("user")) {
                        playerMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board)) {
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else {
                        System.out.println("Making move level \""+botMode+"\"");
                        easyMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board)) {
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                    if (parameter[2].equalsIgnoreCase("user")) {
                        playerMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board)) {
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else {
                        System.out.println("Making move level \""+botMode+"\"");
                        easyMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board)) {
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
        

    }
}
