package az.appa.mobile.utils

fun String.numberLength(): Int {
    return this.replace(" ", "").length
}

fun String.isNotDigit(): Boolean {
    return !this.matches(Regex("\\d+"))
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}