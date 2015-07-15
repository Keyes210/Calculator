package com.alexlowe.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    String screenString = "";
    TextView screen;



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
        screenString += ".";
        displayScreen(screenString);
    }

    public void pressAC(View view) {
        screenString = "";
        displayScreen(screenString);
    }

    private void displayScreen(String screenString) {
        screen.setText(screenString);
    }


}
