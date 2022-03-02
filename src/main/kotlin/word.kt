fun main() {
    println("Введите строку")
    var str: String = readln()
    if (str.isEmpty()) {
        println("Вы ничего не ввели!")
        return
    }

    str = str.lowercase()
    println(str)

    var wordNum = 0
    var word = ' '
    for (i in str.indices) {
        var num = 0
        for (j in str.indices) {
            if (str[i] == str[j])
                num++
        }
        if (num > wordNum) {
                wordNum = num
                word = str[i]
            }
        }

    println("$word встречается чаще всего")
    println("Она встречается $wordNum раз")
}