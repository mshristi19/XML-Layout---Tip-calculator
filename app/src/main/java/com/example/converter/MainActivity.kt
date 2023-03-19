package com.example.converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton: Button = findViewById(R.id.calculateButton)
        val unitEdit: EditText = findViewById(R.id.unitEditText)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val reverseSwitch: Switch = findViewById(R.id.switchButton)
        val unitResult: TextView = findViewById(R.id.unitResultText)

        calculateButton.setOnClickListener(View.OnClickListener {
            var changed = 0f
            var rButton: Int = radioGroup.checkedRadioButtonId
            try{
                if(rButton != -1) {
                    val radioButton: RadioButton = findViewById<RadioButton>(rButton)
                    val rButtonValue: String = radioButton.text.toString()
                    if(rButtonValue == "Grams to cups" && !reverseSwitch.isChecked()) {
                        changed = unitEdit.text.toString().toFloat() / 240
                    } else if (rButtonValue == "Grams to cups" && reverseSwitch.isChecked()) {
                        changed = unitEdit.text.toString().toFloat() * 240
                    } else if (rButtonValue == " Mililiters to fluid ounces" && !reverseSwitch.isChecked()) {
                        changed = unitEdit.text.toString().toFloat() * 0.33814f
                    } else if (rButtonValue == " Mililiters to fluid ounces" && reverseSwitch.isChecked()) {
                        changed = unitEdit.text.toString().toFloat() / 0.033814f
                    }
                }
                unitResult.text = "unit_result: $changed"
            } catch (e:NumberFormatException) {
                Log.d("ERROR","Converted number is incorrect")
            }
        })

    }
}