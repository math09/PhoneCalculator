package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private int op1=0;
    private int op2=0;
    private Ops operator=null;
    private boolean isOp1=true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button btnEgal = (Button)findViewById(R.id.buttonEqual);
        btnEgal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });
    }
    public void compute() {
        if(!isOp1){
                if (operator == Ops.PLUS){
                    op1 = op1 + op2;
                } else if (operator == Ops.MOINS){
                    op1 = op1 - op2;
                }else if (operator == Ops.FOIS){
                    op1 = op1 * op2;
                }else if (operator == Ops.DIV){
                    op1 = op1 / op2;
                } else {
                    Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG);
                    return;// do nothing if no operator
                }
            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }
    public void setOperator(View v) {
        if (v.getId() == R.id.buttonPlus){
            operator=Ops.PLUS;
        } else if (v.getId() == R.id.buttonMoins){
            operator=Ops.MOINS;
        }else if (v.getId() == R.id.buttonMultiply){
            operator=Ops.FOIS;
        }else if (v.getId() == R.id.buttonDivide){
            operator=Ops.DIV;
        } else {
                Toast.makeText(this, "Opérateur non reconnu",Toast.LENGTH_LONG);
                return; // do nothing if no operator
        }
        isOp1=false;
        updateDisplay();
        System.out.println("coucou");
    }
    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    private void updateDisplay() {
        int v=op1;
        if(!isOp1) {
            v=op2;
        }
        screen.setText(String.format("%9d",v));
    }

}