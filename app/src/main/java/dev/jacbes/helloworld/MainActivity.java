package dev.jacbes.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numberTextView = findViewById(R.id.numbers_textview);
        Button oneButton = findViewById(R.id.one_button);
        Button twoButton = findViewById(R.id.two_button);
        Button threeButton = findViewById(R.id.three_button);
        Button fourButton = findViewById(R.id.four_button);
        Button fiveButton = findViewById(R.id.five_button);
        Button sixButton = findViewById(R.id.six_button);
        Button sevenButton = findViewById(R.id.seven_button);
        Button eightButton = findViewById(R.id.eight_button);
        Button nineButton = findViewById(R.id.nine_button);
        Button zeroButton = findViewById(R.id.zero_button);

        Button sumButton = findViewById(R.id.sum_button);
        Button subButton = findViewById(R.id.sub_button);
        Button divideButton = findViewById(R.id.divide_button);
        Button multiplyButton = findViewById(R.id.multiply_button);
        Button resultButton = findViewById(R.id.result_button);
    }
}