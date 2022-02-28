import java.util.LinkedList


class Calculator {

    fun start(expression: String) : String {
        //Проверка на ввод
        if (expression.isBlank()) {
            println("Ошибка! Вы ничего не ввели!")
            return "Ошибка! Вы ничего не ввели!"
        }

        //Проверка на корректные символы
        for (char in expression.indices) {
            if (expression.elementAt(char) in '0' .. '9') {continue}
            else if (expression.elementAt(char) == '-' || expression.elementAt(char) == '+') {continue}
            else if (expression.elementAt(char) == '*' || expression.elementAt(char) == '/') {continue}
            else if (expression.elementAt(char) == ' ') {continue}
            else {
                println("Ошибка!!! Пример содержит некорректные символы!")
                return "Ошибка!!! Пример содержит некорректные символы!"
            }
        }
        val str = "+-/"
        //Проверки на различные ошибки про вводе выражения
        for (char in expression.indices) {

            if (expression[char] == ' ' && (expression[char + 1] in str.first()..str.last() || expression[char + 1] == '*') && expression[char + 2] in '0'..'9') {
                if (expression[char] == ' ' && (expression[char + 1] == '-') && expression[char + 2] in '0'..'9'){continue}
                else {
                    println("Отсутствуют пробелы!")
                    return "Отсутствуют пробелы!"
                }

            } else if (expression[char] in '0'..'9' && (expression[char + 1] in str.first()..str.last() || expression[char + 1] == '*') && expression[char + 2] == ' ') {
                println("Отсутствуют пробелы!")
                return "Отсутствуют пробелы!"
            } else if (expression[char] in '0'..'9' && (expression[char + 1] in str.first()..str.last() || expression[char + 1] == '*') && expression[char + 2] in '0'..'9') {
                println("Отсутствуют пробелы!")
                return "Отсутствуют пробелы!"
            } else if ((expression[char] in str.first()..str.last() || expression[char] == '*') && (expression[char + 1] in str.first()..str.last() || expression[char + 1] == '*')) {
                println("Отсутствуют пробелы!")
                return "Отсутствуют пробелы!"
            } else if (expression[char] in '0'..'9' && expression[char + 1] == ' ' && expression[char + 2] in '0'..'9') {
                println("Введены два числа подряд!")
                return "Введены два числа подряд!"
            }
            if (char + 2 == expression.length) {
                break
            }
            if (expression.last() in str.first() .. str.last() || expression.last() == '*'){
                println("Ошибка!!! Вы ввели последним символом знак!")
                return "Ошибка!!! Вы ввели последним символом знак!"
            }
        }

        queueProcessing(expression)
        return ""
    }

    fun queueProcessing(expression: String): String {
        val spaceQueue = LinkedList<String>()
        var index = 0

        //Зполнение элементами
        for (char in expression.indices){
            if (expression[char] == ' ')
            {
                spaceQueue.add(expression.substring(index, char))
                index = char + 1
            }
            if (char == expression.lastIndex)
            {
                spaceQueue.add(expression.substring(index, char + 1))
            }
        }

        //Проверка на два знака
        for (char in spaceQueue.indices)
        {
            if ((spaceQueue[char] == "*" || spaceQueue[char] == "/" || spaceQueue[char] == "+" || spaceQueue[char] == "-") && (spaceQueue[char + 1] == "*" || spaceQueue[char + 1] == "/" || spaceQueue[char + 1] == "+" || spaceQueue[char + 1] == "-")) {
                println("Ошибка!!! Вы ввели два знака подряд! Желаете повторить попытку!")
                return "Ошибка!!! Вы ввели два знака подряд! Желаете повторить попытку!"
            }
        }

        //Упрощение выражения
        var ch = 0
        while (ch != spaceQueue.lastIndex) {
            var num: Int
            if (spaceQueue.elementAt(ch) == "*") {
                num = spaceQueue.elementAt(ch - 1).toInt()
                num *= spaceQueue.elementAt(ch + 1).toInt()
                for (n in 0 .. 2)
                    spaceQueue.removeAt(ch - 1)
                spaceQueue.add(ch - 1, num.toString())
                ch = 0
            }
            else if (spaceQueue.elementAt(ch) == "/") {
                num = spaceQueue.elementAt(ch - 1).toInt()
                num /= spaceQueue.elementAt(ch + 1).toInt()
                for (n in 0 .. 2)
                    spaceQueue.removeAt(ch - 1)
                spaceQueue.add(ch - 1, num.toString())
                ch = 0
            }
            if (spaceQueue.size == 1)
                break
            ch++
        }

        //Подсчёт выражения
        var num = spaceQueue.first.toInt()
        spaceQueue.removeFirst()
        while (!spaceQueue.isEmpty())
        {
            if (spaceQueue.first == "+") {
                spaceQueue.removeFirst()
                num += spaceQueue.first.toInt()
            }
            if (spaceQueue.first == "-") {
                spaceQueue.removeFirst()
                num -= spaceQueue.first.toInt()
            }
            spaceQueue.removeFirst()
        }
        println(num)

        return ""
    }
}

fun main() {
    println("Введите выражение для подсчёта(c пробелами): ")
    val text: String = readln()
    Calculator().start(text)
}








