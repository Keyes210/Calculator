package com.alexlowe.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    String screenString = "";
    TextView screen;
    Double number1 = null;
    Double number2 = null;
    String operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

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
        displayScreen(screenString);
    }

    public void pressSqrt(View view) {
        if (!screenString.equals("")) {
            number1 = Math.sqrt(Double.parseDouble(screenString));
            screenString = String.valueOf(number1);
            operator = "SR";
            displayScreen(screenString);
        }
    }

    public void pressPercent(View view) {
        operation("%");
    }

    public void pressPlus(View view) {
        operation("+");
        /* --Old Code
        if(screenString != "") {
            if(number1 != null && number2 == null){
                number2 = Double.parseDouble(screenString);
                operator = "+";
                Double result = calculate(number1, number2, operator);
                screenString = String.valueOf(result);
                displayScreen(screenString);
            }else{
                number1 = Double.parseDouble(screenString);
                number2 = null;
                operator = "+";
                screenString = "";
                displayScreen(screenString);
            }
        }*/
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
        //pick up here, trying to get plus operation to work, switch statment for operator, check for value in holder and screen
        //moved operation to calculate method instead of in each operation button's method
        if (!operator.equals("") && !screenString.equals("")) {
            if (operator.equals("SR")) {
                pressSqrt(view);
            } else {
                number2 = Double.parseDouble(screenString);
                number1 = calculate(number1, number2, operator);
                screenString = String.valueOf(number1);
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
        if (!screenString.equals("")) {
            if (number1 != null && number2 == null) {

                number2 = Double.parseDouble(screenString);
                operator = op;

                screenString = "";
                displayScreen(screenString);

                number1 = calculate(number1, number2, operator);
                number2 = null;
            } else {

                number1 = Double.parseDouble(screenString);
                number2 = null;
                operator = op;

                screenString = "";
                displayScreen(screenString);
            }
        }
    }

    private void displayScreen(String screenString) {
        screen.setText(screenString);
    }


}
