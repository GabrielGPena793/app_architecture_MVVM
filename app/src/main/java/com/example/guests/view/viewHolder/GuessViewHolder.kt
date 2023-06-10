package com.example.guests.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel

class GuessViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name
    }
}