package com.example.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guests.model.GuestModel
import com.example.guests.R
import com.example.guests.databinding.ActivityGuessFormBinding
import com.example.guests.viewModel.GuessFormViewModel

class GuessFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuessFormBinding
    private lateinit var viewModel: GuessFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuessFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuessFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true
    }

    override fun onClick(v: View) {
       if (v.id == R.id.button_save) {
           val name = binding.editName.text.toString()
           val presence = binding.radioPresent.isChecked
           val guest = GuestModel(0, name, presence)

           viewModel.insert(guest)

           binding.editName.setText("")
           binding.radioPresent.isChecked = true
       }
    }
}