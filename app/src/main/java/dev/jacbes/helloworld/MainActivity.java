package dev.jacbes.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.function.IntBinaryOperator;

public class MainActivity extends AppCompatActivity {

    TextView numberTextView;
    Integer firstNumber = 0;
    Integer secondNumber = 0;
    IntBinaryOperator operation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberTextView = findViewById(R.id.numbers_textview);

        Button oneButton = findViewById(R.id.one_button);
        oneButton.setOnClickListener(view -> addNumberToTextView("1"));
        Button twoButton = findViewById(R.id.two_button);
        twoButton.setOnClickListener(view -> addNumberToTextView("2"));
        Button threeButton = findViewById(R.id.three_button);
        threeButton.setOnClickListener(view -> addNumberToTextView("3"));
        Button fourButton = findViewById(R.id.four_button);
        fourButton.setOnClickListener(view -> addNumberToTextView("4"));
        Button fiveButton = findViewById(R.id.five_button);
        fiveButton.setOnClickListener(view -> addNumberToTextView("5"));
        Button sixButton = findViewById(R.id.six_button);
        sixButton.setOnClickListener(view -> addNumberToTextView("6"));
        Button sevenButton = findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(view -> addNumberToTextView("7"));
        Button eightButton = findViewById(R.id.eight_button);
        eightButton.setOnClickListener(view -> addNumberToTextView("8"));
        Button nineButton = findViewById(R.id.nine_button);
        nineButton.setOnClickListener(view -> addNumberToTextView("9"));
        Button zeroButton = findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(view -> addNumberToTextView("0"));

        Button sumButton = findViewById(R.id.sum_button);
        sumButton.setOnClickListener(view -> writeNumber((x, y) -> x + y));
        Button subButton = findViewById(R.id.sub_button);
        subButton.setOnClickListener(view -> writeNumber((x, y) -> x - y));
        Button divideButton = findViewById(R.id.divide_button);
        divideButton.setOnClickListener(view -> writeNumber((x, y) -> x / y));
        Button multiplyButton = findViewById(R.id.multiply_button);
        multiplyButton.setOnClickListener(view -> writeNumber((x, y) -> x * y));
        Button resultButton = findViewById(R.id.result_button);
        resultButton.setOnClickListener(view -> calculateOperation());
    }

    private void addNumberToTextView(String number) {
        String numberToView = numberTextView.getText().toString() + number;

        numberTextView.setText(numberToView);
    }

    private void writeNumber(IntBinaryOperator operation) {
        this.operation = operation;

        String inputNumber = numberTextView.getText().toString();
        firstNumber = Integer.valueOf(inputNumber);

        numberTextView.setText("");
    }

    private void calculateOperation() {
        String number = numberTextView.getText().toString();
        secondNumber = Integer.valueOf(number);
        Integer result = operation.applyAsInt(firstNumber, secondNumber);
        numberTextView.setText(result.toString());
    }
}