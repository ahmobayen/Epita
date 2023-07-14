# KCW Team 11

# Chistyakov Nikita
# Ghandehari Dornaz
# Javanitalab Mohammad
# Mobayen Amir
# Sharma Rajat


class Painting:
    def __init__(self, pid, painting_type, num_keys, keys):
        """
        Represents a painting object.

        Args:
            pid (int): Painting ID.
            painting_type (str): Type of painting (either 'L' for landscape or 'P' for portrait).
            num_keys (int): Number of keys.
            keys (set): Set of keys.

        Attributes:
            pid (int): Painting ID.
            painting_type (str): Type of painting.
            num_keys (int): Number of keys.
            keys (set): Set of keys.
        """
        self.pid = pid
        self.painting_type = painting_type
        self.num_keys = num_keys
        self.keys = keys

    def __str__(self):
        """
        Returns a string representation of the Painting object.

        Returns:
            str: String representation of the Painting object.
        """
        return f'{self.pid} {self.num_keys}'


def read_objects_from_file(input_file):
    """
    Reads a list of paintings from a file.

    Args:
        input_file (str): Path to the input file.

    Returns:
        list: List of Painting objects representing the paintings.
    """
    landscapes = []
    portraits = []
    with open(input_file, mode='r') as f:
        total_paintings = f.readline().strip()
        line = f.readline().strip()

        index = 0
        while line:
            string_split = line.split(" ")
            if string_split[0] == 'L':
                landscapes.append(Painting(index, string_split[0], int(string_split[1]), set(string_split[2:])))
            else:
                portraits.append(Painting(index, string_split[0], int(string_split[1]), set(string_split[2:])))
            index += 1
            line = f.readline().strip()
    return sorted(landscapes, key=lambda obj: obj.num_keys) + combine_portraits(portraits)


def combine_portraits(objects):
    """
    Combines a list of portrait paintings into pairs.

    Args:
        objects (list): List of Portrait Painting objects.

    Returns:
        list: List of pairs of Portrait Painting objects.
    """
    objects = sorted(objects, key=lambda obj: obj.num_keys)
    length = len(objects)
    return [[obj1, obj2] for obj1, obj2 in zip(objects[:length // 2], reversed(objects[length // 2:]))]


def write_objects_to_file(objects, output_file):
    """
    Writes a list of paintings to a file.

    Args:
        objects (list): List of Painting objects.
        output_file (str): Path to the output file.
    """
    with open(output_file, mode='w') as f:
        f.write(str(len(objects)) + " \n")
        for obj in objects:
            if isinstance(obj, list):
                if len(obj) == 1:
                    f.write(str(obj[0].pid) + " \n")
                else:
                    f.write(f"{obj[0].pid} {obj[1].pid} \n")
            else:
                f.write(str(obj.pid) + " \n")


def get_score(frame1, obj):
    """
    Calculates the score for a pair of paintings based on the number of common keys.

    Args:
        frame1 (set): Set of keys from the first painting.
        obj (Painting or list): Painting or a pair of Paintings.

    Returns:
        int: Score indicating the number of common keys.
    """
    frame2 = obj[0].keys | obj[1].keys if isinstance(obj, list) else obj.keys
    val1 = len(frame1.intersection(frame2))
    val2 = len(frame1) - val1
    val3 = len(frame2) - val1
    return min(val1, val2, val3)


def get_best_objects(objects):
    """
    Rearranges the list of paintings to maximize the score between adjacent paintings.

    Args:
        objects (list): List of Painting objects.

    Returns:
        list: List of rearranged Painting objects.
    """
    frame_glass_ordered = []
    for idx, obj in enumerate(objects):
        if idx != len(objects) - 1:
            frame1 = obj[0].keys | obj[1].keys if isinstance(obj, list) else obj.keys
            best_obj_idx = max(range(idx + 1, len(objects)), key=lambda j: get_score(frame1, objects[j]))
            objects[idx + 1], objects[best_obj_idx] = objects[best_obj_idx], objects[idx + 1]
        frame_glass_ordered.append(obj)
    return frame_glass_ordered
