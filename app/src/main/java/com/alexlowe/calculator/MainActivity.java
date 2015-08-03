package com.alexlowe.calculator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends Activity {

    String screenString = "";
    TextView screen;
    TextView opScreen;
    Double number1 = null;
    Double number2 = null;
    String operator = "";

    Button plusButton;
    Button subButton;
    Button divButton;
    Button multButton;
    Button srButton;
    Button perButton;

    Button acButton;
    Button backButton;

    Button equalButton;




    DecimalFormat df = new DecimalFormat("@###########");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button shading
        plusButton = (Button) findViewById(R.id.buttonPlus);
        plusButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);
        subButton = (Button) findViewById(R.id.buttonSub);
        subButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);
        divButton = (Button) findViewById(R.id.buttonDivide);
        divButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);
        multButton = (Button) findViewById(R.id.buttonMult);
        multButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);
        srButton = (Button) findViewById(R.id.buttonSquareRoot);
        srButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);
        perButton = (Button) findViewById(R.id.buttonPercent);
        perButton.getBackground().setColorFilter(0xFF666869, PorterDuff.Mode.MULTIPLY);

        acButton = (Button) findViewById(R.id.buttonAC);
        acButton.getBackground().setColorFilter(0xFFFFD239, PorterDuff.Mode.MULTIPLY);
        backButton = (Button) findViewById(R.id.buttonBackspace);
        backButton.getBackground().setColorFilter(0xFFFFD239, PorterDuff.Mode.MULTIPLY);

        equalButton = (Button) findViewById(R.id.buttonEqual);
        equalButton.getBackground().setColorFilter(0xFFFF4D51, PorterDuff.Mode.MULTIPLY);


        //screen and fonts
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
        screen = (TextView) findViewById(R.id.screen);
        opScreen = (TextView) findViewById(R.id.opScreen);
        screen.setTypeface(customFont);
        opScreen.setTypeface(customFont);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);
        df.setMaximumFractionDigits(8);
    }

    public void press1(View view) {
        screenString += "1";
        displayScreen(screenString);
    }

    public void press2(View view) {
        screenString += "2";
        displayScreen(screenString);
    }

    public void press3(View view) {
        screenString += "3";
        displayScreen(screenString);
    }

    public void press4(View view) {
        screenString += "4";
        displayScreen(screenString);
    }

    public void press5(View view) {
        screenString += "5";
        displayScreen(screenString);
    }

    public void press6(View view) {
        screenString += "6";
        displayScreen(screenString);
    }

    public void press7(View view) {
        screenString += "7";
        displayScreen(screenString);
    }

    public void press8(View view) {
        screenString += "8";
        displayScreen(screenString);
    }

    public void press9(View view) {
        screenString += "9";
        displayScreen(screenString);
    }

    public void press0(View view) {
        screenString += "0";
        displayScreen(screenString);
    }

    public void pressPoint(View view) {
        if (!screenString.contains(".")) {
            screenString += ".";
            displayScreen(screenString);
        }
    }

    public void pressBack(View view) {
        if (screenString.length() > 0) {
            screenString = screenString.substring(0, screenString.length() - 1);
        }
        displayScreen(screenString);
    }

    public void pressAC(View view) {
        number1 = null;
        number2 = null;
        operator = "";
        screenString = "";
        displayOps("","");
        displayScreen(screenString);
    }

    public void pressSqrt(View view) {
        if (!screenString.equals("") && !screenString.equals(".") ) {
            number1 = Math.sqrt(Double.parseDouble(screenString));
            screenString = String.valueOf(df.format(number1));
            operator = "SR";
            displayScreen(screenString);
        }
    }

    public void pressPercent(View view) {
        operation("%");
    }

    public void pressPlus(View view) {
        operation("+");
    }

    public void pressMinus(View view) {
        operation("-");
    }

    public void pressMult(View view) {
        operation("*");
    }


    public void pressDiv(View view) {
        operation("/");
    }

    public void pressEqual(View view) {
        if (!operator.equals("") && !screenString.equals("")) {
            if (operator.equals("SR")) {
                pressSqrt(view);
            } else {
                if (number2 == null){
                    number2 = Double.parseDouble(screenString);
                }
                number1 = calculate(number1, number2, operator);
                screenString = String.valueOf(df.format(number1));
                displayOps("","");
                displayScreen(screenString);
            }
        }
    }

    private Double calculate(Double num1, Double num2, String operator) {
        Double result = 0.0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "%":
                result = (num1 / 100) * num2;
                break;
        }
        return result;
    }

    private void operation(String op) {
        if (!screenString.equals("") && !screenString.equals(".") ) {
            if (number1 != null && number2 == null) {

                number2 = Double.parseDouble(screenString);
                operator = op;


                screenString = "";
                displayScreen(screenString);

                number1 = calculate(number1, number2, operator);
                displayOps(String.valueOf(number1), operator);
                number2 = null;
            } else {

                number1 = Double.parseDouble(screenString);
                number2 = null;
                operator = op;

                displayOps(screenString, operator);
                screenString = "";
                displayScreen(screenString);
            }
        }
    }

    private void displayScreen(String screenString) {
        screen.setText(screenString);
    }

    private void displayOps(String number, String operator) {
        opScreen.setText(number+ " " + "\n" + operator + " ");
    }


}
