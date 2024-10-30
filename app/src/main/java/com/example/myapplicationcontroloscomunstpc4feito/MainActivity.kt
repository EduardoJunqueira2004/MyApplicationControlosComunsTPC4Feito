package com.example.myapplicationcontroloscomunstpc4feito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

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
        val editTextYear = findViewById<EditText>(R.id.AnoNascimento)
        val editTextMonth = findViewById<EditText>(R.id.Mes)
        val editTextDay = findViewById<EditText>(R.id.Dia)
        val buttonCalculate = findViewById<Button>(R.id.Validar)
        val buttonOpenSecondActivity = findViewById<Button>(R.id.SecondActivity)
        val textViewResult = findViewById<TextView>(R.id.Resultado)

        buttonCalculate.setOnClickListener {
            val yearText = editTextYear.text.toString()
            val monthText = editTextMonth.text.toString()
            val dayText = editTextDay.text.toString()

            if (yearText.isNotEmpty() && monthText.isNotEmpty() && dayText.isNotEmpty()) {
                val year = yearText.toInt()
                val month = monthText.toInt()
                val day = dayText.toInt()

                val result = "Data: $day/$month/$year"
                textViewResult.text = result
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar o botão para abrir a SecondActivity
        buttonOpenSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    fun Validacao(view: View)
    {

            // ir buscar o valor de cada uma das edittext
            val input_ano = findViewById<EditText>(R.id.AnoNascimento).text.toString().toInt()
            val input_mes = findViewById<EditText>(R.id.Mes).text.toString().toInt()
            val input_dia = findViewById<EditText>(R.id.Dia).text.toString().toInt()

            if (input_ano < 1900 || input_ano > 2024) {
                Toast.makeText(
                    this,
                    R.string.msg1,
                    Toast.LENGTH_SHORT
                ).show()

                findViewById<Button>(R.id.Validar).text = getText(R.string.botao_erro)

            } else {
                val c = Calendar.getInstance()
                val cur_year = c.get(Calendar.YEAR)
                // é preciso somar um mês pois começa em 0
                c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1)
                var idade = 0

                val c_input = Calendar.getInstance()
                c_input.set(cur_year, input_mes, input_dia)
                if (c_input.before(c)) {
                    idade = cur_year - input_ano - 1
                } else {
                    idade = cur_year - input_ano
                }

                findViewById<Button>(R.id.Validar).text = getText(R.string.botao_ok)

                findViewById<TextView>(R.id.Resultado).text = idade.toString()
            }
        }

    fun SecondActivity(view: View)
    {
        val intent = Intent(applicationContext, SecondActivity::class.java)
        intent.putExtra(PARAM_Resultado, findViewById<EditText>(R.id.Resultado).text.toString().toInt())
        startActivity(intent)
    }

    companion object {
        const val PARAM_Resultado = "PARAM_Resultado"
    }


}
