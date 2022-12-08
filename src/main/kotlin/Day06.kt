private const val LIMIT_4 = 4
private const val LIMIT_14 = 14

fun main() {

    fun part1(input: List<String>, limit: Int): Int {
        var result = 0
        val data = mutableListOf<Char>()
        val line = input[0]
        for (letter in line) {
            result += 1
            if (!data.contains(letter)) {
                data.add(letter)
                if (data.size == limit) break
            } else {
                val index = data.indexOf(letter)
                for (i in 0.. index) {
                    data.removeFirst()
                }
                data.add(letter)
            }
        }
        
        return result
    }


    val testInput = readInput("testDay06")
    val result = part1(testInput, LIMIT_4)
    print("result => ")
    println(result)


    val result2 = part1(testInput, LIMIT_14)
    print("result part2 => ")
    println(result2)

}

