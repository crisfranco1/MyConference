package com.crisfranco.myconference.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Speaker
import kotlinx.android.synthetic.main.fragment_speaker_detail_dialog.*


class SpeakerDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speaker_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val speaker = arguments?.getSerializable("speaker") as Speaker

        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
        toolbar.title = speaker.name

        Glide.with(ivDetailSpeakerImage.context).load(speaker.image)
            .apply(RequestOptions.circleCropTransform()).into(ivDetailSpeakerImage)

        tvDetailSpeakerName.text = speaker.name
        tvDetailSpeakerJobtitle.text=speaker.jobtitle
        tvDetailSpeakerWorkplace.text=speaker.workplace

        tvDetailSpeakerBiography.text=speaker.biography
    }

}
