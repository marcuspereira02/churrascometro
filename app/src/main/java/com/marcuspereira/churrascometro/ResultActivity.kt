package com.marcuspereira.churrascometro

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.marcuspereira.churrascometro.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultMeatAmount: Float = intent.getFloatExtra("amountMeat", 0.0f)
        val resultBeerAmount: Float = intent.getFloatExtra("amountBeer", 0.0f)
        val resultRefrigerantAmount: Float = intent.getFloatExtra("amountRefrigerant", 0.0f)

        val amountMeat = numFormat(resultMeatAmount)
        val amountBeer = numFormat(resultBeerAmount)
        val amountRefrigerant = numFormat(resultRefrigerantAmount)

        binding.tvAmountMeat.text = "Quantidade de carne (KG): $amountMeat"
        binding.tvAmountBeer.text = "Quantidade de cerveja (Litros): $amountBeer"
        binding.tvAmountRefrigerant.text = "Quantidade de refrigerante (Litros): $amountRefrigerant"

        binding.btnRefresh.setOnClickListener {
            finish()
        }
    }

    private fun numFormat(num: Float): String {
        val formatNum = String.format("%.2f", num)
        return formatNum
    }
}