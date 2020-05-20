package com.crisfranco.myconference.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Conference
import java.text.SimpleDateFormat

class ScheduleItemAdapter(val scheduleItemListener: ScheduleItemListener) :
    RecyclerView.Adapter<ScheduleItemAdapter.ViewHolder>() {

    var conferences = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.schedule_item, parent, false
        )
    )

    override fun getItemCount() = conferences.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conference = conferences[position]

        holder.tvConferenceTitle.text = conference.title
        holder.tvConferenceSpeaker.text = conference.speaker
        holder.tvConferenceTopic.text = conference.tag

        val hourSimpleDateFormat = SimpleDateFormat("HH:mm")
        holder.tvConferenceHour.text = hourSimpleDateFormat.format(conference.datetime)

        val amPmSimpleDateFormat = SimpleDateFormat("a")
        holder.tvConferenceAmPm.text =
            amPmSimpleDateFormat.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener() {
            scheduleItemListener.onConferenceClicked(conference, position)
        }

    }

    fun updateData(data: List<Conference>) {
        conferences.clear();
        conferences.addAll(data);
        notifyDataSetChanged()
    }

    class ViewHolder(conference: View) : RecyclerView.ViewHolder(conference) {
        val tvConferenceTitle = conference.findViewById<TextView>(R.id.tvConferenceTitle)
        val tvConferenceSpeaker = conference.findViewById<TextView>(R.id.tvConferenceSpeaker)
        val tvConferenceTopic = conference.findViewById<TextView>(R.id.tvConferenceTopic)
        val tvConferenceHour = conference.findViewById<TextView>(R.id.tvConferenceHour)
        val tvConferenceAmPm = conference.findViewById<TextView>(R.id.tvConferenceAmPm)
    }

}