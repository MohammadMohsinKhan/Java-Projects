import java.util.*;
//up till line 52 in github done.
public class tictactoe {
   
    static void hardMove(String matrix[][], String piece) {
        int[][] moves = availableMoves(matrix);
        int[] bestMove = new int[2];
        int score;
        int bestScore = Integer.MIN_VALUE;

        for (int[] move: moves) {
            matrix[move[0]][move[1]] = piece;
            score = minimax(matrix, piece, 0, false);
            matrix[move[0]][move[1]] = " "; //resets the gameboard after the available move is tested
            
            if (score > bestScore) {
                bestScore = score;
                bestMove[0] = move[0];
                bestMove[1]= move[1];
            }
        }
        matrix[bestMove[0]][bestMove[1]] = piece;
        displayBoard(matrix);
    }

    static int minimax(String matrix[][], String piece, int depth, boolean isMaximizing) {
        int[][] moves = availableMoves(matrix);
        String result = winCheck(matrix);
        int bestScore = Integer.MIN_VALUE;
        int score;

        String enemyPiece = piece.equalsIgnoreCase("X") ? "O" : "X";
        
        if (!result.equalsIgnoreCase("")) { //checks if the board has reached a terminal state ie the game has ended and then returns a score
            if (result.equalsIgnoreCase(piece)) {
                return 10;
            } else if (result.equalsIgnoreCase(enemyPiece)) {
                return -10;
            } else {
                return 0;
            }
        }

        if(isMaximizing) { // it is the AI's turn and it wants to maximize its score by picking the optimal move
            bestScore = Integer.MIN_VALUE;
            for (int[] move: moves) {
                matrix[move[0]][move[1]] = piece;
                score = minimax(matrix, piece, depth + 1, false);
                matrix[move[0]][move[1]] = " ";
                bestScore = Math.max(score, bestScore);
            }
            return bestScore;
        } else { // it is the human/enemy bot's tuen and they want to pick a move optimal for themselves so they will minimize the score.
            bestScore = Integer.MAX_VALUE;
            for (int[] move: moves) {
                matrix[move[0]][move[1]] = enemyPiece;
                score = minimax(matrix, piece, depth + 1, true);
                matrix[move[0]][move[1]] = " ";
                bestScore = Math.min(score, bestScore);
            }
            return bestScore;
        }

    }

