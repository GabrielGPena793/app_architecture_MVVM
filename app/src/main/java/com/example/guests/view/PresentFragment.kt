package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.FragmentPresentBinding
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.OnGuestListener
import com.example.guests.viewModel.GuestsViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestAdapter()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,b: Bundle?): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        // layout
        binding.recyclerGuestsList.layoutManager = LinearLayoutManager(context)

        // adapter
        binding.recyclerGuestsList.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuessFormActivity::class.java)

                val bundle = Bundle()   // crio um bundle para passar informações a mais pra próxima tela
                bundle.putInt(DataBaseConstants.GUEST.ID, id) // posso mandar quantos itens quiser
                intent.putExtras(bundle)  // adicino no intent
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresent()
            }
        }

        adapter.attachGuestListener(listener)

        viewModel.getPresent()

        observe()
        return binding.root
    }

    override fun onResume() {
        viewModel.getPresent()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}