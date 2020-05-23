package com.crisfranco.myconference.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Conference
import kotlinx.android.synthetic.main.fragment_conference_detail_dialog.*
import java.text.SimpleDateFormat


class ConferenceDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conference_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarConferenceDetail.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarConferenceDetail.setTitleTextColor(Color.WHITE)
        toolbarConferenceDetail.setNavigationOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConferenceDetail.title = conference.title

        textViewConferenceDetailTitle.text = conference.title
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        val date = simpleDateFormat.format(conference.datetime)
        textViewConferenceDetailHour.text = date
        textViewConferenceDetailSpeaker.text = conference.speaker
        textViewConferenceDetailTopic.text = conference.tag
        textViewConferenceDetailDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


}
