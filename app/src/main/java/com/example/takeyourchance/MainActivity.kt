package com.example.takeyourchance

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var palpite: EditText
    private lateinit var buttonTentar: Button
    private lateinit var status: TextView
    private lateinit var lower: TextView
    private lateinit var higher: TextView

    private var numAleatorio: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        palpite = findViewById(R.id.palpite)
        buttonTentar = findViewById(R.id.button_tentar)
        status = findViewById(R.id.status)
        lower = findViewById(R.id.lower)
        higher = findViewById(R.id.higher)

        numAleatorio = gerarNumero()

        buttonTentar.setOnClickListener() {
            onTentarClick(it)
        }

        status.setOnLongClickListener() {
            numAleatorio = gerarNumero()
            status.text = "TUDO PRONTO!"
            lower.text = "1"
            higher.text = "100"
            buttonTentar.isEnabled = true
            exibirToast("Jogo reiniciado.")
            true
        }
    }

    fun gerarNumero(): Int {
        return Random.nextInt(1, 101)
    }

    fun exibirToast(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }


    fun onTentarClick(view: View) {
        val palpiteTexto = palpite.text.toString()

        if (palpiteTexto.isNotEmpty()) {
            val palpite = palpiteTexto.toIntOrNull()

            if (palpite != null && palpite in 1..100) {
                if (palpite == numAleatorio) {
                    status.text = "Parabéns! Você acertou! :D"
                    buttonTentar.isEnabled = false
                } else {
                    status.text = "Errou, mas vai tentando aí..."
                    exibirToast("Seu intervalo de chute foi alterado.")
                    if (numAleatorio > palpite) {
                        lower.text = (palpite + 1).toString()
                    } else {
                        higher.text = (palpite - 1).toString()
                    }
                }
                if (lower.text.toString() == higher.text.toString()) {
                    status.text = "Você perdeu! :("
                    exibirToast("Fim de jogo. Os limites se encontraram.")
                    buttonTentar.isEnabled = false
                }

            } else {
                exibirToast("Pera aí, meu bichin... bote um número válido, né.")
            }
        } else {
            exibirToast("Parabéns, já começou MAL. Digite um palpite antes.")
        }
    }
}