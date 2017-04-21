package com.dhimandasgupta.kotlincal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity(), TextWatcher {
    private val LAST_RESULT_TAG = "last_result_tag"

    private lateinit var mFirstNumberEditText : AppCompatEditText
    private lateinit var mSecondNumberEditText : AppCompatEditText

    private lateinit var mAddButton : AppCompatButton
    private lateinit var mSubButton : AppCompatButton
    private lateinit var mMulButton : AppCompatButton
    private lateinit var mDivButton : AppCompatButton

    private lateinit var mResultTextView : AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(LAST_RESULT_TAG, mResultTextView.text.toString().trim())
        super.onSaveInstanceState(outState)
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val bothNumbersExist = mFirstNumberEditText.text.toString().trim().length > 0 && mSecondNumberEditText.text.toString().trim().length > 0

        mAddButton.isEnabled = bothNumbersExist
        mSubButton.isEnabled = bothNumbersExist
        mMulButton.isEnabled = bothNumbersExist
        mDivButton.isEnabled = bothNumbersExist
    }

    private fun initViews(savedInstanceState: Bundle?) : Unit {
        mFirstNumberEditText = findViewById(R.id.first_number_edit_text) as AppCompatEditText
        mFirstNumberEditText.addTextChangedListener(this)
        mSecondNumberEditText = findViewById(R.id.second_number_edit_text) as AppCompatEditText
        mSecondNumberEditText.addTextChangedListener(this)

        mAddButton = findViewById(R.id.add_button) as AppCompatButton
        mAddButton.setOnClickListener {
            val firstNumber : Double = mFirstNumberEditText.text.toString().trim().toDouble()
            val secondNumber : Double = mSecondNumberEditText.text.toString().trim().toDouble()

            val result = operation({ firstNumber.plus(secondNumber) })
            mResultTextView.text = result.toString()
        }
        mSubButton = findViewById(R.id.sub_button) as AppCompatButton
        mSubButton.setOnClickListener {
            val firstNumber : Double = mFirstNumberEditText.text.toString().trim().toDouble()
            val secondNumber : Double = mSecondNumberEditText.text.toString().trim().toDouble()

            val result = operation { firstNumber.minus(secondNumber) }
            mResultTextView.text = result.toString()
        }
        mMulButton = findViewById(R.id.mul_button) as AppCompatButton
        mMulButton.setOnClickListener {
            val firstNumber : Double = mFirstNumberEditText.text.toString().trim().toDouble()
            val secondNumber : Double = mSecondNumberEditText.text.toString().trim().toDouble()

            val result = operation { firstNumber.times(secondNumber) }
            mResultTextView.text = result.toString()
        }
        mDivButton = findViewById(R.id.div_button) as AppCompatButton
        mDivButton.setOnClickListener {
            val firstNumber : Double = mFirstNumberEditText.text.toString().trim().toDouble()
            val secondNumber : Double = mSecondNumberEditText.text.toString().trim().toDouble()

            val result = operation { firstNumber.div(secondNumber) }
            mResultTextView.text = result.toString()
        }

        mResultTextView = findViewById(R.id.result_text_view) as AppCompatTextView
        mResultTextView.text = savedInstanceState?.getString(LAST_RESULT_TAG) ?: ""
    }

    /**
     * plus, minus, times, div are passed as lambdas
     * */
    private fun operation(function: () -> Double): Double {
        return function()
    }
}
