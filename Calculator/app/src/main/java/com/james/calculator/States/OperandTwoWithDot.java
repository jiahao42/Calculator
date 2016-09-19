package com.james.calculator.States;

import com.james.calculator.Calculator;

public class OperandTwoWithDot implements State {
    // TODO: 2016/9/18 to be continued...
    Calculator calculator;
    public OperandTwoWithDot(Calculator calculator) {
        this.calculator = calculator;
    }


    /**
     * 当找到小数点时应当作出的应对
     */
    @Override
    public void findDot() {
        calculator.setState(calculator.getErrorState());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        calculator.setState(calculator.getOperandTwoWithDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     */
    @Override
    public void findOperator() {
        calculator.setState(calculator.getDoneState());
    }

    /**
     * 当按下CE时应作出的应对
     */
    @Override
    public void onCEPressed() {
        calculator.setState(calculator.getReplaceableOperatorState());
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
        calculator.setState(calculator.getDoneState());
    }
}
