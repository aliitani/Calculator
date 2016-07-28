package com.aliitani.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    String num1 = "", num2 = "", mathAction = "", mathText = "";
    boolean mathClick = false, decimal = false, decDone1 = true, decDone2 = true;
    Button button;
    TextView textView;

    public void Clear(View view) {
        textView = (TextView) findViewById(R.id.Screen);
        textView.setText("");
        num1 = "";
        num2 = "";
        mathClick = false;
        mathText = "";
        decimal = false;
        decDone1 = true;
        decDone2 = true;

    }

    public void total(View view) {
        textView = (TextView) findViewById(R.id.Screen);
        String total = Calculation(num1,num2,mathAction);
        if (total.length() < 13) {
            textView.setText(num1 + mathText + num2 + "\n" + total);
        } else {
            textView.setText(num1 + mathText + num2 + "\n" + total.substring(0, 12));
        }
        System.out.println(Calculation(num1, num2, mathAction));
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
                    System.out.println("Here");
                    return "" + (d1/d2);
                }

            }else {
                int a1 = Integer.parseInt(a);
                int b1 = Integer.parseInt(b);

                if (key.equals("Sum")) {
                    return "" + (a1 + b1);
                }
                if (key.equals("Diff")) {
                   return "" + (a1 - b1);
                }
                if (key.equals("Product")) {
                   return "" + a1 * b1;
                }
                if (key.equals("Divide")) {
                    System.out.println("Here");
                    return "" + ((double) a1/b1);
                }

                }
        } catch (Exception e) {

        }


        return "";
    }

    public void Math(View view){ // that is when we use + - * / and to use num2
        button = (Button) view;

        mathAction = button.getTag().toString();
        mathText = button.getText().toString();
        textView = (TextView) findViewById(R.id.Screen);
        textView.append(button.getText().toString());

        System.out.println(mathAction);

        mathClick = true;
    }
    public void buttonTapped(View view) {

        button = (Button) view;
        textView = (TextView) findViewById(R.id.Screen);

        if (button.getTag().equals(".")) {
            decimal = true;

            if (decDone1) {
                decDone1 = false;
                num1+= ".";
                textView.setText(num1);
                System.out.println(num1);


            } else{
                if (mathClick && decDone2) {
                    num2 += ".";
                    textView.setText("");
                    textView.setText(num1 + mathText + num2);
                    System.out.println(num2);
                    decDone2 = false;
                }
            }
        }
        if (!button.getTag().equals(".")){
            if (!mathClick) {

                num1 += button.getTag().toString();
                textView.setText(num1);
                System.out.println(num1);

            } else {

                num2 += button.getTag().toString();
                textView.setText("");
                textView.setText(num1  + mathText +  num2);
                System.out.println(num2);
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
