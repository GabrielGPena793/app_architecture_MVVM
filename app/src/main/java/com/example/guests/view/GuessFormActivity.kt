package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guests.model.GuestModel
import com.example.guests.R
import com.example.guests.constants.DataBaseConstants
import com.example.guests.databinding.ActivityGuessFormBinding
import com.example.guests.viewModel.GuessFormViewModel

class GuessFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuessFormBinding
    private lateinit var viewModel: GuessFormViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuessFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuessFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
       if (v.id == R.id.button_save) {
           val name = binding.editName.text.toString()
           val presence = binding.radioPresent.isChecked
           val guest = GuestModel(guestId, name, presence)

           viewModel.save(guest)

           binding.editName.setText("")
           binding.radioPresent.isChecked = true
       }
    }

    private fun observe() {
        viewModel._guest.observe(this) {
            if (it != null) {
                binding.editName.setText(it.name)

                if (it.presence) {
                    binding.radioPresent.isChecked = true
                } else {
                    binding.radioAbsent.isChecked = true
                }
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)

            viewModel.getById(guestId)
        }
    }

}