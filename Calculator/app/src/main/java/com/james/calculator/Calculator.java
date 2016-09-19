package com.james.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.james.calculator.States.DoubleOperandDoneState;
import com.james.calculator.States.EmptyOperandTwoState;
import com.james.calculator.States.ErrorState;
import com.james.calculator.States.InitState;
import com.james.calculator.States.OperandOneWithDot;
import com.james.calculator.States.OperandOneWithoutDot;
import com.james.calculator.States.OperandTwoWithDot;
import com.james.calculator.States.OperandTwoWithoutDot;
import com.james.calculator.States.OperatorState;
import com.james.calculator.States.ReplaceableOperatorState;
import com.james.calculator.States.SingleOperandDoneState;
import com.james.calculator.States.SingleOperandState;
import com.james.calculator.States.State;


public class Calculator extends Activity implements View.OnClickListener {


    private TextView result;
    private EditText input;
    private Button btnZero;
    private Button btnClearOperand;
    private Button btnClearAll;
    private Button add;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button sub;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button mul;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button div;
    private Button equal;

    private State errorState;
    private State initState;
    private State operandOneWithDot;
    private State operandOneWithoutDot;
    private State operandTwoWithDot;
    private State operandTwoWithoutDot;
    private State operatorState;
    private State replaceableOperatorState;
    private State doubleOperandDoneState;
    private State emptyOperandTwoState;
    private State singleOperandDoneState;
    private State singleOperandSate;
    private MyDigitListener myDigitListener;

