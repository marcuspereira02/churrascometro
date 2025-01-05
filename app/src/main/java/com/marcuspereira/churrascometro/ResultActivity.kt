package com.marcuspereira.churrascometro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_ACTIVITY = "ResultActivity.Key"

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultadoQtdCarne : Float = intent.getFloatExtra(KEY_RESULT_ACTIVITY, 0f)
        val resultadoQtdCerveja : Float = intent.getFloatExtra("QTDCERVEJA", 0f)
        val resultadoQtdRefri : Float = intent.getFloatExtra("QTDREFRIGERANTE", 0f)
        val btnNovoCalculo = findViewById<Button>(R.id.btn_NovoCalculo)

        val qtdCarneFormatado = String.format("%.2f", resultadoQtdCarne)
        val qtdCervejaFormatado = String.format("%.2f", resultadoQtdCerveja)
        val qtdRefriFormatado = String.format("%.2f", resultadoQtdRefri)

        val tvQtdCarne = findViewById<TextView>(R.id.tv_QtdCarne)
        val tvQtdCerveja = findViewById<TextView>(R.id.tv_QtdCerveja)
        val tvQtdRefri = findViewById<TextView>(R.id.tv_QtdRefri)

        tvQtdCarne.text = "Quantidade de carne (KG): $qtdCarneFormatado"
        tvQtdCerveja.text = "Quantidade de cerveja (Litros): $qtdCervejaFormatado"
        tvQtdRefri.text = "Quantidade de refrigerante (Litros): $qtdRefriFormatado"

        btnNovoCalculo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(KEY_MAIN_ACTIVITY, "")
            startActivity(intent)
        }
    }
}