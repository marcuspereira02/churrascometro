package com.marcuspereira.churrascometro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

const val KEY_MAIN_ACTIVITY = "MainActivity.Key"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val qtdAdultos = findViewById<TextInputEditText>(R.id.ti_QtdAdultos)
        val qtdCriancas = findViewById<TextInputEditText>(R.id.ti_QtdCriancas)
        val duracao = findViewById<TextInputEditText>(R.id.ti_Duracao)
        val btnCalcular = findViewById<Button>(R.id.btn_Calcular)

        btnCalcular.setOnClickListener {
            val qtdAdultosText = qtdAdultos.text?.toString()
            val qtdCriancasText = qtdCriancas.text?.toString()
            val duracaoText = duracao.text?.toString()

            if (qtdAdultosText.isNullOrEmpty()) {
                qtdAdultos.error = "Preencha este campo"
                return@setOnClickListener
            }

            if (qtdCriancasText.isNullOrEmpty()) {
                qtdCriancas.error = "Preencha este campo"
                return@setOnClickListener
            }

            if (duracaoText.isNullOrEmpty()) {
                duracao.error = "Preencha este campo"
                return@setOnClickListener
            }

            try {
                val qtdAdultosD = qtdAdultosText.toFloat()
                val qtdCriancasD = qtdCriancasText.toFloat()
                val duracaoD = duracaoText.toFloat()

                var qtdCarne: Float = 0.0F
                var qtdRefrigerante: Float = 0.0F
                var qtdCerveja: Float = 0.0F

                if (duracaoD < 4) {
                    qtdCarne = (qtdAdultosD * 0.4F) + (qtdCriancasD * 0.2F)
                    qtdCerveja = qtdAdultosD * 1.5F
                    qtdRefrigerante = (qtdAdultosD * 0.5F) + (qtdCriancasD * 0.4F)
                } else {
                    qtdCarne = ((qtdAdultosD * 0.4F) + (qtdCriancasD * 0.2F)) * 1.2F
                    qtdCerveja = qtdAdultosD * 2F
                    qtdRefrigerante = (qtdAdultosD * 0.5F) + (qtdCriancasD * 0.4F)
                }


                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(KEY_RESULT_ACTIVITY, qtdCarne)
                intent.putExtra("QTDCERVEJA", qtdCerveja)
                intent.putExtra("QTDREFRIGERANTE", qtdRefrigerante)
                startActivity(intent)

            } catch (e: Exception) {
                Log.e("Erro", "Erro ao calcular: ${e.message}")
            }
        }
    }
}