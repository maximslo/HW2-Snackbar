package com.example.hw2_snackbar

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var operandOneEditText: EditText
    private lateinit var operandTwoEditText: EditText
    private lateinit var operationSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views using findViewById
        operandOneEditText = findViewById(R.id.operandOneEditText)
        operandTwoEditText = findViewById(R.id.operandTwoEditText)
        operationSpinner = findViewById(R.id.operationSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        // Setup the spinner with the arithmetic operations
        val operations = arrayOf("Addition", "Subtraction", "Multiplication", "Division", "Modulus")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, operations)
        operationSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            performCalculation()
        }
    }

    private fun performCalculation() {
        val operandOne = operandOneEditText.text.toString().toDoubleOrNull()
        val operandTwo = operandTwoEditText.text.toString().toDoubleOrNull()

        if (operandOne == null || operandTwo == null) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        val operation = operationSpinner.selectedItem.toString()
        val result = when (operation) {
            "Addition" -> operandOne + operandTwo
            "Subtraction" -> operandOne - operandTwo
            "Multiplication" -> operandOne * operandTwo
            "Division" -> if (operandTwo != 0.0) operandOne / operandTwo else null
            "Modulus" -> if (operandTwo != 0.0) operandOne % operandTwo else null
            else -> null
        }

        // Set the result text or an error message
        resultTextView.text = if (result == null) {
            when {
                operation == "Division" && operandTwo == 0.0 -> "Divide by Zero not allowed."
                operation == "Modulus" && operandTwo == 0.0 -> "Modulus by Zero not allowed."
                else -> "Error in calculation"
            }
        } else {
            "Result: $result"
        }
    }
}
