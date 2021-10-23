package com.gideondev.digicard

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.gideondev.digicard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnPay.setOnClickListener {
            confirmInput(it)
        }
        binding.edtCardNumber.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.length == 4){
                    binding.txtFirstFourDigit.text = s.substring(0, 4);
                }

                if(s.length == 8){
                    binding.txtSecondFourDigit.text = s.substring(4, 8);
                }

                if(s.length == 12){
                    binding.txtThirdFourDigit.text = s.substring(8, 12);
                }

                if(s.length == 16){
                    binding.txtFrFourDigit.text = s.substring(12, 16);
                }
            }
        })
    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            "Yes", Toast.LENGTH_SHORT).show()
    }

    fun successAlert(view: View) {

        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setTitle("Success")
            setMessage("Your payment was successful")
            setPositiveButton("OK", DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }

    }

    private fun validateCardNumber(): Boolean {
        val cardNumberInput: String = binding.edtCardNumber.editableText.toString().trim()
        return when {
            cardNumberInput.isEmpty() -> {
                binding.edtCardNumber.error = "Field can't be empty"
                false
            }
            cardNumberInput.length > 16 -> {
                binding.edtCardNumber.error = "Card number too long"
                false
            }

            cardNumberInput.length < 16 -> {
                binding.edtCardNumber.error = "Card number too short"
                false
            }
            else -> {
                binding.edtCardNumber.error = null
                true
            }
        }
    }

    private fun validateCardName(): Boolean {
        val cardNumberInput: String = binding.edtCardName.editableText.toString().trim()
        return when {
            cardNumberInput.isEmpty() -> {
                binding.edtCardName.error = "Field name can't be empty"
                false
            }
            else -> {
                binding.edtCardName.error = null
                true
            }
        }
    }

    private fun validateCardCvvNumber(): Boolean {
        val cardCvvInput: String = binding.edtCardCvv.editableText.toString().trim()
        return when {
            cardCvvInput.isEmpty() -> {
                binding.edtCardCvv.error = "Field can't be empty"
                false
            }
            cardCvvInput.length > 3 -> {
                binding.edtCardCvv.error = "Card Cvv number too long"
                false
            }

            cardCvvInput.length < 3 -> {
                binding.edtCardCvv.error = "Card cvv number too short"
                false
            }
            else -> {
                binding.edtCardCvv.error = null
                true
            }
        }
    }

    private fun validateCardYear(): Boolean {
        val cardYearInput: String = binding.edtCardYear.editableText.toString().trim()
        return when {
            cardYearInput.isEmpty() -> {
                binding.edtCardYear.error = "Field can't be empty"
                false
            }
            cardYearInput.length > 2 -> {
                binding.edtCardYear.error = "Card Year not valid"
                false
            }

            cardYearInput.length < 2 -> {
                binding.edtCardYear.error = "Card cvv not valid"
                false
            }
            else -> {
                binding.edtCardCvv.error = null
                true
            }
        }
    }

    private fun validateCardMonth(): Boolean {
        val cardMonthInput: String = binding.edtCardMonth.editableText.toString().trim()
        return when {
            cardMonthInput.isEmpty() -> {
                binding.edtCardMonth.error = "Field can't be empty"
                false
            }
            cardMonthInput.length > 2 -> {
                binding.edtCardMonth.error = "Card Year not valid"
                false
            }

            cardMonthInput.length < 2 -> {
                binding.edtCardMonth.error = "Card cvv not valid"
                false
            }
            cardMonthInput.toInt() !in 1..12 -> {
                binding.edtCardMonth.error = "Card month not valid month"
                false
            }
            else -> {
                binding.edtCardMonth.error = null
                true
            }
        }
    }

    fun confirmInput(v: View){
        if(!validateCardNumber() || !validateCardName() || !validateCardCvvNumber()|| !validateCardMonth()  || !validateCardYear()  ){
            return
        }

        successAlert(v)

    }

}