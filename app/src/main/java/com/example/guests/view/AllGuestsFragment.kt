package com.example.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.viewModel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        // layout
        binding.recyclerGuestsList.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerGuestsList.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                Toast.makeText(context, "clicou no item $id", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(id: Int) {
                TODO("Not yet implemented")
            }

        }

        adapter.attachGuestListener(listener)

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}