private const val THREE = 3

data class Monkey(val items: MutableList<Long>,
                  val operation: String,
                  val operationValue: Long?,
                  val test: Long,
                  val testTrue: Int,
                  val testFalse: Int,
                  var inspections: Long
) {
    fun operate(item: Long): Long {
        val value = operationValue ?: item
        return when (operation) {
            "*" -> item * value
            "+" -> item + value
            else -> 0
        }
    }
    fun incrementInspections() { inspections += 1 }
}



fun main() {
    fun parseMonkeys(input: List<String>): MutableList<Monkey> {
        val monkeys = mutableListOf<Monkey>()
        var items = mutableListOf<Long>()
        var operation = ""
        var operationValue: Long? = 0
        var test = 0L
        var testTrue = 0
        var testFalse = 0
        for (line in input) {
            if (line.isNotEmpty()) {
                if (line.contains("items")) {
                    val lineSplit = line.split(":")[1].split(",").map { it.trim().toLong() }
                    items = lineSplit.toMutableList()
                } else if (line.contains("Operation")) {
                    val lineSplit = line.split(" ")
                    operation = lineSplit[6]
                    operationValue = if (lineSplit[7] == "old") null else lineSplit[7].toLong()
                } else if (line.contains("Test:")) {
                    test = line.split(" ")[5].toLong()
                } else if (line.contains("If true:")) {
                    testTrue = line.split(" ")[9].toInt()
                } else if (line.contains("If false:")) {
                    testFalse = line.split(" ")[9].toInt()
                }
            } else {
                monkeys.add(Monkey(items, operation, operationValue, test, testTrue, testFalse, 0L))
            }
        }
        return monkeys
    }

    fun part1(input: List<String>): Long {
        val monkeys = parseMonkeys(input)

        for (z in 0 until 20) {
            for (i in 0 until monkeys.size) {
                val monkey = monkeys[i]
                while (monkey.items.isNotEmpty()) {
                    val item = monkey.items.removeFirst()
                    val worried = monkey.operate(item)
                    val bored = worried / THREE
                    val monkeyToPass = if (bored % monkey.test == 0L) monkey.testTrue else monkey.testFalse
                    monkeys[monkeyToPass].items.add(bored)
                    monkey.incrementInspections()
                }
            }
        }

        val inspections = mutableListOf<Long>()
        for (monkey in monkeys) {
            inspections.add(monkey.inspections)
        }
        inspections.sort()
        return inspections[monkeys.size - 1] * inspections[monkeys.size - 2]
    }

    fun part2(input: List<String>): Long {
        val monkeys = parseMonkeys(input)
        val testProduct: Long = monkeys.map { it.test }.reduce(Long::times)
        for (z in 0 until 10000) {
            for (i in 0 until monkeys.size) {
                val monkey = monkeys[i]
                for (item in monkey.items) {
                    val worried = monkey.operate(item) % testProduct
                    val monkeyToPass = if (worried % monkey.test == 0L) monkey.testTrue else monkey.testFalse
                    monkeys[monkeyToPass].items.add(worried)
                }
                monkey.inspections += monkey.items.size
                monkey.items.clear()
            }
        }
        val inspections = mutableListOf<Long>()
        for (monkey in monkeys) {
            inspections.add(monkey.inspections)
        }
        inspections.sort()
        return inspections[monkeys.size - 1] * inspections[monkeys.size - 2]
    }

    val testInput = readInput("testDay11")
    val result = part1(testInput)
    print("result => ")
    println(result)

    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

