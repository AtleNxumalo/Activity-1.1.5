package vcmsa.projects.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var operator: String? = null
    private var firstOperand: Double = 0.0
    private var secondOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Set OnClickListeners for all number buttons
        val numberButtons = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        )

        for (buttonId in numberButtons) {
            findViewById<Button>(buttonId).setOnClickListener { view ->
                onNumberClick(view)
            }
        }

        // Set OnClickListeners for the operator buttons
        findViewById<Button>(R.id.buttonAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { onOperatorClick("*") }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { onOperatorClick("/") }

        // Set OnClickListener for the equals button
        findViewById<Button>(R.id.buttonEquals).setOnClickListener { onEqualsClick() }

        // Set OnClickListener for the clear button
        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClearClick() }
    }

    // Handle number button click
    private fun onNumberClick(view: View) {
        val button = view as Button
        currentInput += button.text.toString()
        resultTextView.text = currentInput
    }

    // Handle operator button click
    private fun onOperatorClick(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    // Handle equals button click
    private fun onEqualsClick() {
        if (currentInput.isNotEmpty() && operator != null) {
            secondOperand = currentInput.toDouble()

            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> {
                    if (secondOperand == 0.0) {
                        "Error"
                    } else {
                        firstOperand / secondOperand
                    }
                }
                else -> 0.0
            }

            resultTextView.text = result.toString()
            currentInput = result.toString()
            operator = null
        }
    }

    // Handle clear button click
    private fun onClearClick() {
        currentInput = ""
        operator = null
        resultTextView.text = "0"
    }
