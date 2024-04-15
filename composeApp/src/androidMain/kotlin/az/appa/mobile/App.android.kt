package az.appa.mobile

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    AndroidApp.INSTANCE.startActivity(intent)
}

internal actual fun hapticFeedback() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        (AndroidApp.INSTANCE.getSystemService(Vibrator::class.java).vibrate(
            VibrationEffect.createOneShot(
                /* milliseconds = */ 50,
                /* amplitude = */ VibrationEffect.EFFECT_TICK
            )
        ))
    }
}

internal actual fun getPlatform(): String {
    return "android"
}

internal actual fun isAndroid() = true