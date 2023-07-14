# KCW Team 11

# Chistyakov Nikita
# Ghandehari Dornaz
# Javanitalab Mohammad
# Mobayen Amir
# Sharma Rajat

from score_checker import Scorer
from KCW_Team_11 import *

from concurrent.futures import ProcessPoolExecutor
from datetime import datetime


if __name__ == "__main__":
    files = ['0_example', '10_computable_moments', '11_randomizing_paintings', '110_oily_portraits',
             '1_binary_landscapes']

    files = ['10_computable_moments']
    with ProcessPoolExecutor(max_workers=3) as executor:  # Limiting to 3 concurrent processes
        futures = []
        scores = []
        for file in files:
            start_time = datetime.now()
            # Submit the task to the executor and store the Future object
            future = executor.submit(read_objects_from_file, f'./Input/{file}.txt')
            futures.append((file, future))

        # Wait for the tasks to complete and retrieve the results
        for file, future in futures:
            objects_list = future.result()
            order_of_frame_glasses = get_best_objects(objects_list)
            write_objects_to_file(order_of_frame_glasses, f'./Output/{file}.txt')
            score = Scorer(f'./Input/{file}.txt', f'./Output/{file}.txt')
            score.exhibition_walk()
            result = score.score
            scores.append(result)
            print(f'{file} score = {result} \t\t| Time = {datetime.now() - start_time}')

    print(f'Total score = {sum(scores)} \t\t| Time = {datetime.now() - start_time}')