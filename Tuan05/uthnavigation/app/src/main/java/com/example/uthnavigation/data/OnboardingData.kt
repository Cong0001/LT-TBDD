package com.example.uthnavigation.data

import com.example.uthnavigation.R
import com.example.uthnavigation.model.OnboardingItem

object OnboardingData {
    val items = listOf(
        OnboardingItem(
            "Easy Time Management",
            "With management based on priority and daily tasks, it will give you convenience.",
            R.drawable.onboard_1
        ),
        OnboardingItem(
            "Increase Work Effectiveness",
            "Time management helps you improve productivity and efficiency.",
            R.drawable.onboard_2
        ),
        OnboardingItem(
            "Reminder Notification",
            "This application provides reminders so you don't forget important tasks.",
            R.drawable.onboard_3
        )
    )
}
