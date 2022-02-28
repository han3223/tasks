fun matchDetails(inputString: String, whatToFind: String,Register: Boolean, startIndex: Int = 0): String {
    val matchIndex = inputString.indexOf(whatToFind, startIndex, ignoreCase = Register)
    return "Поиск '$whatToFind' в '$inputString' начиная с позиции $startIndex: " +
            if (matchIndex >= 0) "Найдено в позиции $matchIndex" else "Не найдено"
}

fun main() {
    var answer: String
    val register: Boolean

    println("Введите строку: ")
    val string1: String = readln()

    println("Введите подстроку: ")
    val string2: String = readln()

    println("Стоит ли игнорировать регистр?   Да|Нет")
    answer = readln()

    when (answer) {
        "Да", "да" -> {
            register = true
            if (string1.contains(string2, ignoreCase = register)) {
                println("Данная строка существует.")
                println("Желаете найти индекс?   Да|Нет")
                answer = readln()
            } else
                println("Данной строки не существует.")
        }
        "Нет", "нет" -> {
            register = false
            if (string1.contains(string2, ignoreCase = register)) {
                println("Данная строка существует.")
                println("Желаете найти индекс?   Да|Нет")
                answer = readln()
            } else
                println("Данной строки не существует.")
        }
        else -> {
            println("Введены некорректные данные")
            return
        }
    }
    when (answer) {
        "Да", "да" -> {
            println(matchDetails(string1, string2, register, 0))
        }
        "Нет", "нет" -> {
            return
        }
        else -> {
            println("Введены некорректные данные")
        }
    }
}