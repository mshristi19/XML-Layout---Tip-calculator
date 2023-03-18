package com.example.xmllayout_tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xmllayout_tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val costEditText: TextView = findViewById(R.id.costEditText);
        val calculateButton: Button = findViewById(R.id.calculateButton);
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup);
        val roundupSwitch: Switch = findViewById(R.id.switchButton);
        val tipAmountText: TextView = findViewById(R.id.tipAmountTextView);

        binding.calculateButton.setOnClickListener{
            tipCalculate();
        }
    }

    private fun tipCalculate() {
        val stringInTextField = binding.costEditText.text.toString();
        val cost = stringInTextField.toDouble();
        var selectedId = binding.radioGroup.checkedRadioButtonId;
        val tipPercentage = when(selectedId) {
            R.id.Amazing -> 0.20;
            R.id.Good -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost;
        val roundUp = binding.switchButton.isChecked;
        if(roundUp) {
            tip = kotlin.math.ceil(tip);
        }

        val formatTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipAmountTextView.text = getString(R.string.tip_amount, formattedTip);

    }
}
