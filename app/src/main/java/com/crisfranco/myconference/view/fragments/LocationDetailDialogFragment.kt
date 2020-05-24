package com.crisfranco.myconference.view.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.crisfranco.myconference.R
import com.crisfranco.myconference.model.Location
import kotlinx.android.synthetic.main.fragment_location_detail_dialog.*


class LocationDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarLocationDetail.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarLocationDetail.setTitleTextColor(Color.WHITE)
        toolbarLocationDetail.setNavigationOnClickListener {
            dismiss()
        }

        val location = Location()
        toolbarLocationDetail.title=location.name
        tvLocationName.text = location.name
        tvLocationAddress.text = location.address
        tvLocationPhone.text = location.phone
        tvLocationWebSite.text = location.website

        llLocationPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${location.phone}")
            }
            startActivity(intent)
        }

        llLocationWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(location.website)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

}
