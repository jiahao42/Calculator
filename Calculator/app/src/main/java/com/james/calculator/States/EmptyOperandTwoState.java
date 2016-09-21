package com.james.calculator.States;

import com.james.calculator.Calculator;

/**
 * 对应图中状态6
 */
public class EmptyOperandTwoState implements State {
    Calculator calculator;
    public EmptyOperandTwoState(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     */
    @Override
    public void findDot() {
        calculator.setOperandTwo();
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
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        calculator.changeOperator(operator);
        calculator.setCurrentState(calculator.getOperatorState());
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

    /**
     * 当按下等号时作出的应对
     * 此时操作数2为空
     * 按下等号则直接将操作数2的值赋给操作数1
     * 然后不停 OP1 OP OP2
     */
    @Override
    public void onEqualPressed() {
        calculator.setOperandTwoWithOperandOne();
        if (calculator.calculate(calculator.getOperator())){
            calculator.appendOperand(String.valueOf(calculator.getOperandTwo()));
            calculator.showUltimateResult();
            calculator.setCurrentState(calculator.getSingleOperandDoneWithSelfState());
        }else{
            calculator.showError();
            calculator.setCurrentState(calculator.getErrorState());
        }
    }

    @Override
    public String toString() {
        return "EmptyOperandTwoState{" +
                "calculator=" + calculator +
                '}';
    }
}
