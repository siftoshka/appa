import androidx.compose.ui.window.ComposeUIViewController
import az.appa.mobile.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
