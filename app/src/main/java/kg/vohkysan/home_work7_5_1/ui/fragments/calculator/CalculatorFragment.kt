package kg.vohkysan.home_work7_5_1.ui.fragments.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kg.vohkysan.home_work7_5_1.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    private var curNumber = "0"
    private var selectOperat = ""
    private val listOfNumberBut = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnsData()
        clickNumber(listOfNumberBut)
    }

    private fun btnsData() {
        with(binding) {
            btnClear.setOnClickListener {
                tvResult.text = "0"
                curNumber = "0"
                selectOperat = ""
            }
            btnPlus.setOnClickListener {
                performOperation("+")
            }
            btnMinus.setOnClickListener {
                performOperation("-")
            }
            btnMulti.setOnClickListener {
                performOperation("*")
            }
            btnDivision.setOnClickListener {
                performOperation("/")
            }
            btnPercent.setOnClickListener {
                performPercent()
            }
            btnEquals.setOnClickListener {
                calculateResult()
            }
        }
    }

    private fun performOperation(operation: String) {
        if (curNumber != "0") {
            selectOperat = operation
            binding.tvResult.text = "0"
        }
    }

    private fun performPercent() {
        val result = curNumber.toDouble() / 100
        curNumber = result.toString()
        binding.tvResult.text = curNumber
    }

    private fun calculateResult() {
        if (selectOperat.isNotEmpty() && curNumber != "0") {
            val secondNumber = binding.tvResult.text.toString().toDouble()
            when (selectOperat) {
                "+" -> curNumber = (curNumber.toDouble() + secondNumber).toString()
                "-" -> curNumber = (curNumber.toDouble() - secondNumber).toString()
                "*" -> curNumber = (curNumber.toDouble() * secondNumber).toString()
                "/" -> curNumber = (curNumber.toDouble() / secondNumber).toString()
            }
            binding.tvResult.text = curNumber
            selectOperat = ""
        }
    }

    private fun clickNumber(numbers: List<String>) {
        with(binding) {
            numbers.forEach { number ->
                val button = when (number) {
                    "0" -> btn0
                    "1" -> btn1
                    "2" -> btn2
                    "3" -> btn3
                    "4" -> btn4
                    "5" -> btn5
                    "6" -> btn6
                    "7" -> btn7
                    "8" -> btn8
                    "9" -> btn9
                    else -> null
                }

                button?.setOnClickListener {
                    if (tvResult.text.toString() == "0") {
                        tvResult.text = number
                        curNumber = number
                    } else {
                        tvResult.append(number)
                        curNumber += number
                    }
                }
            }
        }
    }
}

