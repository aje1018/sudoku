import java.io.File;
import java.util.Scanner;
public class Sudoku {
    public static Board board;
    public static int recursionCount;
    public static int backupCount;
    public static boolean[][] originals;
    public Sudoku(Scanner fileReader) {
        board = new Board(fileReader);
        originals = board.originalFill();
        recursionCount = 0;
        backupCount = 0;
    }
    public static void main(String[] args) {
        boolean invalid = true;
        Scanner entry = new Scanner(System.in);
        String fileName;
        while (invalid) {
            System.out.println("Enter the path to the sudoku file:");
            fileName = entry.next();
            try {
                invalid = false;
                File file = new File(fileName);
                Scanner fileReader = new Scanner(file);
                Sudoku newGame = new Sudoku(fileReader);
                System.out.println("Initial configuration of the sudoku");
                System.out.println(board.toString());
                if(newGame.solve(new Location(0, 0))) {
                    System.out.println("Successful!");
                    System.out.println("Final configuration of the sudoku");
                    System.out.println(board.toString());
                    System.out.println("Recursion count = " + recursionCount);
                    System.out.println("Backup count = " + backupCount);
                } else {
                    System.out.println(board.toString());
                    System.out.println("Could not solve sudoku");
                    System.out.println("Recursion count = " + recursionCount);
                    System.out.println("Backup count = " + backupCount);
                }
            } catch (Exception e) {
                invalid = true;
            }
        }
    }
    public boolean solve(Location loc) {
        recursionCount++;
        if (loc == null) {
            return true;
        }
        if (board.get(loc.getRow(), loc.getColumn()) != 0) {
            return solve(loc.next());
        }
        for (int number = 1; number <= 9; number++) {
            if (board.isAllowed(loc.getRow(), loc.getColumn(), number)) {
                board.set(loc.getRow(), loc.getColumn(), number);
                if (solve(loc.next())) {
                    return true;
                } else {
                    board.set(loc.getRow(), loc.getColumn(), 0);
                }
            }
        }
        board.set(loc.getRow(), loc.getColumn(), 0);
        backupCount++;
        return false;
    }
    public int getRecursionCount() {
        return recursionCount;
    }
    public int getBackupCount() {
        return backupCount;
    }
    public Board getBoard() {
        return board;
    }
}
