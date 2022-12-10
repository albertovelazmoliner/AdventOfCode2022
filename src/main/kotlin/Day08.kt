import java.lang.Integer.max

fun main() {

    fun part1(input: List<String>): Int {
        var result = 0
        for(i in input.indices) {
            val line = input[i]
            if (i == 0 || i == input.size - 1) {
                result += line.length
            } else {
                for (j in line.indices) {
                    val number = line[j].toString().toInt()
                    if (j == 0 || j == line.length - 1) {
                        result += 1
                    } else {
                        val splitLine = line.toCharArray().map { it.toString().toInt() }
                        val leftLine = splitLine.take(j).max()
                        val rightLine = splitLine.takeLast(splitLine.size - (j + 1)).max()
                        val upperColumn = mutableListOf<Int>()
                        for(z in 0 until i) {
                            upperColumn.add(input[z][j].toString().toInt())
                        }
                        val upper = upperColumn.max()
                        val lowerColumn = mutableListOf<Int>()
                        for(z in i + 1 until input.size) {
                            lowerColumn.add(input[z][j].toString().toInt())
                        }
                        val lower = lowerColumn.max()
                        if (number > leftLine || number > rightLine || number > upper || number > lower ) {
                            result += 1
                        }
                    }
                }
            }
        }
        return result
    }

    fun findTreeLimit(input: List<Int>, limit: Int): Int {
        var result = 0
        for (num in input) {
            result += 1
            if (num >= limit) break
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for(i in 1 until input.size - 1) {
            val line = input[i]
            for (j in 1 until line.length -2) {
                val number = line[j].toString().toInt()
                val splitLine = line.toCharArray().map { it.toString().toInt() }

                val leftLine = splitLine.take(j).reversed()
                val left = findTreeLimit(leftLine, number)

                val rightLine = splitLine.takeLast(splitLine.size - (j + 1))
                val right = findTreeLimit(rightLine, number)

                val upperColumn = mutableListOf<Int>()
                for(z in 0 until i) {
                    upperColumn.add(input[z][j].toString().toInt())
                }
                val upper = findTreeLimit(upperColumn.reversed(), number)

                val lowerColumn = mutableListOf<Int>()
                for(z in i + 1 until input.size) {
                    lowerColumn.add(input[z][j].toString().toInt())
                }
                val lower = findTreeLimit(lowerColumn, number)

                val relative = left * right * upper * lower

                result = max(result, relative)
            }
        }
        return result
    }

    val testInput = readInput("testDay08")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

