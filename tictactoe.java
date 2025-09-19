package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        char[][] grid = new char[3][3];      // Creates empty grid
        initializeGrid(grid);
        
        printGrid(grid);
        
        char currentPlayer = 'X';             // X starts first
        boolean gameEnd = false;        
        
        while (!gameEnd) {                  // Main game loop
            boolean validMove = false;            
            
            while (!validMove) {
                String moveInput = scanner.nextLine();
                
                try {
                    String[] coordinates = moveInput.split(" ");                    
                    
                    if (coordinates.length != 2) {                      // Checks if input contains exactly 2 parts
                        System.out.println("You should enter numbers!");
                        continue;
                    }                 
                    int row = Integer.parseInt(coordinates[0]);        // Checks if 1,2,3
                    int col = Integer.parseInt(coordinates[1]);                    
                    
                    if (row < 1 || row > 3 || col < 1 || col > 3) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }                    
                    
                    int gridRow = row - 1;                         // Checks if cell is empty
                    int gridCol = col - 1;                    
                    
                    if (grid[gridRow][gridCol] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }
                                        
                    grid[gridRow][gridCol] = currentPlayer;       // Updates grid
                    validMove = true;
                    
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                }
            }
            
            printGrid(grid);
            
            String gameState = analyzeGame(grid);
            
            if (gameState.equals("X wins") || gameState.equals("O wins") || gameState.equals("Draw")) {
                System.out.println(gameState);
                gameEnd = true;
            } else {                
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';        // Switch player 
            }
        }
        
        scanner.close();
    }
    
    private static void initializeGrid(char[][] grid) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = ' ';
            }
        }
    }
    
    private static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    
    private static String analyzeGame(char[][] grid) {        
        int xCount = 0;
        int oCount = 0;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == 'X') xCount++;
                else if (grid[row][col] == 'O') oCount++;
            }
        }
        
        boolean xWins = checkWin(grid, 'X');
        boolean oWins = checkWin(grid, 'O');        
        
        if (xWins && oWins) {                // Checks impossible states & wins
            return "Impossible";
        } else if (Math.abs(xCount - oCount) > 1) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (xCount + oCount == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }
    
    private static boolean checkWin(char[][] grid, char player) {
        for (int row = 0; row < 3; row++) {                                        
            if (grid[row][0] == player && grid[row][1] == player && grid[row][2] == player) {
                return true;
            }
        }        
        
        for (int col = 0; col < 3; col++) {                                          
            if (grid[0][col] == player && grid[1][col] == player && grid[2][col] == player) {
                return true;
            }
        }        
        
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {     
            return true;
        }
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }
        
        return false;
    }
}
