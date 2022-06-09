package com.example.dagger

import android.util.Log

interface  AnalyticsService {

    fun trackEvent(eventName: String, eventType: String)
}

class MixPanel : AnalyticsService {
    override fun trackEvent(eventName: String, eventType: String) {
       Log.d("mixpanel tag", "mixpanel - $eventName + $eventType")
    }

}

class FirebaseAnalytics : AnalyticsService{
    override fun trackEvent(eventName: String, eventType: String) {
        Log.d("FirebaseAnalytics tag", "FirebaseAnalytics - $eventName + $eventType")

    }

}