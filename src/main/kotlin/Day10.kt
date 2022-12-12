data class SignalCycle(val value: Int, val cycle: Int)

fun main() {
    fun getQueue(input: List<String>): MutableList<SignalCycle> {
        val queue = mutableListOf<SignalCycle>()
        var cyclePosition = 0
        for (i in input.indices) {
            val instruction = input[i]
            queue.add(SignalCycle(0,0))
            cyclePosition += 1
            if (instruction[0] == 'a') {
                val value = instruction.split(" ")[1].toInt()
                queue.add(SignalCycle(value, cyclePosition))
                cyclePosition += 1
            }
        }
        return queue
    }
    fun part1(input: List<String>): Int {
        var result = 0
        var x = 1
        val queue = getQueue(input)
        for (i in queue.indices) {
            if (i == 19 || i == 59 || i == 99 || i == 139 || i == 179 || i == 219) {
                result += x * (i + 1)
            }
            val signal = queue.removeFirst()
            x += signal.value
        }
        return result
    }

    fun part2(input: List<String>): MutableList<String> {
        val result = mutableListOf<String>()
        var x = 1
        val queue = getQueue(input)
        for (i in queue.indices) {
            val position = i % 40
            if (x == position || x == position - 1 || x == position + 1) result.add("#")
            else result.add(".")

            val signal = queue.removeFirst()
            x += signal.value
        }
        return result
    }

    val testInput = readInput("testDay10")
    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    println("result part2 => ")
    for (i in 0 until 6) {
        for (j in 0 until  40) {
            print(result2[0])
            result2.removeFirst()
        }
        println("")
    }
}

