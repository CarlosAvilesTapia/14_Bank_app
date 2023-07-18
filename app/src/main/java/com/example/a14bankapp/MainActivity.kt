package com.example.a14bankapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a14bankapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var balance = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asignación de comportamiento al radio group.
        binding.bAction.setOnClickListener {
            when(binding.rgOptions.checkedRadioButtonId) {
                R.id.rbCheck -> checkBalance()
                R.id.rbDeposit -> depositAmount()
                R.id.rbWithdraw -> withdrawAmount()
                R.id.rbClose -> finish()
            }
        }
    }

    // Función para mostrar el saldo en el text view.
    private fun checkBalance() {
        binding.etAmount.setText(balance.toString())
    }

    // Función que suma la cantidad indicada al saldo.
    private fun depositAmount() {
        balance += binding.etAmount.text.toString().toInt()
        clearAmount()
        showToast("Depósito exitoso, su saldo ahora es $$balance.")
    }

    // Función que resta la cantidad indicada al saldo.
    private fun withdrawAmount() {
        val withdraw = binding.etAmount.text.toString().toInt()

        // Condición para validar que el saldo no sea negativo.
        if (balance >= withdraw) {
            balance -= withdraw
            clearAmount()
            showToast("Retiro exitoso, su saldo ahora es $$balance.")
        } else {
            showToast("Saldo insuficiente para realizar la operación.")
        }
    }

    // Función para borrado del texto en el text view.
    private fun clearAmount() {
        binding.etAmount.text.clear()
    }

    // Función que llama a un toast.
    private fun showToast(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_LONG).show()
    }
}
