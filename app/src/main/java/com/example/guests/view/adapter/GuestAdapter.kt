package com.example.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.model.GuestModel
import com.example.guests.view.viewHolder.GuessViewHolder

class GuestAdapter : RecyclerView.Adapter<GuessViewHolder>(){

    private var questList = listOf<GuestModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuessViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuessViewHolder(item)
    }

    override fun getItemCount(): Int {
        return questList.count();
    }

    override fun onBindViewHolder(holder: GuessViewHolder, position: Int) {
        holder.bind(questList[position])
    }

    fun updateGuests(list: List<GuestModel>) {
        questList = list
        notifyDataSetChanged() // notifica que houve alterações
    }
}