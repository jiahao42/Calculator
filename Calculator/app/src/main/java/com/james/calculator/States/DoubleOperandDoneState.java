package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态11
 */
public class DoubleOperandDoneState implements State {
    Calculator calculator;
    public DoubleOperandDoneState(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     */
    @Override
    public void findDot() {

    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {

    }

    /**
     * 当找到运算符时应该作出的应对
     * @param operator
     */
    @Override
    public void findOperator(char operator) {

    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {

    }

    /**
     * 当按下C时应作出的应对
     */
    @Override
    public void onCPressed() {

    }

    /**
     * 当按下等号时作出的应对
     */
    @Override
    public void onEqualPressed() {

    }

    @Override
    public String toString() {
        return "DoubleOperandDoneState{" +
                "calculator=" + calculator +
                '}';
    }
}
