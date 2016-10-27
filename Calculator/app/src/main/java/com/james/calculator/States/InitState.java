package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态0 初始状态
 */
public class InitState implements State {
    Calculator calculator;
    public InitState(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     * 设置操作数并跳转
     */
    @Override
    public void findDot() {
        calculator.setOperandOne();
        calculator.setCurrentState(calculator.getOperandOneWithDot());
    }

    /**
     * 当找到数字时应当作出的应对
     * 设置操作数并跳转
     */
    @Override
    public void findDigit() {
        calculator.setOperandOne();
        calculator.setCurrentState(calculator.getOperandOneWithoutDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     * 留在原状态即可
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下CE时应作出的应对
     * 留在原状态即可
     */
    @Override
    public void onCEPressed() {
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下C时应作出的应对
     * 留在原状态即可
     */
    @Override
    public void onCPressed() {
        calculator.resetAll();
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下等号时作出的应对
     * 留在原状态即可
     */
    @Override
    public void onEqualPressed() {
        calculator.setCurrentState(calculator.getInitState());
    }

    @Override
    public String toString() {
        return "InitState{" +
                "calculator=" + calculator +
                '}';
    }
}
