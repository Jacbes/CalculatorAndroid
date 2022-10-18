package dev.jacbes.calculatorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private void eraseSecondNumber() {
        if (!secondNumber.isEmpty()) {
            setSecondNumber(secondNumber.substring(0, secondNumber.length() - 1));
            setResultTextView(secondNumber);
        }
    }

    private void setSecondNumber(String number) {
        secondNumber = number;
    }

    private void setResultTextView(String number) {
        resultTextView.setText(number);
    }

    private void setDotInSecondNumber() {
        if (!checkDotInSecondNumber()) {
            addToSecondNumber(".");
        }
    }

    private boolean checkDotInSecondNumber() {
        return secondNumber.contains(".");
    }

    private void addToSecondNumber(String number) {
        secondNumber = secondNumber + number;
        setResultTextView(secondNumber);
    }

    private void setOperation(DoubleBinaryOperator operation) {
        this.operation = operation;

        setFirstNumber(secondNumber);
        setResultTextView("");
    }

    private void setFirstNumber(String number) {
        firstNumber = number;
        secondNumber = "";
    }

    private void launchOperation() {
        if (operation != null) {
            try {
                Double resultOfOperation = operation
                        .applyAsDouble(
                                Double.parseDouble(firstNumber),
                                Double.parseDouble(secondNumber));
                this.resultNumber = decimalFormat.format(resultOfOperation);

                setResultTextView(this.resultNumber);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Error to calculate",
                        Toast.LENGTH_LONG).show();
            }
        }

        clearNumbersAndOperation();

        setSecondNumber(resultNumber);
    }

    private void clearNumbersAndOperation() {
        firstNumber = "";
        secondNumber = "";
        operation = null;
    }
}