    private State state;
    private float operandOne = 0f;
    private float operandTwo = 0f;
    private float resultValue = 0f;
    private char operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculator);
        initWidget();
        initDigitListener();
    }

    private void initWidget(){
        result = (TextView)findViewById(R.id.result);
        input = (EditText)findViewById(R.id.input);
        btnZero = (Button)findViewById(R.id.btn_zero);
        btnClearOperand = (Button) findViewById(R.id.btn_clear_operand);
        btnClearAll = (Button) findViewById(R.id.btn_clear_all);
        add = (Button) findViewById(R.id.add);
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button) findViewById(R.id.btn_three);
        sub = (Button) findViewById(R.id.sub);
        btnFour = (Button) findViewById(R.id.btn_four);
        btnFive = (Button) findViewById(R.id.btn_five);
        btnSix = (Button) findViewById(R.id.btn_six);
        mul = (Button) findViewById(R.id.mul);
        btnSeven = (Button) findViewById(R.id.btn_seven);
        btnEight = (Button) findViewById(R.id.btn_eight);
        btnNine = (Button) findViewById(R.id.btn_nine);
        div = (Button) findViewById(R.id.div);
        equal = (Button) findViewById(R.id.equal);
    }

    private void initDigitListener() {
        myDigitListener = new MyDigitListener();
        btnOne.setOnClickListener(myDigitListener);
        btnTwo.setOnClickListener(myDigitListener);
        btnThree.setOnClickListener(myDigitListener);
        btnFour.setOnClickListener(myDigitListener);
        btnFive.setOnClickListener(myDigitListener);
        btnSix.setOnClickListener(myDigitListener);
        btnSeven.setOnClickListener(myDigitListener);
        btnEight.setOnClickListener(myDigitListener);
        btnNine.setOnClickListener(myDigitListener);
    }


    public Calculator() {
        this.errorState = new ErrorState(this);
        this.initState = new InitState(this);
        this.operandOneWithDot = new OperandOneWithDot(this);
        this.operandOneWithoutDot = new OperandOneWithoutDot(this);
        this.operandTwoWithDot = new OperandTwoWithDot(this);
        this.operandTwoWithoutDot = new OperandTwoWithoutDot(this);
        this.operatorState = new OperatorState(this);
        this.replaceableOperatorState = new ReplaceableOperatorState(this);
        this.doubleOperandDoneState = new DoubleOperandDoneState(this);
        this.emptyOperandTwoState = new EmptyOperandTwoState(this);
        this.singleOperandDoneState = new SingleOperandDoneState(this);
        this.singleOperandSate = new SingleOperandState(this);
        this.state = this.initState;
    }


    public void findDot() {
        state.findDot();
    }

    public void findDigit() {
        state.findDigit();
    }

    public void findOperator() {
        state.findOperator();
    }

    public void onCEPressed() {
        state.onCEPressed();
    }

    public void onCPressed() {
        state.onCPressed();
    }

    public void onEqualPressed() {
        state.onEqualPressed();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getErrorState() {
        return errorState;
    }

    public State getInitState() {
        return initState;
    }

    public State getOperandOneWithDot() {
        return operandOneWithDot;
    }

    public State getOperandOneWithoutDot() {
        return operandOneWithoutDot;
    }

    public State getOperandTwoWithDot() {
        return operandTwoWithDot;
    }

    public State getOperandTwoWithoutDot() {
        return operandTwoWithoutDot;
    }

    public State getOperatorState() {
        return operatorState;
    }

    public State getReplaceableOperatorState() {
        return replaceableOperatorState;
    }

    public State getDoubleOperandDoneState() {
        return doubleOperandDoneState;
    }

    public State getEmptyOperandTwoState() {
        return emptyOperandTwoState;
    }

    public State getSingleOperandDoneState() {
        return singleOperandDoneState;
    }

    public State getSingleOperandSate() {
        return singleOperandSate;
    }

    public State getState() {
        return state;
    }

    public EditText getInput() {
        return input;
    }

    public TextView getResult() {
        return result;
    }

    public float getOperandOne() {
        return operandOne;
    }

    public float getOperandTwo() {
        return operandTwo;
    }

    public float getResultValue() {
        return resultValue;
    }

    public void resetAll() {
        input.setText("");
        result.setText("");
        operandOne = 0f;
        operandTwo = 0f;
        resultValue = 0f;
    }

    public void showError() {
        input.setText(R.string.error);
        result.setText(R.string.error);
    }

    public void setOperandOne() {
        operandOne = Integer.parseInt(input.getText().toString());
        Log.d("operandOne",String .valueOf(operandOne));
    }

    public void setOperandTwo() {
        Log.d("operandTwo",String .valueOf(operandTwo));
        operandTwo = Integer.parseInt(input.getText().toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add:
                operator = '+';
                break;
            case R.id.sub:
                operator = '-';
                break;
            case R.id.mul:
                operator = '*';
                break;
            case R.id.div:
                operator = '/';
                break;
            case R.id.btn_clear_all:
                onCPressed();
                break;
            case R.id.btn_clear_operand:
                onCEPressed();
                break;
            default:
                break;
        }
    }

    public void setOperandTwoWithOperandOne() {
        operandTwo = operandOne;
    }

    public boolean calculate() {
        switch (operator)
        {
            case '+':
                resultValue = operandOne + operandTwo;
                break;
            case '-':
                resultValue = operandOne - operandTwo;
                break;
            case '*':
                resultValue = operandOne * operandTwo;
                break;
            case '/':
                if (operandTwo == 0f)
                {
                    return false;
                }
                resultValue = operandOne / operandTwo;
                break;
            default:
                break;
        }
        return true;
    }

    private class MyDigitListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_zero:
                    input.setText(input.getText().append('0'));
                    result.setText(result.getText().toString() + "0");
                    findDigit();
                    break;
                case R.id.btn_one:
                    input.setText(input.getText().append('1'));
                    result.setText(result.getText().toString() + "1");
                    findDigit();
                    break;
                case R.id.btn_two:
                    input.setText(input.getText().append('2'));
                    result.setText(result.getText().toString() + "2");
                    findDigit();
                    break;
                case R.id.btn_three:
                    input.setText(input.getText().append('3'));
                    result.setText(result.getText().toString() + "3");
                    findDigit();
                    break;
                case R.id.btn_four:
                    input.setText(input.getText().append('4'));
                    result.setText(result.getText().toString() + "4");
                    findDigit();
                    break;
                case R.id.btn_five:
                    input.setText(input.getText().append('5'));
                    result.setText(result.getText().toString() + "5");
                    findDigit();
                    break;
                case R.id.btn_six:
                    input.setText(input.getText().append('6'));
                    result.setText(result.getText().toString() + "6");
                    findDigit();
                    break;
                case R.id.btn_seven:
                    input.setText(input.getText().append('7'));
                    result.setText(result.getText().toString() + "7");
                    findDigit();
                    break;
                case R.id.btn_eight:
                    input.setText(input.getText().append('8'));
                    result.setText(result.getText().toString() + "8");
                    findDigit();
                    break;
                case R.id.btn_nine:
                    input.setText(input.getText().append('9'));
                    result.setText(result.getText().toString() + "9");
                    findDigit();
                    break;
            }
        }
    }
}
