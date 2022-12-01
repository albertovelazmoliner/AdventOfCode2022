fun main() {
    fun part1(input: List<String>): Int {
        var maxCals = 0
        var elfCals = 0
        for (text in input) {
            if (text != "") {
                elfCals += text.toInt()
            } else {
                if (elfCals > maxCals) {
                    maxCals = elfCals;
                }
                elfCals = 0
            }
        }
        return maxCals
    }

    fun part2(input: List<String>): Int {
        var maxCalsList = mutableListOf<Int>()
        var elfCals = 0
        for (text in input) {
            if (text != "") {
                elfCals += text.toInt()
            } else {
                maxCalsList.add(elfCals)
                elfCals = 0
            }
        }
        maxCalsList.sort()
        return maxCalsList[maxCalsList.size - 1] + maxCalsList[maxCalsList.size -2] + maxCalsList[maxCalsList.size -3]
    }

    val testInput = readInput("testDay01")
    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

