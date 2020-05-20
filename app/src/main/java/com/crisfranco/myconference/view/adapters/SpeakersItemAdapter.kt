package com.crisfranco.myconference.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Speaker

class SpeakersItemAdapter(val speakersItemListener: SpeakersItemListener) :
    RecyclerView.Adapter<SpeakersItemAdapter.ViewHolder>() {

    var speakers = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.speakers_item, parent, false
        )
    )

    override fun getItemCount() = speakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = speakers[position]

        Glide.with(holder.ivSpeakersItemImage.context).load(speaker.image)
            .apply(RequestOptions.circleCropTransform()).into(holder.ivSpeakersItemImage)

        holder.tvSpeakersItemFullName.text = speaker.name
        holder.tvSpeakersItemJobTitle.text = speaker.image

        holder.itemView.setOnClickListener() {
            speakersItemListener.onSpeakerClicked(speaker, position)
        }

    }


    fun updateData(data: List<Speaker>) {
        speakers.clear();
        speakers.addAll(data);
        notifyDataSetChanged()
    }

    class ViewHolder(speaker: View) : RecyclerView.ViewHolder(speaker) {
        val ivSpeakersItemImage = speaker.findViewById<ImageView>(R.id.ivSpeakersItemImage)
        val tvSpeakersItemFullName = speaker.findViewById<TextView>(R.id.tvSpeakersItemFullName)
        val tvSpeakersItemJobTitle = speaker.findViewById<TextView>(R.id.tvSpeakersItemJobTitle)
    }
}