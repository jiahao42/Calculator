package com.james.calculator;

import android.app.Activity;
import android.os.Bundle;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class Calculator extends Activity implements View.OnClickListener{
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

    private State state = initState;
    private float operandOne = 0f;
    private float operandTwo = 0f;
    private float resultValue = 0f;
    private char operator;

    @Bind(R.id.result)
    TextView result;
    @Bind(R.id.input)
    EditText input;
    @Bind(R.id.btn_zero)
    Button btnZero;
    @Bind(R.id.btn_clear_operand)
    Button btnClearOperand;
    @Bind(R.id.btn_clear_all)
    Button btnClearAll;
    @Bind(R.id.add)
    Button add;
    @Bind(R.id.first_line)
    LinearLayout firstLine;
    @Bind(R.id.btn_one)
    Button btnOne;
    @Bind(R.id.btn_two)
    Button btnTwo;
    @Bind(R.id.btn_three)
    Button btnThree;
    @Bind(R.id.sub)
    Button sub;
    @Bind(R.id.second_line)
    LinearLayout secondLine;
    @Bind(R.id.btn_four)
    Button btnFour;
    @Bind(R.id.btn_five)
    Button btnFive;
    @Bind(R.id.btn_six)
    Button btnSix;
    @Bind(R.id.mul)
    Button mul;
    @Bind(R.id.third_line)
    LinearLayout thirdLine;
    @Bind(R.id.btn_seven)
    Button btnSeven;
    @Bind(R.id.btn_eight)
    Button btnEight;
    @Bind(R.id.btn_nine)
    Button btnNine;
    @Bind(R.id.div)
    Button div;
    @Bind(R.id.fourth_line)
    LinearLayout fourthLine;
    @Bind(R.id.equal)
    Button equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculator);
        ButterKnife.bind(this);
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

    public void setState(State state){
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

    public EditText getInput(){
        return input;
    }
    public TextView getResult(){
        return result;
    }
    public float getOperandOne(){
        return operandOne;
    }
    public float getOperandTwo(){
        return operandTwo;
    }
    public float getResultValue(){
        return resultValue;
    }
    public void resetAll(){
        input.setText("");
        result.setText("");
        operandOne = 0f;
        operandTwo = 0f;
        resultValue = 0f;
    }
    public void showError(){
        input.setText(R.string.error);
        result.setText(R.string.error);
    }
    public void setOperandOne(){
        operandOne = Integer.parseInt(input.getText().toString());
        input.setText("");
    }
    public void setOperandTwo(){
        operandTwo = Integer.parseInt(input.getText().toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
            default:
                break;
        }

    }
}
