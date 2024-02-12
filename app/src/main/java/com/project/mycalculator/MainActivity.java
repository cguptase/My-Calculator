package com.project.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton btnC, btnBracketOpen, btnBracketClose, btnDivide;
    MaterialButton btn7, btn8, btn9, btnMultiply;
    MaterialButton btn4, btn5, btn6, btnAdd;
    MaterialButton btn1, btn2, btn3, btnSubstract;
    MaterialButton btnAllClear, btn0, btnDot, btnEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.resultTv);
        solutionTv = findViewById(R.id.solutionTv);

        assignId(btnC, R.id.btnClear);
        assignId(btnBracketOpen, R.id.btnBracketOpen);
        assignId(btnBracketClose, R.id.btnBracketClose);
        assignId(btnDivide, R.id.btnDivide);
        assignId(btn7, R.id.btn7);
        assignId(btn8, R.id.btn8);
        assignId(btn9, R.id.btn9);
        assignId(btnMultiply, R.id.btnMultiply);
        assignId(btn4, R.id.btn4);
        assignId(btn5, R.id.btn5);
        assignId(btn6, R.id.btn6);
        assignId(btnAdd, R.id.btnAdd);
        assignId(btn1, R.id.btn1);
        assignId(btn2, R.id.btn2);
        assignId(btn3, R.id.btn3);
        assignId(btnSubstract, R.id.btnSubstraction);
        assignId(btnAllClear, R.id.btnAllClear);
        assignId(btn0, R.id.btn0);
        assignId(btnDot, R.id.btnDot);
        assignId(btnEqual, R.id.btnEqual);

    }

    public void assignId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (dataToCalculate.length() == 0) {
                dataToCalculate = "0";
            } else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            if(dataToCalculate.startsWith("0")){
                dataToCalculate = dataToCalculate.replace("0","");
            }
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    public String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }

}
