package com.example.myapplicationcontroloscomunstpc4feito

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationcontroloscomunstpc4feito.MainActivity.Companion.PARAM_Resultado

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Ir ao itent e buscar o prâmetro que passamos de uma mensagem neste caso o nome
        val message = intent.getIntExtra(MainActivity.PARAM_Resultado,-1).toString()
        //Alterar o texto do textView para a mensagem que passamos o text
        val resultadoText = findViewById<TextView>(R.id.Activity2).text.toString()
        if (resultadoText.isNotEmpty()) {
            intent.putExtra(PARAM_Resultado, resultadoText.toInt())
        }
    }
}