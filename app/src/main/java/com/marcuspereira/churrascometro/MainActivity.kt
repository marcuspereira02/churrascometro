package com.marcuspereira.churrascometro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

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
            val qtdAdultosD : Double = qtdAdultos.toString().toDouble()
            val qtdCriancasD : Double = qtdCriancas.toString().toDouble()
            val duracaoD : Double = duracao.toString().toDouble()
            var qtdCarne: Double = 0.0
            var qtdRefrigerante: Double = 0.0
            var qtdCerveja: Double = 0.0

            if(duracaoD < 4) {
                qtdCarne = (qtdAdultosD * 0.4) + (qtdCriancasD * 0.2)
                qtdCerveja = qtdAdultosD * 1.5
                qtdRefrigerante = (qtdAdultosD * 0.5) + (qtdCriancasD * 0.4)
            } else {
                qtdCarne = ((qtdAdultosD * 0.4) + (qtdCriancasD * 0.2)) * 1.2
                qtdCerveja = (qtdAdultosD * 1.5) + 2
                qtdRefrigerante = ((qtdAdultosD * 0.5) + (qtdCriancasD * 0.4)) * 2
            }

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(KEY_RESULT_ACTIVITY, qtdCarne)
            intent.putExtra("QTDCERVEJA", qtdCerveja)
            intent.putExtra("QTDREFRIGERANTE", qtdRefrigerante)
            startActivity(intent)
        }
    }
}