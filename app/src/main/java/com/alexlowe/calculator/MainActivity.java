package com.alexlowe.calculator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


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


    }

    public void press1(View view) {
        pressKey("1");
    }

    public void press2(View view) {
        pressKey("2");
    }

    public void press3(View view) {
        pressKey("3");
    }

    public void press4(View view) {
        pressKey("4");
    }

    public void press5(View view) {
        pressKey("5");
    }

    public void press6(View view) {
        pressKey("6");
    }

    public void press7(View view) {
        pressKey("7");
    }

    public void press8(View view) {
        pressKey("8");
    }

    public void press9(View view) {
        pressKey("9");
    }

    public void press0(View view) {
        pressKey("0");
    }



    public void pressPoint(View view) {
        if (!screenString.contains(".")) {
            pressKey(".");
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
            screenString = String.valueOf(formatNumber(number1));
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
                screenString = String.valueOf(formatNumber(number1));
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
                number1 = calculate(number1, number2, operator);
                operator = op;

                displayOps(String.valueOf(formatNumber(number1)), operator);
                screenString = "";
                displayScreen(screenString);

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


    private String formatNumber(Double number){
        NumberFormat df;
        String numStr = Double.toString(Math.abs(number));
        int intPlaces = numStr.indexOf('.');
        int decPlaces = numStr.length() - intPlaces - 1;

        if (intPlaces > 10 || decPlaces > 10){
            df = new DecimalFormat("0.0E0");
            df.setMinimumFractionDigits(6);
        }else{
            df = new DecimalFormat("@###########");
            df.setMaximumFractionDigits(0);
        }

        return df.format(number);
    }

    private void pressKey(String key) {
        if(screenString.length() < 13){
            screenString += key;
            displayScreen(screenString);
        }else{
            Toast.makeText(this, R.string.digits_exceeded_toast, Toast.LENGTH_LONG).show();
        }
    }
}
