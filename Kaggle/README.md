# Painting Exhibition Arrangement

## Overview
This program arranges paintings for an exhibition to maximize the score between adjacent paintings.
It reads input files containing information about different types of paintings, their keys, and their relationships.
It then performs various operations to determine the best arrangement of paintings, calculates the exhibition score, and writes the arranged paintings to output files.

## File Structure
The code consists of the following files:

- `score_checker.py`: Defines the `Scorer` class to calculate the score of the exhibition.
- `main.py`: Contains the main logic for arranging paintings and calculating scores.
- `input/`: Directory containing input files with information about paintings.
- `output/`: Directory where the arranged paintings will be written.

## Execution Flow

The program follows the following execution flow:

1. Import necessary modules and classes.
2. Define the `Painting` class, representing a single painting with its properties.
3. Implement helper functions:
    - `read_objects_from_file(input_file)`: Reads objects from the input file and returns a list of `Painting` objects.
    - `combine_portraits(objects)`: Combines portrait paintings into pairs and returns a list of pairs.
    - `write_objects_to_file(objects, output_file)`: Writes objects to the output file.
    - `get_score(frame1, obj)`: Calculates the score between two paintings or a painting and a pair of paintings.
    - `get_best_objects(objects)`: Rearranges the list of paintings to maximize the score between adjacent paintings.
4. Define the main entry point of the program:
    - Set up the list of input files to process.
    - Create a `ProcessPoolExecutor` with a maximum of 3 concurrent processes.
    - Submit tasks to the executor to process each input file concurrently.
    - Wait for the tasks to complete and retrieve the results.
    - Write the arranged paintings to output files.
    - Calculate the score of the exhibition for each file using the `Scorer` class.
    - Print the scores and execution times for each file.
    - Calculate and print the total score for all files.
5. Execute the program when the script is run directly.

## Usage

To use this program, follow these steps:

1. Place the input files in the `input/` directory.
2. Run the `main.py` script.
3. The program will read the input files, arrange the paintings, calculate the scores, and write the arranged paintings to the `output/` directory.
4. The scores and execution times for each file will be printed.
5. The total score for all files will be printed at the end.

## Dependencies
The code requires the following dependencies:

- `concurrent.futures`: Used for concurrent execution of tasks.
- `datetime`: Used for measuring execution time.

## Compatibility
The code is compatible with Python 3.x.

## Conclusion
This program efficiently arranges paintings for an exhibition by maximizing the score between adjacent paintings.
It provides flexibility for handling various types of paintings and calculating the exhibition score.
By using concurrent execution, it processes multiple input files concurrently, reducing overall execution time.