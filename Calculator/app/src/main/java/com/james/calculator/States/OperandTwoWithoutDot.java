package com.james.calculator.States;

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
        calculator.getOperandTwo();
        calculator.setState(calculator.getOperandTwoWithDot());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.getOperandTwo();
        calculator.setState(calculator.getOperandTwoWithoutDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     */
    @Override
    public void findOperator() {
        calculator.setOperandTwoWithOperandOne();
        calculator.setState(calculator.getDoubleOperandDoneState());
    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {
        calculator.setState(calculator.getEmptyOperandTwoState());
    }

    /**
     * 当按下C时应作出的应对
     */
    @Override
    public void onCPressed() {
        calculator.setState(calculator.getInitState());
    }

    /**
     * 当按下等号时作出的应对
     */
    @Override
    public void onEqualPressed() {
        calculator.setState(calculator.getSingleOperandDoneState());
    }
}
