package az.appa.mobile

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIImpactFeedbackGenerator
import platform.UIKit.UINotificationFeedbackGenerator

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

internal actual fun hapticFeedback() {
    val generator = UIImpactFeedbackGenerator()
    generator.prepare()
    generator.impactOccurredWithIntensity(1.0)
}

internal actual fun getPlatform(): String {
    return "ios"
}

internal actual fun isAndroid() = false