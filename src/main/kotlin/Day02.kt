fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for(hand in input) {
            val data = hand.split(' ')
            when (data[1]) {
                "X" -> result += 1
                "Y" -> result += 2
                "Z" -> result += 3
            }
            if (data[0] == "A" && data[1] == "X" ||
                data[0] == "B" && data[1] == "Y" ||
                data[0] == "C" && data[1] == "Z") {
                result += 3
            } else if (data[0] == "A" && data[1] == "Y" ||
                data[0] == "B" && data[1] == "Z" ||
                data[0] == "C" && data[1] == "X") {
                result += 6
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for(hand in input) {
            val data = hand.split(' ')
            if (data[1] == "Y") {
                when (data[0]) {
                    "A" -> result += 4
                    "B" -> result += 5
                    "C" -> result += 6
                }
            } else if (data[1] == "X") {
                when (data[0]) {
                    "A" -> result += 3
                    "B" -> result += 1
                    "C" -> result += 2
                }
            } else {
                when (data[0]) {
                    "A" -> result += 8
                    "B" -> result += 9
                    "C" -> result += 7
                }
            }
        }
        return result
    }

    val testInput = readInput("testDay02")
    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

