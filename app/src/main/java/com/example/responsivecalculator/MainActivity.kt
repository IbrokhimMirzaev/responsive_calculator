package com.example.responsivecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button

    private lateinit var point: Button
    private lateinit var clear: Button
    private lateinit var delete: Button

    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var plus: Button
    private lateinit var minus: Button

    private lateinit var oper: TextView
    private lateinit var result: TextView

    private var isPoint = true
    private var isSymbol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)

        point.setOnClickListener {
            if (isPoint && oper.text[oper.text.length - 1].isDigit()) {
                oper.text = oper.text.toString() + "."
                isPoint = false
                isSymbol = false
            }
        }

        clear.setOnClickListener { clear() }

        delete.setOnClickListener {
            if (oper.text.length == 1){
                clear()
            }
            else {
                if (oper.text[oper.text.length - 1] == '.'){
                    isPoint = true
                }
                oper.text = oper.text.substring(0, oper.text.length - 1)
            }
        }

        div.setOnClickListener { addSymbol(div.text.toString()) }
        multiply.setOnClickListener { addSymbol(multiply.text.toString()) }
        plus.setOnClickListener { addSymbol(plus.text.toString()) }
        minus.setOnClickListener { addSymbol(minus.text.toString()) }

    }

    private fun initUI() {
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        oper = findViewById(R.id.text1)
        result = findViewById(R.id.text2)

        clear = findViewById(R.id.clear)
        point = findViewById(R.id.point)
        delete = findViewById(R.id.delete)

        div = findViewById(R.id.division)
        multiply = findViewById(R.id.multiply)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
    }

    override fun onClick(p0: View?) {
        val btn = findViewById<Button>(p0!!.id)
        if (oper.text != "0") {
            oper.text = oper.text.toString() + btn.text
        } else {
            oper.text = btn.text
        }

        result.text = calculate()
        isSymbol = true
    }

    private fun calculate(): String {
        var r = "123"
        createArray(oper.text.toString())
        return r
    }

    private fun clear() {
        oper.text = "0"
        isPoint = true
        isSymbol = false
    }

    private fun addSymbol(symbol: String) {
        if (isSymbol && oper.text[oper.text.length - 1] != '.') {
            oper.text = oper.text.toString() + symbol
            isSymbol = false
        } else {
            if (oper.text != "0" && oper.text[oper.text.length - 1] != '.') {
                oper.text = oper.text.substring(0, oper.text.length - 1) + symbol
            }
        }
        isPoint = true
    }

    private fun createArray(s: String): MutableList<Any> {
        var list = mutableListOf<Any>()

        var temp = ""
        for (i in s) {
            if (i.isDigit() || i == '.') {
                temp += i
            } else {
                list.add(temp)
                list.add(i)
                temp = ""
            }
        }
        if (temp.isNotEmpty()) {
            list.add(temp)
        }
        return list
    }


}