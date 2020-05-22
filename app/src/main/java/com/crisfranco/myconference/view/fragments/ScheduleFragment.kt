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
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Conference
import com.crisfranco.myconference.view.adapters.ScheduleItemAdapter
import com.crisfranco.myconference.view.adapters.ScheduleItemListener
import com.crisfranco.myconference.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment(), ScheduleItemListener {

    private lateinit var scheduleViewModel: ScheduleViewModel
    private lateinit var scheduleItemAdapter: ScheduleItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        scheduleViewModel.refresh()
        scheduleItemAdapter = ScheduleItemAdapter(this)
        rvConferences.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = scheduleItemAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {

        scheduleViewModel.conferences.observe(this, Observer<List<Conference>> { conference ->
            scheduleItemAdapter.updateData(conference)
        })

        scheduleViewModel.isLoading.observe(this, Observer<Boolean> {
            if (it != null)
                rlSchedule.visibility = View.INVISIBLE
        })

    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.dConferenceDetail, bundle)
    }


}
