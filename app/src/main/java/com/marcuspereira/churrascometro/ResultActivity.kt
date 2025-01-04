package com.marcuspereira.churrascometro

import android.os.Bundle
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

    }
}