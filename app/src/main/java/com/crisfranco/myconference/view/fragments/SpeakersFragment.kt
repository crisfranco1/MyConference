package com.crisfranco.myconference.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Speaker
import com.crisfranco.myconference.view.adapters.SpeakersItemAdapter
import com.crisfranco.myconference.view.adapters.SpeakersItemListener
import com.crisfranco.myconference.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment(), SpeakersItemListener {

    private lateinit var speakerViewModel: SpeakerViewModel
    private lateinit var speakersItemAdapter: SpeakersItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speakerViewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        speakerViewModel.refresh();
        speakersItemAdapter = SpeakersItemAdapter(this)
        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakersItemAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        speakerViewModel.speakers.observe(this, Observer<List<Speaker>> { speakers ->
            speakers.let {
                speakersItemAdapter.updateData(speakers)
            }
        })

        speakerViewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null) {
                rlLoadingSpeakers.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.dSpeakerDetail, bundle)
    }

}
