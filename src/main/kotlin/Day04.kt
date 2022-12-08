fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for(section in input) {
            val elves = section.split(",").map { it.split("-").map { data -> data.toInt() } }
            val elf1start = elves[0][0]
            val elf1end = elves[0][1]
            val elf2start = elves[1][0]
            val elf2end = elves[1][1]

            if (elf1start in elf2start..elf2end && elf1end in elf2start..elf2end ||
                elf2start in elf1start..elf1end && elf2end in elf1start..elf1end ) {
                result += 1
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for(section in input) {
            val elves = section.split(",").map { it.split("-").map { data -> data.toInt() } }
            val elf1start = elves[0][0]
            val elf1end = elves[0][1]
            val elf2start = elves[1][0]
            val elf2end = elves[1][1]

            if (elf1start in elf2start..elf2end || elf1end in elf2start..elf2end ||
                elf2start in elf1start..elf1end || elf2end in elf1start..elf1end ) {
                result += 1
            }
        }
        return result
    }

    val testInput = readInput("testDay04")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

