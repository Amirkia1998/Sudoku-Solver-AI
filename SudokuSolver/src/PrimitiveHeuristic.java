

import sac.State;
import sac.StateFunction;

public class PrimitiveHeuristic extends StateFunction {

    public double calculate(State state) {
        Sudoku sudoku = (Sudoku) state;
        return sudoku.getUnknownsCount();
    }

}
