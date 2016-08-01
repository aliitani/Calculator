package com.aliitani.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    String num1 = "", num2 = "", mathAction = "", mathText = "";
    boolean mathClick = false, decimal = false, decDone1 = false, decDone2 = false, useAnswer = false, isDone = false;
    Button button;
    TextView screenView;

    public void Clear(View view) {
        screenView = (TextView) findViewById(R.id.Screen);

        screenView.setText("");
        num1 = "";
        num2 = "";
        mathClick = false;
        mathAction = "";
        mathText = "";
        decimal = false;
        decDone1 = false;
        decDone2 = false;
        useAnswer = false;
    }

    public void total(View view) {
        screenView = (TextView) findViewById(R.id.Screen);
        String total = Calculation(num1,num2,mathAction);

        if (total.length() < 13) {
            screenView.setText(num1 + mathText + num2 + "\n" + total);
            num1 = total;
            num2 = "";
            useAnswer = true;
            isDone = true;
        } else {
            screenView.setText(num1 + mathText + num2 + "\n" + total.substring(0, 12));
            num1 = total;
            num2 = "";
            useAnswer = true;
            isDone = true;
        }
    }

    public String Calculation(String a, String b, String key) {


        try {
            if (decimal) {

                double d1 = Double.parseDouble(a);
                double d2 = Double.parseDouble(b);

                if (key.equals("Sum")) {
                    return ""+ (d1+d2);
                }
                if (key.equals("Diff")) {

                    return "" + (d1 - d2);

                }
                if (key.equals("Product")) {
                    return "" + (d1 * d2);
                }
                if (key.equals("Divide")) {
                    if (d1 % d2 == 0) {
                        return "" + d1/d2;
                    }else {
                        return "" + ((double) d1/d2);
                    }
                }

            }else {
                int i1 = Integer.parseInt(a);
                int i2 = Integer.parseInt(b);

                if (key.equals("Sum")) {
                    return "" + (i1 + i2);
                }
                if (key.equals("Diff")) {
                   return "" + (i1 - i2);
                }
                if (key.equals("Product")) {
                   return "" + i1 * i2;
                }
                if (key.equals("Divide")) {
                    if (i1 % i2 == 0) {
                        return "" + i1/i2;
                    }else {
                      return "" + ((double) i1/i2);
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
            screenView.setText(num1 + mathText);
            mathClick = true;
            isDone = false;
        }
    }

    public void buttonTapped(View view) {
        button = (Button) view;
        screenView = (TextView) findViewById(R.id.Screen);

        if(isDone) {
            isDone = false;
            Clear(screenView);
        }

        if (button.getTag().equals(".")) {
            decimal = true;
            if (!mathClick) {
                decDone1 = true;
                num1 += ".";
                screenView.setText(num1);


            } else {
                num2 += ".";
                screenView.setText("");
                screenView.setText(num1 + mathText + num2);
                decDone2 = true;

            }
        }
        if (!button.getTag().equals(".")) {
            if (!mathClick) {

                num1 += button.getTag().toString();
                screenView.setText(num1);

            } else {
                num2 += button.getTag().toString();
                screenView.setText("");
                screenView.setText(num1 + mathText + num2);
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
