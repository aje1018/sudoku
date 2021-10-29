import java.util.Scanner;

public class Board {
    public int[][] board;
    public Board(Scanner fileReader) {
        board = readBoard(fileReader);
    }
    public static int[][] readBoard(Scanner fileReader) {
        int[][] newBoard = new int[9][9];
        int r = 0;
        int c = 0;
        for (r = 0; r < 9; r++) {
            for (c = 0; c < 9; c++) {
                newBoard[r][c] = 10;
            }
        }
        r = 0;
        c = 0;
        while (fileReader.hasNextLine()) {
            String holdBoard = fileReader.nextLine();
            if (holdBoard.length() != 9) {
                return null;
            }
            for (int counter = 0; counter < holdBoard.length(); counter++) {
                if (holdBoard.charAt(counter) == '-') {
                    newBoard[r][c] = 0;
                } else if (holdBoard.charAt(counter) - 48 >= 1 && holdBoard.charAt(counter) - 48 <= 9) {
                    newBoard[r][c] = holdBoard.charAt(counter) - 48;
                } else {
                    return null;
                }
                if (c == 8 && r == 8) {
                    break;
                } else if (c == 8) {
                    c = 0;
                    r++;
                } else {
                    c++;
                }
            }
        }
        return newBoard;
    }
    public int get(int row, int col) {
        return board[row][col];
    }
    public void set(int row, int col, int value) {
        board[row][col] = value;
    }
    public boolean containsInRow(int row, int number) {
        int col = 0;
        while (col < 9) {
            if (board[row][col] == number) {
                return true;
            }
            col++;
        }
        return false;
    }
    public boolean containsInCol(int col, int number) {
        int row = 0;
        while (row < 9) {
            if (board[row][col] == number) {
                return true;
            }
            row++;
        }
        return false;
    }
    public boolean containsInBox(int row, int col, int number) {
        int lRow = row % 3;
        int lCol = col % 3;
        int endRow = row - lRow + 2;
        int endCol = col - lCol + 2;
        int beginRow = row - lRow;
        int beginCol = col - lCol;
        for (int counter1 = beginRow; counter1 <= endRow; counter1++) {
            for (int counter2 = beginCol; counter2 <= endCol; counter2++) {
                if (board[counter1][counter2] == number) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isAllowed(int row, int col, int number) {
        boolean hold1 = containsInRow(row, number);
        boolean hold2 = containsInCol(col, number);
        boolean hold3 = containsInBox(row, col, number);
        boolean hold4 = board[row][col] == 0;
        return !hold1 && !hold2 && !hold3 && hold4;
    }
    public String toString() {
        int r;
        int c;
        String stringBoard = "+-------+-------+-------+\n";
        for (r = 0; r < 9; r++) {
            for (c = 0; c < 9; c++) {
                if (board[r][c] != 0) {
                    if (c % 3 == 0) {
                        stringBoard = stringBoard + "| " + board[r][c] + " ";
                    } else if (c == 8) {
                        stringBoard = stringBoard + board[r][c] + " |\n";
                    } else {
                        stringBoard = stringBoard + board[r][c] + " ";
                    }
                } else {
                    if (c % 3 == 0) {
                        stringBoard = stringBoard + "| - ";
                    } else if (c == 8) {
                        stringBoard = stringBoard + "- |\n";
                    } else {
                        stringBoard = stringBoard + "- ";
                    }
                }
            }
            if (r % 3 == 2 && r != 8) {
                stringBoard = stringBoard + "+-------+-------+-------+\n";
            }
        }
        stringBoard = stringBoard + "+-------+-------+-------+";
        return stringBoard;
    }
    public boolean allFilled() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean[][] originalFill() {
        boolean[][] original = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if(board[r][c] != 0) {
                    original[r][c] = true;
                } else {
                    original[r][c] = false;
                }
            }
        }
        return original;
    }
}
