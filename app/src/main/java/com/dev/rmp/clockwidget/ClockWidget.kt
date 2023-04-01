package com.dev.rmp.clockwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log

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
        Log.d(TAG, "onUpdate:")

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetIds, appWidgetId)
        }
    }

    /**
     * 최초의 앱 위젯이 등록될 때 호출
     */
    override fun onEnabled(context: Context) {
        Log.d(TAG, "onEnabled:")

    }

    /**
     * 최종 앱 위젯 인스턴스가 삭제될 때 호출
     */
    override fun onDisabled(context: Context) {
        Log.d(TAG, "onDisabled:")
    }


    /**
     * activity 에서 전달한 broadcast 가  received ?
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ${intent?.action}")
        Log.d(TAG, "Who send?: ${intent?.`package`}, ${intent?.component}")
        super.onReceive(context, intent)

        intent?.let {
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                Log.d(TAG, "ACTION_APPWIDGET_UPDATE")
                it.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)?.let { array ->
                    context?.let { ctx ->
                        onUpdate(ctx, AppWidgetManager.getInstance(ctx), array)
                    }
                } ?: Log.e(TAG, "AppWidgetIds is null.")
            }
        }
    }

    companion object {
        private const val TAG = "ClockWidgetProvider"
    }
}