package com.james.calculator.States;

import android.util.Log;

import com.james.calculator.Calculator;

/**
 * 对应图中状态8
 */
public class OperandTwoWithoutDot implements State {


    Calculator calculator;

    public OperandTwoWithoutDot(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     * 跳到OperandTwoWithDot
     */
    @Override
    public void findDot() {
        calculator.setOperandTwo();
        Log.d("StateChanged","ErrorState");
        calculator.setCurrentState(calculator.getOperandTwoWithDot());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.setOperandTwo();
        calculator.setCurrentState(calculator.getOperandTwoWithoutDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     * 此时应该在Result栏追加
     * 计算完成后的OperandOne 和 新的Operator
     * 并要使OP2 = OP1 防止用户不输入下一个操作数直接按等于号
     * 清空Input栏
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.clearResult();
        if (calculator.calculate(calculator.getOperatorBefore())){
            calculator.appendOperand(String.valueOf(calculator.getOperandOne()));
            calculator.appendOperator(operator);
            calculator.setOperandTwoWithOperandOne();
            calculator.clearInput();
            calculator.setCurrentState(calculator.getOperatorState());
        }else {
            calculator.showError();
            calculator.setCurrentState(calculator.getErrorState());
        }
    }

    /**
     * 当按下等号时作出的应对
     * 在Result中补充OperandTwo
     * 并且显示最终结果
     */
    @Override
    public void onEqualPressed() {
        if (calculator.calculate(calculator.getOperator())){
            calculator.appendOperand(String.valueOf(calculator.getOperandTwo()));
            calculator.showUltimateResult();
            calculator.setCurrentState(calculator.getSingleOperandDoneWithSelfState());
        }else {
            Log.d("--Error--",this.toString());
            calculator.showError();
            calculator.setCurrentState(calculator.getErrorState());
        }
    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {
        calculator.clearInput();
        calculator.setCurrentState(calculator.getEmptyOperandTwoState());
    }

    /**
     * 当按下C时应作出的应对
     */
    @Override
    public void onCPressed() {
        calculator.resetAll();
        calculator.setCurrentState(calculator.getInitState());
    }

    @Override
    public String toString() {
        return "OperandTwoWithoutDot{" +
                "calculator=" + calculator +
                '}';
    }
}
