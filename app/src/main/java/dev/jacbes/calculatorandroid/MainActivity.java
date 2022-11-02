package dev.jacbes.calculatorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.function.DoubleBinaryOperator;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    TextView resultTextView;
    String firstNumber = "";
    String secondNumber = "";
    String resultNumber = "";
    DoubleBinaryOperator operation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.numbers_textview);

        Button oneButton = findViewById(R.id.one_button);
        oneButton.setOnClickListener(view -> addToSecondNumber("1"));
        Button twoButton = findViewById(R.id.two_button);
        twoButton.setOnClickListener(view -> addToSecondNumber("2"));
        Button threeButton = findViewById(R.id.three_button);
        threeButton.setOnClickListener(view -> addToSecondNumber("3"));
        Button fourButton = findViewById(R.id.four_button);
        fourButton.setOnClickListener(view -> addToSecondNumber("4"));
        Button fiveButton = findViewById(R.id.five_button);
        fiveButton.setOnClickListener(view -> addToSecondNumber("5"));
        Button sixButton = findViewById(R.id.six_button);
        sixButton.setOnClickListener(view -> addToSecondNumber("6"));
        Button sevenButton = findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(view -> addToSecondNumber("7"));
        Button eightButton = findViewById(R.id.eight_button);
        eightButton.setOnClickListener(view -> addToSecondNumber("8"));
        Button nineButton = findViewById(R.id.nine_button);
        nineButton.setOnClickListener(view -> addToSecondNumber("9"));
        Button zeroButton = findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(view -> addToSecondNumber("0"));

        Button sumButton = findViewById(R.id.sum_button);
        sumButton.setOnClickListener(view -> setOperation((x, y) -> x + y));
        Button subButton = findViewById(R.id.sub_button);
        subButton.setOnClickListener(view -> setOperation((x, y) -> x - y));
        Button divideButton = findViewById(R.id.divide_button);
        divideButton.setOnClickListener(view -> setOperation((x, y) -> x / y));
        Button multiplyButton = findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(view -> setOperation((x, y) -> x * y));
        Button resultButton = findViewById(R.id.result_button);
        resultButton.setOnClickListener(view -> launchOperation());

        Button dotButton = findViewById(R.id.dot_button);
        dotButton.setOnClickListener(view -> setDotInSecondNumber());

        Button eraseNumberButton = findViewById(R.id.erase_number_button);
        eraseNumberButton.setOnClickListener(view -> eraseSecondNumber());
    }

    /**
     * Clear secondNumber if it is not empty
     */
    private void eraseSecondNumber() {
        if (!secondNumber.isEmpty()) {
            Log.i("CalcApp", "Erase secondNumber is not empty");
            setSecondNumber(secondNumber.substring(0, secondNumber.length() - 1));
            setResultTextView(secondNumber);
        }
    }

    /**
     * Set secondNumber of number value
     *
     * @param number
     */
    private void setSecondNumber(String number) {
        Log.i("CalcApp", "Set second number: " + number);
        secondNumber = number;
    }

    /**
     * Set value of number to textView
     *
     * @param number
     */
    private void setResultTextView(String number) {
        Log.i("CalcApp", "Set textview number: " + number);
        resultTextView.setText(number);
    }

    /**
     * Set concatenate dot with secondNumber
     */
    private void setDotInSecondNumber() {
        if (!checkDotInSecondNumber()) {
            Log.i("CalcApp", "Number contains dot");
            addToSecondNumber(".");
        }
    }

    /**
     * Check contains dot in second number
     * return true if contains,
     * else - false
     *
     * @return
     */
    private boolean checkDotInSecondNumber() {
        return secondNumber.contains(".");
    }

    /**
     * Concatenate secondNumber and number
     *
     * @param number
     */
    private void addToSecondNumber(String number) {
        Log.i("CalcApp", "Press on button: " + number);
        secondNumber = secondNumber + number;
        setResultTextView(secondNumber);
    }

    /**
     * Set operation and clear textView
     *
     * @param operation
     */
    private void setOperation(DoubleBinaryOperator operation) {
        Log.i("CalcApp", "Choose operation");
        this.operation = operation;

        setFirstNumber(secondNumber);
        setResultTextView("");
    }

    /**
     * Set firstNumber value of secondNumber
     * and clear secondNumber
     *
     * @param number
     */
    private void setFirstNumber(String number) {
        Log.i("CalcApp", "Save first number: " + number);
        firstNumber = number;
        secondNumber = "";
    }

    /**
     * Calculate operation with firstNumber and secondNumber
     */
    private void launchOperation() {
        if (operation != null) {
            try {
                Log.i("CalcApp", "Calculate: " + firstNumber + ", " + secondNumber);
                Double resultOfOperation = operation
                        .applyAsDouble(
                                Double.parseDouble(firstNumber),
                                Double.parseDouble(secondNumber));
                this.resultNumber = decimalFormat.format(resultOfOperation);

                setResultTextView(this.resultNumber);
            } catch (Exception e) {
                Log.w("CalcApp", "(Operation error) Cannot calculate with: " + firstNumber + ", " + secondNumber);
                Toast.makeText(getApplicationContext(),
                        "Error to calculate",
                        Toast.LENGTH_LONG).show();
            }
        }

        clearNumbersAndOperation();

        setSecondNumber(resultNumber);
    }

    /**
     * Clear value of firstNumber, secondNumber and operation
     */
    private void clearNumbersAndOperation() {
        Log.i("CalcApp", "Clear textview number");
        firstNumber = "";
        secondNumber = "";
        operation = null;
    }
}