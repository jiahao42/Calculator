package com.james.calculator;

import com.james.calculator.States.ErrorState;
import com.james.calculator.States.InitState;
import com.james.calculator.States.OperandOneWithDot;
import com.james.calculator.States.OperandOneWithoutDot;
import com.james.calculator.States.OperandTwoWithDot;
import com.james.calculator.States.OperandTwoWithoutDot;
import com.james.calculator.States.OperatorState;
import com.james.calculator.States.ReplaceableOperatorState;
import com.james.calculator.States.SingleOperand;
import com.james.calculator.States.State;

public class Calculator {
    State errorState;
    State initState;
    State operandOneWithDot;
    State operandOneWithoutDot;
    State operandTwoWithDot;
    State operandTwoWithoutDot;
    State operatorState;
    State replaceableOperatorState;
    State singleOperand;

    State state = initState;
    float operandOne = 0f;
    float operandTwo = 0f;
    float result = 0f;

    public Calculator() {
        this.errorState = new ErrorState(this);
        this.initState = new InitState(this);
        this.operandOneWithDot = new OperandOneWithDot(this);
        this.operandOneWithoutDot = new OperandOneWithoutDot(this);
        this.operandTwoWithDot = new OperandTwoWithDot(this);
        this.operandTwoWithoutDot = new OperandTwoWithoutDot(this);
        this.operatorState = new OperatorState(this);
        this.replaceableOperatorState = new ReplaceableOperatorState(this);
        this.singleOperand = new SingleOperand(this);
    }

    public void findDot() {
        state.findDot();
    }

    public void findDigit() {
        state.findDigit();
    }

    public void findOperator() {
        state.findOperator();
    }

    public void onCEPressed() {
        state.onCEPressed();
    }

    public void onCPressed() {
        state.onCPressed();
    }
    public void onEqualPressed(){
        state.onEqualPressed();
    }

}
