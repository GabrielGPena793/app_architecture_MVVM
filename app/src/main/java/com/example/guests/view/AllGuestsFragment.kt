package com.example.guests.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.viewModel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        // layout
        binding.recyclerGuestsList.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerGuestsList.adapter = GuestAdapter()

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
        }
    }
}