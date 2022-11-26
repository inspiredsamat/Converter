package kz.inspiredsamat.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.inspiredsamat.converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { convertValue() }
    }

    private fun convertValue() {
        val stringInTextField = binding.inputValue.text.toString()
        val value = stringInTextField.toDoubleOrNull()
        if (value == null || value == 0.0) {
            displayResult(0.0)
            return
        }
        val unitMultiplier = when (binding.converterOptions.checkedRadioButtonId) {
            R.id.option_km_to_m -> 1000.0
            R.id.option_minute_to_second -> 60.0
            else -> 0.453
        }
        var result = unitMultiplier * value
        if (binding.roundUpSwitch.isChecked) {
            result = kotlin.math.ceil(result)
        }
        displayResult(result)
    }

    private fun displayResult(value: Double) {
        binding.result.text = getString(R.string.result, value.toString())
    }
}