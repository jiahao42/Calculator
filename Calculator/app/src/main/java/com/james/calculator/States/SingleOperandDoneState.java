package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态7(暂定12)
 */
public class SingleOperandDoneState implements State {

    Calculator calculator;
    public SingleOperandDoneState(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     */
    @Override
    public void findDot() {
        calculator.setCurrentState(calculator.getSingleOperandDoneState());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当找到运算符时应该作出的应对
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.setCurrentState(calculator.getOperatorState());
    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下C时应作出的应对
     */
    @Override
    public void onCPressed() {
        calculator.resetAll();
        calculator.setCurrentState(calculator.getInitState());
    }

    /**
     * 当按下等号时作出的应对
     */
    @Override
    public void onEqualPressed() {
        if (calculator.calculate()){
            calculator.setCurrentState(calculator.getSingleOperandDoneState());
        }else {
            calculator.setCurrentState(calculator.getErrorState());
        }
    }

    @Override
    public String toString() {
        return "SingleOperandDoneState{" +
                "calculator=" + calculator +
                '}';
    }
}
