package com.dev.rmp.clockwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val tag = "UpdateWidget"
    Log.d(tag, "updateAppWidget:")

    val intent = Intent(context, WidgetSettingActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    // Construct the RemoteViews object
    // Permission "USE_FULL_SCREEN_INTENT" is mandatory.
    val views = RemoteViews(context.packageName, R.layout.clock_widget).apply {
        setOnClickPendingIntent(appWidgetId, pendingIntent)
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}