import kotlin.math.sqrt

data class Position(var x: Int, var y: Int) {
    fun move(direction: String) {
        when (direction) {
            "R" -> moveRight()
            "L" -> moveLeft()
            "U" -> moveUp()
            "D" -> moveDown()
        }
    }
    private fun moveRight() { x += 1 }
    private fun moveLeft() { x -= 1 }
    private fun moveUp() { y += 1 }
    private fun moveDown() { y -= 1 }
}

fun main() {

    fun calculateDistanceBetweenPoints(
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double) : Double {
        return sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    fun checkAndMoveTail(head: Position, tail: Position) {
        val distance = calculateDistanceBetweenPoints(head.x.toDouble(),
                                                      head.y.toDouble(),
                                                      tail.x.toDouble(),
                                                      tail.y.toDouble())
        if (distance < 2) {
            return
        } else if (distance == 2.0) {
            if (head.x - tail.x == 2) {
                tail.move("R")
            } else if (head.x - tail.x == -2) {
                tail.move("L")
            } else if (head.y - tail.y == 2) {
                tail.move("U")
            } else if (head.y - tail.y == -2) {
                tail.move("D")
            }
        } else if (distance > 2) {
            if ((head.x - tail.x >= 2 && head.y - tail.y >= 1) ||
                (head.y - tail.y >= 2 && head.x - tail.x >= 1)) {
                tail.move("R")
                tail.move("U")
            } else if ((head.x - tail.x >= 2 && head.y - tail.y <= -1) ||
                       (head.y - tail.y >= -2 && head.x - tail.x >= 1)) {
                tail.move("R")
                tail.move("D")
            } else if ((head.x - tail.x <= -2 && head.y - tail.y >= 1) ||
                       (head.y - tail.y >= 2 && head.x - tail.x <= -1)) {
                tail.move("L")
                tail.move("U")
            } else if ((head.x - tail.x <= -2 && head.y - tail.y <= -1) ||
                    (head.y - tail.y <= -2 && head.x - tail.x <= -1)){
                tail.move("L")
                tail.move("D")
            }
        }
    }

    fun part1(input: List<String>): Int {
        val visited = mutableSetOf<Position>()
        val head = Position(0,0)
        val tail = Position(0, 0)
        visited.add(Position(tail.x, tail.y))
        for (instruction in input) {
            val parsedData = instruction.split(" ")
            val direction = parsedData[0]
            val steps = parsedData[1].toInt()

            var movements = 0
            while (movements < steps) {
                head.move(direction)
                checkAndMoveTail(head, tail)
                visited.add(Position(tail.x, tail.y))
                movements += 1
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {
        val visited = mutableSetOf<Position>()
        val rope = mutableListOf<Position>()
        for (i in 1..10) {
            rope.add(Position(0,0))
        }
        visited.add(Position(0, 0))
        for (instruction in input) {
            val parsedData = instruction.split(" ")
            val direction = parsedData[0]
            val steps = parsedData[1].toInt()

            var movements = 0
            while (movements < steps) {
                rope[0].move(direction)
                for (i in 1 until rope.size) {
                    val prev = rope[i - 1]
                    val tail = rope[i]
                    checkAndMoveTail(prev, tail)
                    if (i == rope.size - 1) {
                        visited.add(Position(tail.x, tail.y))
                    }
                }

                movements += 1
            }
        }

        return visited.size
    }

    val testInput = readInput("testDay09")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

