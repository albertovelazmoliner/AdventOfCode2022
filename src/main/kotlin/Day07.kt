import kotlin.math.min

data class Dir(val name: String,
               var dirs: MutableList<Dir>,
               var parent: String?,
               var size: Int = 0)

private const val TOTAL_MEM = 70000000
private const val MIN_MEM = 30000000

fun main() {

    fun sumDirSizes(dir: Dir): Int {
        var sum = dir.size
        for (childDir in dir.dirs) {
            sum += sumDirSizes(childDir)
        }
        return sum
    }

    fun parseData(input: List<String>): MutableList<Dir> {
        val directory = mutableListOf<Dir>()
        val root = Dir("root", mutableListOf(),null)
        directory.add(root)
        var currentNode: Dir = root
        for (line in input) {
            if ("dir" in line) {
                val path = line.split(" ")[1]
                val newDir = Dir(path, mutableListOf(), currentNode.name)
                directory.add(newDir)
                currentNode.dirs.add(newDir)
            } else if ("$ cd" in line && line != "$ cd /") {
                val path = line.split(" ")[2]
                if (path == "..") {
                    currentNode = directory.filter { it.name == currentNode.parent!! &&
                            it.dirs.contains(currentNode)}[0]
                } else {
                    for (i in currentNode.dirs.indices) {
                        if (currentNode.dirs[i].name == path) {
                            currentNode = currentNode.dirs[i]
                            break
                        }
                    }
                }
            } else if (line != "$ ls" && line != "$ cd /"){
                currentNode.size += line.split(" ")[0].toInt()
            }
        }
        return directory
    }

    fun part1(input: List<String>): Int {
        var result = 0
        val directory = parseData(input)

        for (i in directory.indices) {
            var relative = 0
            relative = sumDirSizes(directory[i])
            println("key => ${directory[i].name} // relative => $relative")
            if (relative <= 100000) {
                result += relative
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val directory = parseData(input)
        val freeMem = TOTAL_MEM - sumDirSizes(directory[0])
        val needMem = MIN_MEM - freeMem
        var result = 0

        for (i in directory.indices) {
            val dirSize = sumDirSizes(directory[i])
            println("key => ${directory[i].name} // dirSize => $dirSize")
            if (dirSize > needMem && result != 0) {
                result = min(result, dirSize)
            } else if (dirSize > needMem) {
                result = dirSize
            }
        }
        return result
    }

    val testInput = readInput("testDay07")
    val result = part1(testInput)
    print("result => ")
    println(result)


    val result2 = part2(testInput)
    print("result part2 => ")
    println(result2)

}

