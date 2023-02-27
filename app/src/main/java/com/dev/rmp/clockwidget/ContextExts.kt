package com.dev.rmp.clockwidget

import android.appwidget.AppWidgetManager
import android.content.Context

val Context.appWidgetManager
get() = this.getSystemService(Context.APPWIDGET_SERVICE) as AppWidgetManager