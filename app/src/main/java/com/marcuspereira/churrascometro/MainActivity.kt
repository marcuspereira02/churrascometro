package com.marcuspereira.churrascometro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcuspereira.churrascometro.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {

            if (binding.tieNumAdults.text.isNullOrEmpty()) {
                binding.tieNumAdults.error = "Preencha este campo"
                return@setOnClickListener
            }

            if (binding.tieNumKid.text.isNullOrEmpty()) {
                binding.tieNumKid.error = "Preencha este campo"
                return@setOnClickListener
            }

            if (binding.tieDuration.text.isNullOrEmpty()) {
                binding.tieDuration.error = "Preencha este campo"
                return@setOnClickListener
            }

            try {
                val numAdults = binding.tieNumAdults.text.toString().toFloat()
                val numKids = binding.tieNumKid.text.toString().toFloat()
                val duration = binding.tieDuration.text.toString().toFloat()

                var amountMeat: Float = 0.0F
                var amountRefrigerant: Float = 0.0F
                var amountBeer: Float = 0.0F

                if (duration < 4) {
                    amountMeat = (numAdults * 0.4F) + (numKids * 0.2F)
                    amountBeer = numAdults * 1.5F
                    amountRefrigerant = (numAdults * 0.5F) + (numKids * 0.4F)
                } else {
                    amountMeat = ((numAdults * 0.4F) + (numKids * 0.2F)) * 1.2F
                    amountBeer = numAdults * 2F
                    amountRefrigerant = (numAdults * 0.5F) + (numKids * 0.4F)
                }

                val intent = Intent(this, ResultActivity::class.java)
                intent.apply{
                    intent.putExtra("amountMeat", amountMeat)
                    intent.putExtra("amountBeer", amountBeer)
                    intent.putExtra("amountRefrigerant", amountRefrigerant)
                }
                clean()
                startActivity(intent)

            } catch (e: Exception) {
                Log.e("Erro", "Erro ao calcular: ${e.message}")
            }

            binding.btnClean.setOnClickListener {
                clean()
            }
        }
    }

    private fun clean(){
        binding.tieDuration.setText("")
        binding.tieNumKid.setText("")
        binding.tieNumAdults.setText("")
    }
}