package com.james.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.james.calculator.States.SingleOperandDoneWithSelfState;
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
    private State singleOperandDoneWithSelfState;
    private State singleOperandSate;
    private State currentState;


    private MyDigitListener myDigitListener;
    private MyOperatorListener myOperatorListener;


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
        initOperatorListener();
        initOtherListener();
    }

    /**
     * Bind各种控件
     */
    private void initWidget() {
        result = (TextView) findViewById(R.id.result);
        input = (EditText) findViewById(R.id.input);
        btnZero = (Button) findViewById(R.id.btn_zero);
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

    /**
     * 为所有数字按钮注册监听器
     */
    private void initDigitListener() {
        myDigitListener = new MyDigitListener();
        btnZero.setOnClickListener(myDigitListener);
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

    /**
     * 为所有运算符注册监听器
     */
    private void initOperatorListener() {
        myOperatorListener = new MyOperatorListener();
        add.setOnClickListener(myOperatorListener);
        sub.setOnClickListener(myOperatorListener);
        mul.setOnClickListener(myOperatorListener);
        div.setOnClickListener(myOperatorListener);
        equal.setOnClickListener(myOperatorListener);
    }

    /**
     * 注册其他监听器
     */
    private void initOtherListener() {
        btnClearAll.setOnClickListener(this);
        btnClearOperand.setOnClickListener(this);
    }

    /**
     * 初始化所有状态
     */
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
        this.singleOperandDoneWithSelfState = new SingleOperandDoneWithSelfState(this);
        this.singleOperandSate = new SingleOperandState(this);
        this.currentState = this.initState;
    }


    /**
     * 找到小数点时
     */
    public void findDot() {
        currentState.findDot();
    }

    /**
     * 找到数字时
     */
    public void findDigit() {
        currentState.findDigit();
    }

    /**
     * 找到运算符时
     */
    public void findOperator() {
        currentState.findOperator(operator);
    }

    /**
     * 按下CE时
     */
    public void onCEPressed() {
        currentState.onCEPressed();
    }

    /**
     * 按下C时
     */
    public void onCPressed() {
        currentState.onCPressed();
    }

    /**
     * 按下等于时
     */
    public void onEqualPressed() {
        currentState.onEqualPressed();
    }

    /**
     * 设置当前的状态
     *
     * @param currentState
     */
    public void setCurrentState(State currentState) {
        Log.d("-----StateChanged-----", currentState.toString());
        this.currentState = currentState;
    }

    /**
     * 返回ErrorState的实例
     *
     * @return
     */
    public State getErrorState() {
        return errorState;
    }

    /**
     * 返回InitState的实例
     *
     * @return
     */
    public State getInitState() {
        return initState;
    }

    /**
     * 返回OperandOneWithDot的实例
     *
     * @return
     */
    public State getOperandOneWithDot() {
        return operandOneWithDot;
    }

    /**
     * 返回OperandOneWithoutDot的实例
     *
     * @return
     */
    public State getOperandOneWithoutDot() {
        return operandOneWithoutDot;
    }

    /**
     * 返回OperandTwoWithDot的实例
     *
     * @return
     */
    public State getOperandTwoWithDot() {
        return operandTwoWithDot;
    }

    /**
     * 返回OperandTwoWithoutDot的实例
     *
     * @return
     */
    public State getOperandTwoWithoutDot() {
        return operandTwoWithoutDot;
    }

    /**
     * 返回OperatorState的实例
     *
     * @return
     */
    public State getOperatorState() {
        return operatorState;
    }

    /**
     * 获取ReplaceableOperatorState的实例
     *
     * @return
     */
    public State getReplaceableOperatorState() {
        return replaceableOperatorState;
    }

    /**
     * 获取DoubleOperandDoneState的实例
     *
     * @return
     */
    public State getDoubleOperandDoneState() {
        return doubleOperandDoneState;
    }

    /**
     * 获取EmptyOperandTwoState的实例
     *
     * @return
     */
    public State getEmptyOperandTwoState() {
        return emptyOperandTwoState;
    }

    /**
     * 获取SingleOperandDoneState的实例
     *
     * @return
     */
    public State getSingleOperandDoneWithSelfState() {
        return singleOperandDoneWithSelfState;
    }

    /**
     * 获取SingleOperandState的实例
     *
     * @return
     */
    public State getSingleOperandState() {
        return singleOperandSate;
    }

    /**
     * 获取当前状态的实例
     *
     * @return
     */
    public State getCurrentState() {
        return currentState;
    }

    /**
     * 获取输入框的实例
     *
     * @return
     */
    public EditText getInput() {
        return input;
    }

    /**
     * 获取展示框的实例
     *
     * @return
     */
    public TextView getResult() {
        return result;
    }

    /**
     * 获取操作数1
     *
     * @return
     */
    public float getOperandOne() {
        return operandOne;
    }

    /**
     * 获取操作数2
     *
     * @return
     */
    public float getOperandTwo() {
        return operandTwo;
    }

    /**
     * 获取最终值
     *
     * @return
     */
    public float getResultValue() {
        return resultValue;
    }

    /**
     * 设置当前运算符
     *
     * @param operator
     */
    public void setOperator(char operator) {
        Log.d("Operator", String.valueOf(operator));
        this.operator = operator;
    }

    /**
     * 向Result框中追加数字
     * @param operand
     */
    public void appendOperand(String operand){
        getResult().setText(getResult().getText().toString() + operand);
    }

    /**
     * 要在Result中追加一个运算符
     *
     * @param operator
     */
    public void appendOperator(char operator) {
        Log.d("---OperatorChanged---", String.valueOf(operator));
        getResult().setText(getResult().getText().toString() + String.valueOf(operator));
    }

    /**
     * Result中已经有运算符，要更改此运算符
     * 通过正则匹配到该运算符，然后替换之
     *
     * @param operator
     */
    public void changeOperator(char operator) {
        Log.d("---OperatorChanged---", String.valueOf(operator));
        getResult().setText(getResult().getText().toString().replaceFirst("[\\+\\-\\*/]", String.valueOf(operator)));
    }

    /**
     * 重置所有变量
     */
    public void resetAll() {
        input.setText("");
        result.setText("");
        operandOne = 0f;
        operandTwo = 0f;
        resultValue = 0f;
    }

    /**
     * 设置错误信息
     */
    public void showError() {
        input.setText(R.string.error);
        result.setText(R.string.error);
    }

    /**
     * 设置操作数1
     */
    public void setOperandOne() {
        operandOne = Integer.parseInt(input.getText().toString());
        Log.d("operandOne", String.valueOf(operandOne));
    }

    /**
     * 设置操作数2
     */
    public void setOperandTwo() {
        operandTwo = Integer.parseInt(input.getText().toString());
        Log.d("operandTwo", String.valueOf(operandTwo));
    }

    /**
     * 注册监听器
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
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

    /**
     * 将操作数1的值赋给操作数2
     */
    public void setOperandTwoWithOperandOne() {
        operandTwo = operandOne;
        Log.d("OperandTwo", String.valueOf(operandTwo));
    }

    /**
     * 计算结果
     *
     * @return 除数为0则返回false，其余返回true
     */
    public boolean calculate() {
        Log.d("operandOne", String.valueOf(operandOne));
        Log.d("operandTwo", String.valueOf(operandTwo));
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

    /**
     * 清空输入框
     * 当按下运算符时，要清空
     */
    public void clearInput() {
        getInput().setText("");
    }

    /**
     * 清空答案框
     */
    public void clearResult() {
        getResult().setText("");
    }

    /**
     * 显示最终答案，这是在按下等于号的情况
     * 显示时追加 = 号
     */
    public void showUltimateResult() {
        getInput().setText(String.valueOf(resultValue));
        getResult().setText(getResult().getText().toString() + "=" + String.valueOf(resultValue));
    }

    /**
     *
     */
    public void showUltimateResultInInput(){
        getInput().setText(getInput().getText().append(String.valueOf(resultValue)));
    }

    /**
     * 所有数字按钮的监听器
     */
    private class MyDigitListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_zero:
                    input.setText(input.getText().append('0'));
                    //result.setText(result.getText().toString() + "0");
                    findDigit();
                    break;
                case R.id.btn_one:
                    input.setText(input.getText().append('1'));
                    //result.setText(result.getText().toString() + "1");
                    findDigit();
                    break;
                case R.id.btn_two:
                    input.setText(input.getText().append('2'));
                    //result.setText(result.getText().toString() + "2");
                    findDigit();
                    break;
                case R.id.btn_three:
                    input.setText(input.getText().append('3'));
                    //result.setText(result.getText().toString() + "3");
                    findDigit();
                    break;
                case R.id.btn_four:
                    input.setText(input.getText().append('4'));
                    //result.setText(result.getText().toString() + "4");
                    findDigit();
                    break;
                case R.id.btn_five:
                    input.setText(input.getText().append('5'));
                    //result.setText(result.getText().toString() + "5");
                    findDigit();
                    break;
                case R.id.btn_six:
                    input.setText(input.getText().append('6'));
                    //result.setText(result.getText().toString() + "6");
                    findDigit();
                    break;
                case R.id.btn_seven:
                    input.setText(input.getText().append('7'));
                    //result.setText(result.getText().toString() + "7");
                    findDigit();
                    break;
                case R.id.btn_eight:
                    input.setText(input.getText().append('8'));
                    //result.setText(result.getText().toString() + "8");
                    findDigit();
                    break;
                case R.id.btn_nine:
                    input.setText(input.getText().append('9'));
                    //result.setText(result.getText().toString() + "9");
                    findDigit();
                    break;
            }
        }
    }

    /**
     * 所有操作符的监听器
     */
    private class MyOperatorListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.add:
                    operator = '+';
                    findOperator();
                    break;
                case R.id.sub:
                    operator = '-';
                    findOperator();
                    //result.setText(result.getText().toString() + "-");
                    break;
                case R.id.mul:
                    operator = '*';
                    findOperator();
                    //result.setText(result.getText().toString() + "*");
                    break;
                case R.id.div:
                    operator = '/';
                    findOperator();
                    //result.setText(result.getText().toString() + "/");
                    break;
                case R.id.equal:
                    calculate();
                    onEqualPressed();
                    //result.setText(result.getText().toString() + String.valueOf(resultValue));
                    break;
                default:
                    break;
            }
        }
    }
}
