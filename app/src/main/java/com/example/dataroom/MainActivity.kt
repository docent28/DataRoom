package com.example.dataroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.dataroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDB.getDB(this)

        db.getDao().getAllItem().asLiveData().observe(this) { list ->
            binding.edMessage.text = ""
            list.forEach {
                val text = "Id: ${it.id} Name: ${it.name} Price: ${it.price}\n"
                binding.edMessage.append(text)
            }
        }

        binding.btnSave.setOnClickListener {
            val item = Item(
                null,
                binding.edName.text.toString(),
                binding.edPrice.text.toString(),
            )

            Thread {
                db.getDao().insertItem(item)
            }.start()
        }
    }
}