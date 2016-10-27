package com.james.calculator.States;

import android.util.Log;

import com.james.calculator.Calculator;

/**
 * 对应图中状态4
 */
public class OperatorState implements State {


    Calculator calculator;
    public OperatorState(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 当找到小数点时应当作出的应对
     */
    @Override
    public void findDot() {
        calculator.setCurrentState(calculator.getOperandTwoWithDot());
    }

    /**
     * 当找到数字时应当作出的应对
     */
    @Override
    public void findDigit() {
        //calculator.clearInput();
        // TODO: 2016/10/27 bug1： 按键顺序 ：1+2==3
        calculator.setOperandTwo();
        calculator.setOperatorBefore();//注意 如果用户随意修改运算符时，这里要记下用户的最终运算符
        calculator.setCurrentState(calculator.getOperandTwoWithoutDot());
    }

    /**
     * 当找到运算符时应该作出的应对
     * @param operator
     */
    @Override
    public void findOperator(char operator) {
        // TODO: 2016/9/20  
        calculator.changeOperator(operator);
        calculator.clearInput();
        calculator.setCurrentState(calculator.getOperatorState());
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

    /**
     * 当按下等号时作出的应对
     * 此时操作符刚刚输入完毕
     * 操作数1 = 操作数2
     * 若按下等于号
     * 则直接运算 op1 op op2
     * 而且 根据Windows计算器
     * Result框不显示 结果直接显示在input框中
     * 作为新的Operand1
     */
    @Override
    public void onEqualPressed() {
        //为什么需要再设置一次二者相等？
        //因为中途可能输入了OP2，但是CE了，所以这里要重置
        calculator.setOperandTwoWithOperandOne();
        if (calculator.calculate(calculator.getOperator())){
            calculator.clearResult();
            calculator.clearInput();
            calculator.showUltimateResultInInput();
            Log.d("test",String.valueOf(calculator.getOperandOne()));
            calculator.setCurrentState(calculator.getSingleOperandDoneWithSelfState());
        }else {
            Log.d("--Error--",this.toString());
            calculator.showError();
            calculator.setCurrentState(calculator.getErrorState());
        }
    }

    @Override
    public String toString() {
        return "OperatorState{" +
                "calculator=" + calculator +
                '}';
    }
}
