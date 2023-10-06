

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import sac.graph.*;

public class Sudoku extends GraphStateImpl {
    public static final int n = 3;
    public static final int n2 = n * n;
    private byte[][] board = null;
    private int unknownsCount = n2 * n2;

    public Sudoku() {
        board = new byte[n2][n2];
        for (int i = 0; i < n2; i++)
            for (int j = 0; j < n2; j++)
                board[i][j] = 0;
    }

    public Sudoku(Sudoku parent) {
        board = new byte[n2][n2];
        for (int i = 0; i < n2; i++)
            for (int j = 0; j < n2; j++)
                board[i][j] = parent.board[i][j];

        parent.unknownsCount = unknownsCount;
    }

    @Override
    public int hashCode() {
//        return toString().hashCode();
        byte[] flatSudoku = new byte[n2 * n2];
        int k = 0;
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
                flatSudoku[k++] = board[i][j];
            }
        }
        return Arrays.hashCode(flatSudoku);
    }

/**
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++)
                result += board[i][j] + ",";
            result += "\n";
        }
        return result;
    }
 **/

    @Override
    public String toString() {
//        StringBuilder result = new StringBuilder();
        StringBuilder result = new StringBuilder(256);
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) {
//                result.append(board[i][j] + ",");
                result.append(board[i][j]);
                result.append(",");
            }
            result.append("\n") ;
        }
        return result.toString();
    }


    public void fromStringN3(String txt) { // dedicated only for n = 3 case
        int k = 0; // for moving along input string
        for (int i = 0; i < n2; i++)
            for (int j = 0; j < n2; j++, k++)
                board[i][j] = Byte.valueOf(txt.substring(k, k + 1));
        refreshUnknownsCount();
    }

    public boolean isLegal() {
        byte[] group = new byte[n2]; // we will put here rows or columns or squares

        // checking rows
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) // only to make a copy
                group[j] = board[i][j];
            if (!isGroupLegal(group))
                return false;
        }

        // checking columns
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n2; j++) // only to make a copy
                group[j] = board[j][i];
            if (!isGroupLegal(group))
                return false;
        }

        // checking squares
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int q = 0;
                for (int k = 0; k < n; k++)
                    for (int l = 0; l < n; l++)
                        group[q++] = board[i * n + k][j * n + l];
                if (!isGroupLegal(group))
                    return false;
            }

        return true;
    }


    public boolean isGroupLegal(byte[] group) {
        boolean[] visited = new boolean[n2 + 1];
        for (int i = 1; i < n2 + 1; i++) //initialize with false
            visited[i] = false;

        for (byte b : group) {
            if (b > 0) {
                if (visited[b])
                    return false; // conflict
                visited[b] = true;
            }
        }
        return true;
    }


    private void refreshUnknownsCount() {
        unknownsCount = 0;
        for (int i = 0; i < n2; i++)
            for (int j = 0; j < n2; j++)
                if (board[i][j] == 0)
                    unknownsCount++;
    }


    @Override
    public List<GraphState> generateChildren() {
        List<GraphState> children = new ArrayList<>();
        //looking for first empty cell (from top-left)
        int i = 0, j = 0; // coordinates of empty cell

        zeroFound: //label
        for (i = 0; i < n2; i++)
            for (j = 0; j < n2; j++)
                if (board[i][j] == 0)
                    break zeroFound;
        // onwards after break
        if (i == n2)
            return children;

        for (int k = 0; k < n2; k++) {
            Sudoku child = new Sudoku(this);
            child.board[i][j] = (byte) (k+1);
            if (child.isLegal()) {
                children.add(child);
                child.refreshUnknownsCount();
            }
        }

        return children;
    }


    public int getUnknownsCount() {
        return unknownsCount;
    }


    @Override
    public boolean isSolution() {
        return ((unknownsCount == 0) && (isLegal()));
    }



    public static void main(String[] args) {
//        String sudokuAsString = "003020600900305001001806400008102900700000008006708200002609500800203009005010300";
        String sudokuAsString =   "000020600000005001001806400008102900700000008006708200002609500800203000005010000";
        Sudoku s = new Sudoku();
        s.fromStringN3(sudokuAsString);
        System.out.println(s);
        System.out.println(s.isLegal());

        Sudoku.setHFunction(new PrimitiveHeuristic());
        GraphSearchConfigurator conf = new GraphSearchConfigurator();
//        conf.setWantedNumberOfSolutions(3);
        conf.setWantedNumberOfSolutions(Integer.MAX_VALUE);
        GraphSearchAlgorithm algo = new BestFirstSearch(s, conf);
        algo.execute();
        List<GraphState> solutions = algo.getSolutions();
        for (GraphState sol : solutions) {
               System.out.println(sol + "\n");
        }

        System.out.println("Time [ms]: " + algo.getDurationTime());
        System.out.println("Closed States: " + algo.getClosedStatesCount());
        System.out.println("Open States(now): " + algo.getOpenSet().size());
        System.out.println("Solutions: " + solutions.size());



    }



}