    static int emptyCount(String matrix[][]) { //returns a count of how many empty cells remain on the game board
        int emptyCount = 0;
        
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix.length - 1; j++) {
                if (matrix[i][j].equalsIgnoreCase(" ")) {
                    ++emptyCount;
                }
            }
        }
        return emptyCount;
    }

    static int[][] availableMoves(String matrix[][]) { // returns a list of available moves left on the board 
        
        int[][] moves = new int[emptyCount(matrix)][2];
        int ind = 0;

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix.length - 1; j++) {
                if (matrix[i][j].equalsIgnoreCase(" ")) {
                    moves[ind][0] = i;
                    moves[ind][1] = j;
                    ++ind;
                }
            }
        }
        return moves;
    }

    static void mediumMove(String matrix[][], String piece) {
        int counter = 0;
        int mark = 0;
        boolean empty = false;
        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the player is going to win horizontally and stops it
            for (int j = 1; j < matrix.length - 1; j++) {
                if (!matrix[i][j].equalsIgnoreCase(piece) && !matrix[i][j].equalsIgnoreCase(" ")) {
                    counter++;
                } else if (matrix[i][j].equalsIgnoreCase(" ")){
                    mark = j;
                    empty = true;
                }
            }
            if (counter >= 2 && empty == true) {
                matrix[i][mark] = piece;
                displayBoard(matrix);
                return;
            }
            counter = 0;
            empty = false;
        }

        counter = 0;
        mark = 0;
        int rowLoop = 0;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the player is going to win vertically and stops it
            for (int j = 1; j < matrix.length - 1; j++) {
                if (!matrix[j][i].equalsIgnoreCase(piece) && !matrix[j][i].equalsIgnoreCase(" ")) {
                    counter++;
                } else if (matrix[j][i].equalsIgnoreCase(" ")){
                    rowLoop = j;
                    mark = i;
                    empty = true;
                }
            }
            if (counter >= 2 && empty == true) {
                matrix[rowLoop][mark] = piece;
                displayBoard(matrix);
                return;
            }
            counter = 0;
            empty = false;
        }

        counter = 0;
        empty = false;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the player is going to win diagonally and stops it
            if (!matrix[i][i].equalsIgnoreCase(piece) && !matrix[i][i].equalsIgnoreCase(" ")) {
                counter++;
            } else if (matrix[i][i].equalsIgnoreCase(" ")) {
                empty = true;
                mark = i;
            }
        }

        if (counter >= 2 && empty == true) {
            matrix[mark][mark] = piece;
            displayBoard(matrix);
            return;
        }

        counter = 0;
        empty = false;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the player is going to win diagonally and stops it
            if (!matrix[i][4 - i].equalsIgnoreCase(piece) && !matrix[i][4 - i].equalsIgnoreCase(" ")) {
                counter++;
            } else if (matrix[i][4 - i].equalsIgnoreCase(" ")) {
                empty = true;
                mark = i;
            }
        }

        if (counter >= 2 && empty == true) {
            matrix[mark][4 - mark] = piece;
            displayBoard(matrix);
            return;
        }

        counter = 0;
        empty = false;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the bot is going to win horizontally and executes it
            for (int j = 1; j < matrix.length - 1; j++) {
                if (matrix[i][j].equalsIgnoreCase(piece) && !matrix[i][j].equalsIgnoreCase(" ")) {
                    counter++;
                } else if (matrix[i][j].equalsIgnoreCase(" ")){
                    mark = j;
                    empty = true;
                }
            }
            if (counter >= 2 && empty == true) {
                matrix[i][mark] = piece;
                displayBoard(matrix);
                return;
            }
            counter = 0;
            empty = false;
        }

        counter = 0;
        mark = 0;
        rowLoop = 0;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the bot is going to win vertically and executes it
            for (int j = 1; j < matrix.length - 1; j++) {
                if (matrix[j][i].equalsIgnoreCase(piece) && !matrix[j][i].equalsIgnoreCase(" ")) {
                    counter++;
                } else if (matrix[j][i].equalsIgnoreCase(" ")){
                    rowLoop = j;
                    mark = i;
                    empty = true;
                }
            }
            if (counter >= 2 && empty == true) {
                matrix[rowLoop][mark] = piece;
                displayBoard(matrix);
                return;
            }
            counter = 0;
            empty = false;
        }

        counter = 0;
        empty = false;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the bot is going to win diagonally and executes  it
            if (matrix[i][i].equalsIgnoreCase(piece) && !matrix[i][i].equalsIgnoreCase(" ")) {
                counter++;
            } else if (matrix[i][i].equalsIgnoreCase(" ")) {
                empty = true;
                mark = i;
            }
        }

        if (counter >= 2 && empty == true) {
            matrix[mark][mark] = piece;
            displayBoard(matrix);
            return;
        }

        counter = 0;
        empty = false;

        for (int i = 1; i < matrix.length - 1; i++) { // loop for checking if the bot is going to win diagonally and executes  it
            if (matrix[i][4 - i].equalsIgnoreCase(piece) && !matrix[i][4 - i].equalsIgnoreCase(" ")) {
                counter++;
            } else if (matrix[i][4 - i].equalsIgnoreCase(" ")) {
                empty = true;
                mark = i;
            }
        }

        if (counter >= 2 && empty == true) {
            matrix[mark][4 - mark] = piece;
            displayBoard(matrix);
            return;
        }

        while (true) {
            Random random = new Random();
            int row = random.nextInt(3) + 1;
            int col = random.nextInt(3) + 1;
            if (matrix[row][col] == " ") {
                matrix[row][col] = piece;
                displayBoard(matrix);
                break;
            }
        }
        

    }

    static void easyMove (String matrix[][], String piece) {
        // generates a random move
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
            // checks to see whether the coordinates entered are in the correct format and within the range
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

    static boolean fillCheck(String matrix[][]) { // returns a boolean of whether the board is filled
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

    static String winCheck(String matrix[][]) { // returns the winning piece
        boolean X = false;
        Boolean O = false;

        for (int i = 1; i < matrix.length - 1; i++) {
            if (matrix[i][1].equalsIgnoreCase("X") && matrix[i][2].equalsIgnoreCase("X") && matrix[i][3].equalsIgnoreCase("X")) {
                X = true;
                return "X";
                
            } else if (matrix[i][1].equalsIgnoreCase("O") && matrix[i][2].equalsIgnoreCase("O") && matrix[i][3].equalsIgnoreCase("O")) {
                O = true;
                return "O";
                
            }

            if (!X && !O) {
                if (matrix[1][i].equalsIgnoreCase("X") && matrix[2][i].equalsIgnoreCase("X") && matrix[3][i].equalsIgnoreCase("X")) {
                    X = true;
                    return "X";
                    
                } else if (matrix[1][i].equalsIgnoreCase("O") && matrix[2][i].equalsIgnoreCase("O") && matrix[3][i].equalsIgnoreCase("O")) {
                    O = true;
                    return "O";
                    
                }
            }
        }

        if (!X && !O) {
                if (matrix[1][1].equalsIgnoreCase("X") && matrix[2][2].equalsIgnoreCase("X") && matrix[3][3].equalsIgnoreCase("X")) {
                    X = true;
                    return "X";
                } else if (matrix[1][1].equalsIgnoreCase("O") && matrix[2][2].equalsIgnoreCase("O") && matrix[3][3].equalsIgnoreCase("O")) {
                    O = true;
                    return "O";
                }
        }

        if (!X && !O) {
            if (matrix[1][3].equalsIgnoreCase("X") && matrix[2][2].equalsIgnoreCase("X") && matrix[3][1].equalsIgnoreCase("X")) {
                X = true;
                return "X";
            } else if (matrix[1][3].equalsIgnoreCase("O") && matrix[2][2].equalsIgnoreCase("O") && matrix[3][1].equalsIgnoreCase("O")) {
                O = true;
                return "O";
            }
        }   
        
        if (fillCheck(matrix) == true) {
            return "tie";
        }

        return "";
    }

    static void displayBoard(String matrix[][]) { // function for rendering the gameboard on the terminal
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
        String[] goodParameter = {"easy", "user", "medium", "hard"};

        while (true) {
            String[][] board = {
                {"-","-","-","-","-","-","-","-","-"},
                {"|"," "," "," ","|"},
                {"|"," "," "," ","|"},
                {"|"," "," "," ","|"},
                {"-","-","-","-","-","-","-","-","-"}
            };
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

            if (parameter[0].equalsIgnoreCase("start") && (Arrays.asList(goodParameter).contains(parameter[1])) && (Arrays.asList(goodParameter).contains(parameter[2]))) {
                displayBoard(board);
                while (true) {
                    int num = 1;
                    if (parameter[1].equalsIgnoreCase("user")) {
                        playerMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("X")) {
                            System.out.println("X wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else if (parameter[1].equalsIgnoreCase("easy")){
                        System.out.println("Making move level \"easy\"");
                        easyMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("X")) {
                            System.out.println("X wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else if (parameter[1].equalsIgnoreCase("medium")){
                        System.out.println("Making move level \"medium\"");
                        mediumMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("X")) {
                            System.out.println("X wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else {
                        System.out.println("Making move level \"hard\"");
                        hardMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("X")) {
                            System.out.println("X wins");
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
                        if (winCheck(board).equalsIgnoreCase("O")) {
                            System.out.println("O wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else if (parameter[2].equalsIgnoreCase("easy")){
                        System.out.println("Making move level \"easy\"");
                        easyMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("O")) {
                            System.out.println("O wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else if (parameter[2].equalsIgnoreCase("medium")){
                        System.out.println("Making move level \"medium\"");
                        mediumMove(board, num % 2 == 0 ? "O" : "X");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("O")) {
                            System.out.println("O wins");
                            break;
                        }
                        if (fillCheck(board)) {
                            System.out.println("Draw");
                            break;
                        }
                    } else {
                        System.out.println("Making move level \"hard\"");
                        hardMove(board, "O");
                        ++num;
                        if (winCheck(board).equalsIgnoreCase("O")) {
                            System.out.println("O wins");
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
