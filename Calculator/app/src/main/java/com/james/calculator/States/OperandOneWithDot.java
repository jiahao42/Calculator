package com.james.calculator.States;

import com.james.calculator.Calculator;

public class OperandOneWithDot implements State {

    Calculator calculator;
    public OperandOneWithDot(Calculator calculator) {
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

    }

    /**
     * 当找到运算符时应该作出的应对
     */
    @Override
    public void findOperator() {

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
}
