fun main() {
    fun prepareData(input: List<String>): MutableList<MutableList<Char>> {
        var columnsNumber = 0
        for (line in input) {
            if (line[1] == '1') {
                val numbers = line.split("   ")
                columnsNumber = numbers[numbers.size - 1].trim().toInt()
                break
            }
        }

        val columns: MutableList<MutableList<Char>> = MutableList(columnsNumber) { mutableListOf() }
        for (line in input) {
            if (line[1] == '1') {
                break
            }
            for (i in line.indices step 4) {
                val value = line[i + 1]
                if (value.code != 32) {
                    columns[i / 4].add(0, value)
                }
            }
        }
        return columns
    }

    fun part1(input: List<String>): String {
        val columns = prepareData(input)
        for (line in input) {
            if (line.isNotEmpty() && line[0] == 'm') {
                val data = mutableListOf<Int>()
                val lineSplit = line.split(" ")
                data.add(lineSplit[1].toInt())
                data.add(lineSplit[3].toInt())
                data.add(lineSplit[5].toInt())
                for (i in 0 until data[0]) {
                    val dropped = columns[data[1] - 1].removeLast()
                    columns[data[2] -1].add(dropped)
                }
            }
        }
        var result = ""
        columns.map { if(it.isNotEmpty()) { result += it.removeLast() } }
        return result
    }

    fun part2(input: List<String>): String {
        val columns = prepareData(input)
        for (line in input) {
            if (line.isNotEmpty() && line[0] == 'm') {
                val data = mutableListOf<Int>()
                val lineSplitted = line.split(" ")
                data.add(lineSplitted[1].toInt())
                data.add(lineSplitted[3].toInt())
                data.add(lineSplitted[5].toInt())
                if (data[0] == 1) {
                    val dropped = columns[data[1] - 1].removeLast()
                    columns[data[2] -1].add(dropped)
                } else {
                    val dropped = mutableListOf<Char>()
                    for (i in 0 until data[0]) {
                        dropped.add(0, columns[data[1] - 1].removeLast())
                    }
                    columns[data[2] -1].addAll(dropped)
                }
            }
        }
        var result = ""
        columns.map { if(it.isNotEmpty()) { result += it.removeLast() } }
        return result
    }

    val testInput = readInput("testDay05")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

