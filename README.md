# Sudoku Solver

This Sudoku Solver is a Java-based application developed as part of University AI course. It provides an efficient way to solve Sudoku puzzles using various solving algorithms.

## Overview

Sudoku Solver is designed to solve Sudoku puzzles of different sizes, providing a quick and effective solution to Sudoku enthusiasts. With an intuitive command-line interface, it allows users to input puzzles, select solving algorithms, and view the solved results. The project serves as a practical demonstration of various AI techniques applied to puzzle-solving.

## Key Features

- **Solves Sudoku Puzzles**: Capable of solving Sudoku puzzles of various grid sizes (e.g., 9x9).
- **Multiple Solving Algorithms**: Offers different solving algorithms to demonstrate various AI approaches.
- **User-Friendly Interface**: Provides a command-line interface for inputting custom puzzles and selecting solving methods.
- **Detailed Statistics**: Displays solving statistics, including time duration and node expansions.

## How to Use

1. Clone this repository to your local machine.
2. Open the project in your Java development environment.
3. Run the `Sudoku.java` file to start the Sudoku Solver application.
4. Input your Sudoku puzzle as a string of numbers (use '0' for empty cells).
5. Choose a solving algorithm to tackle the puzzle.
6. Wait for the solver to find a solution.
7. View the solved Sudoku puzzle along with solving statistics.

## Solving Statistics

The application provides detailed solving statistics:

- **Duration**: Total time taken to find a solution.
- **Nodes Expanded**: Number of nodes explored during the solving process.

## Custom Puzzles

You can input custom Sudoku puzzles by providing a string of numbers as input to the solver. Use '0' to represent empty cells and provide known values for the puzzle.

## Dependencies

This project uses the SAC library for graph search algorithms, which can be found on GitHub [here](https://github.com/pklesk/sac)

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to use this Sudoku Solver for educational purposes and explore its code to gain insights into AI-based puzzle-solving techniques.
