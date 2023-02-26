package com.dev.rmp.clockwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class ClockWidget : AppWidgetProvider() {
    /**
     * updatePeriodMills 라는 업데이트 주기 값에 따라 호출되는 함수
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     * 최초의 앱 위젯이 등록될 때 호출
     */
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is create
    }

    /**
     * 최종 앱 위젯 인스턴스가 삭제될 때 호출
     */
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * activity 에서 전달한 broadcast 가  received ?
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.clock_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}