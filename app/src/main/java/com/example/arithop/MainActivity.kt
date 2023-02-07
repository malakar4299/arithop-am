package com.example.arithop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.arithop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private val operationBank = listOf(
        "Addition",
        "Subtraction",
        "Multiplication",
        "Division",
        "Modulus",
    )

    var spinner: Spinner? = null

    private var currentSelection = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = binding.spinner
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, operationBank)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        binding.button.setOnClickListener {view:View ->
            run {
                calculateValue()
            }
        }
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        currentSelection = operationBank[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun calculateValue(){
        if( !isNumber(binding.firstNumber.text.toString()) || !isNumber(binding.secondNumber.text.toString())){
            binding.outputValue.setText("INV INP")
        }else{
            val firstNumber = binding.firstNumber.text.toString().toDouble();
            val secondNumber = binding.secondNumber.text.toString().toDouble();
            when(currentSelection){
                "Addition" -> {
                    binding.outputValue.setText((firstNumber + secondNumber).toString())
                }

                "Subtraction" -> {
                    binding.outputValue.setText((firstNumber - secondNumber).toString())
                }

                "Multiplication" -> {
                    binding.outputValue.setText((firstNumber * secondNumber).toString())
                }

                "Division" -> {
                    if(secondNumber==0.0){
                        binding.outputValue.setText("INV DENOM")
                    }else{
                        binding.outputValue.setText((firstNumber / secondNumber).toString())
                    }
                }

                "Modulus" -> {
                    if(secondNumber==0.0){
                        binding.outputValue.setText("INV DENOM")
                    }else{
                        binding.outputValue.setText((firstNumber % secondNumber).toString())
                    }
                }
            }
        }
    }

    private fun isNumber(s: String?): Boolean {
        return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
    }
}