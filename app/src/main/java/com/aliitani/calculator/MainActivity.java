package com.aliitani.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends Activity {

    String num1 = "", num2 = "", mathAction = "", mathText = "";
    boolean mathClick = false, decimal = false, decDone1 = false, decDone2 = false, useAnswer = false, isDone = false;
    Button button;
    TextView screenView;
    String num1Display = "", num2Display = "",totalDisplay = "";

    public void Clear(View view) {
        screenView = (TextView) findViewById(R.id.Screen);

        screenView.setText("");
        num1 = "";
        num2 = "";
        num1Display = "";
        num2Display = "";
        totalDisplay = "";
        mathClick = false;
        mathAction = "";
        mathText = "";
        decimal = false;
        decDone1 = false;
        decDone2 = false;
        useAnswer = false;
        screenView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
    }

    public void total(View view) {
        screenView = (TextView) findViewById(R.id.Screen);
        String total = Calculation(num1,num2,mathAction);

        if (total.length() < 13) {
            screenView.setText(num1Display + mathText + num2Display + "\n" + total);
            num1 = total;
            num2 = "";
            num1Display = total;
            num2Display = "";
            useAnswer = true;
            isDone = true;
        } else {
            screenView.setText(num1Display + mathText + num2Display + "\n" + total.substring(0, 12));
            num1 = total;
            num2 = "";
            num1Display = total;
            num2Display = "";
            useAnswer = true;
            isDone = true;
        }
    }

    public String Calculation(String a, String b, String key) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);

        try {
            if (decimal) {
                double d1 = Double.parseDouble(a);
                double d2 = Double.parseDouble(b);

                double number = 0.0;
                totalDisplay = "";

                if (key.equals("Sum")) {
                    number = d1+d2;
                    totalDisplay = numberFormat.format(number);
                    return ""+ totalDisplay;
                }
                if (key.equals("Diff")) {
                    number = d1-d2;
                    totalDisplay = numberFormat.format(number);
                    return "" + totalDisplay;

                }
                if (key.equals("Product")) {
                    number = d1*d2;
                    totalDisplay = numberFormat.format(number);
                    return "" + totalDisplay;
                }
                if (key.equals("Divide")) {
                    number = d1/d2;
                    totalDisplay = numberFormat.format(number);
                    return "" +totalDisplay;
                }

            }else {
                long i1 = Long.parseLong(a);
                long i2 = Long.parseLong(b);


                long number = 0;
                totalDisplay = "";

                if (key.equals("Sum")) {
                    number = i1+i2;
                    totalDisplay = numberFormat.format(number);
                    return "" + totalDisplay;
                }
                if (key.equals("Diff")) {
                    number = i1-i2;
                    totalDisplay = numberFormat.format(number);
                    return "" + totalDisplay;
                }
                if (key.equals("Product")) {
                    number = i1 * i2;
                    totalDisplay = numberFormat.format(number);
                    return "" + totalDisplay;
                }
                if (key.equals("Divide")) {
                    if (i1 % i2 == 0) {
                        number = i1/i2;
                        totalDisplay = numberFormat.format(number);
                        return "" + totalDisplay;
                    }else {
                        double numberd = (double) i1/i2;
                        totalDisplay = numberFormat.format(numberd);
                      return "" + totalDisplay;
                    }
                }

                }
        } catch (Exception e) {

        }


        return "";
    }

    public void Math(View view){ // that is when we use + - * / and to use num2
        button = (Button) view;
        screenView = (TextView) findViewById(R.id.Screen);
        mathAction = button.getTag().toString();
        mathText = button.getText().toString();

        if(!useAnswer) {
            screenView.append(mathText);
            mathClick = true;
        } else {
            screenView.setText(num1Display + mathText);
            mathClick = true;
            isDone = false;
        }
    }

    public void buttonTapped(View view) {
        button = (Button) view;
        screenView = (TextView) findViewById(R.id.Screen);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);

        String n = screenView.getText().toString();

        if (n.length() > 13) {
            screenView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        }

        if(isDone) {
            isDone = false;
            Clear(screenView);
        }

        if (button.getTag().equals(".")) {


            decimal = true;
            if (!mathClick) {
                decDone1 = true;

                num1 += ".";


                screenView.setText(num1Display+ ".");
            } else {
                num2 += ".";


                screenView.setText(num1Display + mathText + num2Display +".");
                decDone2 = true;

            }
        }
        if (!button.getTag().equals(".")) {


                if (!mathClick) {

                    num1 += button.getTag().toString();

                    num1Display = numberFormat.format(Double.parseDouble(num1));

                    screenView.setText(num1Display);
                } else {

                    num2 += button.getTag().toString();

                    num2Display = numberFormat.format(Double.parseDouble(num2));

                    screenView.setText(num1Display + mathText + num2Display);
                }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
}
