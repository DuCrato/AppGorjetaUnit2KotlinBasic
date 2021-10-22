package com.example.gorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gorjeta.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcularButton.setOnClickListener{calcularGorjeta()}
    }

    private fun calcularGorjeta(){
        val campoServico = binding.custoDeServico.text.toString()
        val valorServico = campoServico.toDoubleOrNull()
        val porcentagemCaixa = when(binding.caixaDeOpcoes.checkedRadioButtonId){
            R.id.opcao_vinte_porcento   -> 0.20
            R.id.opcao_dezoito_porcento -> 0.18
            else                        -> 0.15
        }
        if(valorServico == null){
            binding.resuldadoGorjeta.text = ""
            return
        }
        var valorFinal = porcentagemCaixa * valorServico!!

        if(binding.adicionarGorjeta.isChecked){ valorFinal = kotlin.math.ceil(valorFinal)}

        NumberFormat.getCurrencyInstance()

        val valorFormatado = NumberFormat.getCurrencyInstance().format(valorFinal)
        binding.resuldadoGorjeta.text = getString(R.string.valor_da_gorjeta, valorFormatado)

    }
}