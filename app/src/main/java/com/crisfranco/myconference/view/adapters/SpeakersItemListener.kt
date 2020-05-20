package com.crisfranco.myconference.view.adapters

import com.crisfranco.myconference.model.Speaker

interface SpeakersItemListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}