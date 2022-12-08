const val lowercase = 96
const val uppercase = 38

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for(rucksack in input) {
            val mid = rucksack.length / 2
            val s1 = rucksack.subSequence(0, mid)
            val s2 = rucksack.subSequence(mid, rucksack.length)

            for (letter in s1) {
                if (s2.contains(letter)) {
                    var a = letter.code
                    a -= if (a > lowercase) {
                        lowercase
                    } else {
                        uppercase
                    }
                    result += a
                    break
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val length = input.size - 1
        for(i in 0..length step 3) {
            val s2 = input[i + 1].subSequence(0, input[i + 1].length)
            val s3 = input[i + 2].subSequence(0, input[i + 2].length)
            for (letter in input[i]) {
                if (s2.contains(letter) and s3.contains(letter)) {
                    var a = letter.code
                    a -= if (a > lowercase) {
                        lowercase
                    } else {
                        uppercase
                    }
                    result += a
                    break
                }
            }
        }

        return result
    }

    val testInput = readInput("testDay03")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

