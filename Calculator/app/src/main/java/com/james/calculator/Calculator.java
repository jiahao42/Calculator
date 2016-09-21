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

/**
 *
 *  ---------------------------------测试用例-----------------------------------------
 *
 *
 *
 * 
 * 1.   123 + 456 =         正常两步计算
 * 2.   12 + =========  无操作数2时多次按下等号的默认操作
 * 3.   12 -+* 3 =           更改运算符
 * 4.   12 -+* =               更改运算符。且无操作数2时的默认操作
 * 5.   12 + 34 - 45 * 3 ========       多步计算，多次按下等号的默认操作
 * 6.   12 + 34 ++++        多次输入相同操作符的操作
 * 7.   127 / 0 =             查看除数为0时的错误处理
 * 8.   127 / 0 +             查看除数为0时的错误处理
 * 9.   36 =========    查看直接输入数字按下等号的情况
 * 10.  12 + 34 = + 56   当一次运算完成后又将结果作为操作数
 * 11.  +*-/=                  在初始状态下直接输入各种运算符
 *
 *
 *
 *  ---------------------------------测试用例-----------------------------------------
 */

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
    //private float resultValue = 0f;
    private char operator;
    private char operatorBefore;


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
     * 设置当前运算符
     * 并保存之前的运算符
     *
     * @param operator
     */
    public void setOperator(char operator) {
        Log.d("Operator", String.valueOf(operator));
        this.operator = operator;
    }

    /**
     * 注意 该函数不能与setOperator合为一个函数
     * 因为适用场景并不相同
     * 比如当用户想修改当前运算符时，例：1 -  --> 1 *
     * 如果两个函数合在一起
     * 那么operatorBefore会被赋予错误的值
     */
    public void setOperatorBefore(){
        this.operatorBefore = this.operator;
    }

    /**
     * 返回当前的运算符
     *
     * @return
     */
    public char getOperator() {
        return this.operator;
    }

    /**
     * 返回前一个运算符
     *
     * @return
     */
    public char getOperatorBefore() {
        return this.operatorBefore;
    }

    /**
     * 向Result框中追加数字
     *
     * @param operand
     */
    public void appendOperand(String operand) {
        Log.d("---AppendingOperand---", operand);
        getResult().setText(getResult().getText().toString() + operand);
    }

    /**
     * 要在Result中追加一个运算符
     *
     * @param operator
     */
    public void appendOperator(char operator) {
        Log.d("---AppendingOperator---", String.valueOf(operator));
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
        //一开始想用正则来匹配最后一个运算符
        //但是发现当式子如 1+2-3* 时 难以匹配 故放弃
        //getResult().setText(getResult().getText().toString().replaceFirst("((([0-9]*)[\\+\\-\\*/])*)", String.valueOf(operator)));
        //如今用最笨的方法解决
        // TODO: 2016/9/20 待优化
        getResult().setText(getResult().getText().toString().substring(0, getResult().getText().toString().length() - 1));
        getResult().setText(getResult().getText().toString() + String.valueOf(operator));
    }

    /**
     * 重置所有变量
     */
    public void resetAll() {
        input.setText("");
        result.setText("");
        operandOne = 0f;
        operandTwo = 0f;
        //resultValue = 0f;
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
        operandOne = Float.parseFloat(input.getText().toString());
        Log.d("operandOne", String.valueOf(operandOne));
    }

    /**
     * 设置操作数2
     */
    public void setOperandTwo() {
        operandTwo = Float.parseFloat(input.getText().toString());
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
        Log.d("settingTwoWithOne", String.valueOf(operandTwo));
    }

    /**
     * 计算结果
     *
     * @return 除数为0则返回false，其余返回true
     * @param operator
     */
    public boolean calculate(char operator) {
        Log.d("Calculate -- operandOne", String.valueOf(operandOne));
        Log.d("Calculate -- operandTwo", String.valueOf(operandTwo));
        Log.d("Calculate -- Operator", String.valueOf(this.operator));
        switch (operator)
        {
            case '+':
                operandOne = operandOne + operandTwo;
                break;
            case '-':
                operandOne = operandOne - operandTwo;
                break;
            case '*':
                operandOne = operandOne * operandTwo;
                break;
            case '/':
                if (operandTwo == 0f)
                {
                    return false;
                }
                operandOne = operandOne / operandTwo;
                break;
            default:
                break;
        }
        Log.d("--CalculateResult---",String.valueOf(getOperandOne()));
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
        getInput().setText(String.valueOf(operandOne));
        getResult().setText(getResult().getText().toString() + "=" + String.valueOf(operandOne));
    }

    /**
     *
     */
    public void showUltimateResultInInput() {
        getInput().setText(getInput().getText().append(String.valueOf(operandOne)));
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
                    onEqualPressed();
                    //result.setText(result.getText().toString() + String.valueOf(resultValue));
                    break;
                default:
                    break;
            }
        }
    }
}
