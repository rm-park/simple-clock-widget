package com.dev.rmp.clockwidget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dev.rmp.clockwidget.databinding.ActivityWidgetSettingBinding

class WidgetSettingActivity : AppCompatActivity() {

    private val layout: ActivityWidgetSettingBinding by lazy {
        ActivityWidgetSettingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate:")
        setContentView(layout.root)

        layout.apply {
            widgetSettingButtonSetMetadata.apply {
                setOnClickListener {
                    Log.d(TAG, "widgetSettingButtonSetMetadata: onClick:")

                    context.sendBroadcast(Intent().apply {
                        action = ACTION_APP_WIDGET_UPDATE
                        `package` = PACKAGE_NAME
                    })
                }
            }
        }
    }

    companion object {
        private const val TAG = "WidgetSettingActivity"
    }
}