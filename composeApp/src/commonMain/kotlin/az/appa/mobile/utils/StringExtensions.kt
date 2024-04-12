package az.appa.mobile.utils

fun String.isOtpValid(otpCount: Int): Boolean {
    return this.count() == otpCount
}