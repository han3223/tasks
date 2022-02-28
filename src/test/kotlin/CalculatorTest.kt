import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CalculatorTest {
    private val testCal = Calculator()
    @Test
    fun testStart() {
        assertEquals("Ошибка! Вы ничего не ввели!",testCal.start(""))
        assertEquals("Ошибка!!! Пример содержит некорректные символы!",testCal.start("100ф + 214"))
        assertEquals("Отсутствуют пробелы!", testCal.start("100 +100"))
        assertEquals("Отсутствуют пробелы!", testCal.start("100+ 100"))
        assertEquals("Отсутствуют пробелы!", testCal.start("100+100"))
        assertEquals("Введены два числа подряд!", testCal.start("100 100"))
        assertEquals("Отсутствуют пробелы!", testCal.start("100 ++ 100"))
        assertEquals("Ошибка!!! Вы ввели два знака подряд! Желаете повторить попытку!", testCal.queueProcessing("100 + + 100"))
    }
}