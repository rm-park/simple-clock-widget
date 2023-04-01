package com.dev.rmp.clockwidget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetIds: IntArray,
    appWidgetId: Int
) {
    val tag = "UpdateWidget"
    Log.d(tag, "updateAppWidget: $appWidgetId")


    val intent = Intent(context, WidgetSettingActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.clock_widget).apply {
        setOnClickPendingIntent(appWidgetId, pendingIntent)
        setTextViewText(R.id.appwidget_text, getCurrentDateTime())
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
    Log.d(tag, "click PendingIntent applied.")

    val alarmIntent = Intent(context, ClockWidget::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds)
    }
    val alarmPendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        alarmIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    context.alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC,
        60 * 1000,
        alarmPendingIntent
    )
    Log.d(tag, "60 seconds period update broadcast applied.")
}

internal fun getCurrentDateTime(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd\nHH:mm", Locale.getDefault())
    return dateFormat.format(calendar.time)
}