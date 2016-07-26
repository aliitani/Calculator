package com.aliitani.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String num1 = "", num2 = "", mathAction = "";;
    boolean mathClick = false;

    Button button;
    TextView textView;

    public void Clear(View view) {

        num1 = "";
        num2 = "";
        mathClick = false;

    }

    public void total(View view) {
        textView = (TextView) findViewById(R.id.Screen);

        textView.setText("" + Calculation(num1,num2,mathAction));
        System.out.println(Calculation(num1, num2, mathAction));

        Clear(null);
    }
    public int Calculation(String a, String b, String key) {
        int a1 = Integer.parseInt(a);
        int b1 = Integer.parseInt(b);

        if (key.equals("Sum")) {return a1 + b1;}
        if (key.equals("Diff")) {

            if (a1 < b1) {
                return (int) b1 - a1;
            } else {
                return (int) a1 - b1; // that is either if a1 > b1 or a1 == b1
            }

        }
        if (key.equals("Product")) {return a1 * b1;}
        if (key.equals("Divide")) {return (int) a1 / b1;}


        return 0;
    }

    public void Math(View view){ // that is when we use + - * / and to use num2
        button = (Button) view;

        mathAction = button.getTag().toString();
        System.out.println(mathAction);
        mathClick = true;

    }
    public void buttonTapped(View view) {

        button = (Button) view;

        if (mathClick == false) {

            num1 += button.getTag().toString();
            System.out.println(num1);
        } else {

            num2 += button.getTag().toString();
            System.out.println(num2);
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
