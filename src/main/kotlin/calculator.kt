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
        val signs = "+-/"
        //Проверки на различные ошибки про вводе выражения
        for (char in expression.indices) {

            if (expression[char] == ' ' && (expression[char + 1] in signs.first()..signs.last() || expression[char + 1] == '*') && expression[char + 2] in '0'..'9') {
                if (expression[char] == ' ' && (expression[char + 1] == '-') && expression[char + 2] in '0'..'9'){continue}
                else {
                    println("Отсутствуют пробелы после знака!")
                    return "Отсутствуют пробелы после знака!"
                }

            } else if (expression[char] in '0'..'9' && (expression[char + 1] in signs.first()..signs.last() || expression[char + 1] == '*') && expression[char + 2] == ' ') {
                println("Отсутствуют пробелы до знака!")
                return "Отсутствуют пробелы до знака!"
            } else if (expression[char] in '0'..'9' && (expression[char + 1] in signs.first()..signs.last() || expression[char + 1] == '*') && expression[char + 2] in '0'..'9') {
                println("Отсутствуют пробелы до и после знака!")
                return "Отсутствуют пробелы до и после знака!"
            } else if ((expression[char] in signs.first()..signs.last() || expression[char] == '*') && (expression[char + 1] in signs.first()..signs.last() || expression[char + 1] == '*')) {
                println("Отсутствуют пробелы между знаками!")
                return "Отсутствуют пробелы между знаками!"
            } else if (expression[char] in '0'..'9' && expression[char + 1] == ' ' && expression[char + 2] in '0'..'9') {
                println("Введены два числа подряд!")
                return "Введены два числа подряд!"
            }
            if (char + 2 == expression.length) {
                break
            }
            if (expression.last() in signs.first() .. signs.last() || expression.last() == '*'){
                println("Ошибка!!! Вы ввели последним символом знак!")
                return "Ошибка!!! Вы ввели последним символом знак!"
            }
        }

        listProcessing(expression)
        return ""
    }

    fun listProcessing(expression: String): String {
        val listExpressionElements = LinkedList<String>()
        var index = 0

        //Зполнение элементами
        for (char in expression.indices){
            if (expression[char] == ' ')
            {
                listExpressionElements.add(expression.substring(index, char))
                index = char + 1
            }
            if (char == expression.lastIndex)
            {
                listExpressionElements.add(expression.substring(index, char + 1))
            }
        }

        //Проверка на два знака
        for (char in listExpressionElements.indices)
        {
            if ((listExpressionElements[char] == "*" || listExpressionElements[char] == "/" || listExpressionElements[char] == "+" || listExpressionElements[char] == "-") && (listExpressionElements[char + 1] == "*" || listExpressionElements[char + 1] == "/" || listExpressionElements[char + 1] == "+" || listExpressionElements[char + 1] == "-")) {
                println("Ошибка!!! Вы ввели два знака подряд! Желаете повторить попытку!")
                return "Ошибка!!! Вы ввели два знака подряд! Желаете повторить попытку!"
            }
            if (listExpressionElements[char] == "/" && listExpressionElements[char + 1] == "0") {
                println("Ошибка!!! Делить на ноль нельзя!")
                return "Ошибка!!! Делить на ноль нельзя!"
            }
        }

        //Упрощение выражения
        var ch = 0
        while (ch != listExpressionElements.lastIndex) {
            var num: Int
            if (listExpressionElements.elementAt(ch) == "*") {
                num = listExpressionElements.elementAt(ch - 1).toInt()
                num *= listExpressionElements.elementAt(ch + 1).toInt()
                for (n in 0 .. 2)
                    listExpressionElements.removeAt(ch - 1)
                listExpressionElements.add(ch - 1, num.toString())
                ch = 0
            }
            else if (listExpressionElements.elementAt(ch) == "/") {
                num = listExpressionElements.elementAt(ch - 1).toInt()
                num /= listExpressionElements.elementAt(ch + 1).toInt()
                for (n in 0 .. 2)
                    listExpressionElements.removeAt(ch - 1)
                listExpressionElements.add(ch - 1, num.toString())
                ch = 0
            }
            if (listExpressionElements.size == 1)
                break
            ch++
        }

        //Подсчёт выражения
        var num = listExpressionElements.first.toInt()
        listExpressionElements.removeFirst()
        while (!listExpressionElements.isEmpty())
        {
            if (listExpressionElements.first == "+") {
                listExpressionElements.removeFirst()
                num += listExpressionElements.first.toInt()
            }
            if (listExpressionElements.first == "-") {
                listExpressionElements.removeFirst()
                num -= listExpressionElements.first.toInt()
            }
            listExpressionElements.removeFirst()
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








