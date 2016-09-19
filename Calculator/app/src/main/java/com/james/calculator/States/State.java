package com.james.calculator.States;

public interface State {
    /**
     * 当找到小数点时应当作出的应对
     */
    void findDot();

    /**
     * 当找到数字时应当作出的应对
     */
    void findDigit();

    /**
     * 当找到运算符时应该作出的应对
     * @param operator  运算符
     */
    void findOperator(char operator);

    /**
     * 当按下CE时应作出的应对
     */
    void onCEPressed();

    /**
     * 当按下C时应作出的应对
     */
    void onCPressed();

    /**
     * 当按下等号时作出的应对
     */
    void onEqualPressed();

}
