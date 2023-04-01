package com.dev.rmp.clockwidget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.content.Context

val Context.appWidgetManager
get() = this.getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager

val Context.alarmManager
get() = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager