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
     * OperandOne 和 Operator
     * 在Input栏中留下OperandOne
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.setOperandTwoWithOperandOne();
        calculator.appendOperand(String.valueOf(calculator.getOperandTwo()));
        calculator.appendOperator(operator);
        calculator.setCurrentState(calculator.getOperatorState());
    }

    /**
     * 当按下等号时作出的应对
     */
    @Override
    public void onEqualPressed() {
        if (calculator.calculate()){
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
