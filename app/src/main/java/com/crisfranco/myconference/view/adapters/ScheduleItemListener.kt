package com.crisfranco.myconference.view.adapters

import com.crisfranco.myconference.model.Conference

interface ScheduleItemListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}