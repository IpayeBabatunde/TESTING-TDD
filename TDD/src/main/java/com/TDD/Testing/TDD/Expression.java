package com.TDD.Testing.TDD;

public interface Expression {
    Money reduce(Bank bank, String to);
    public Expression plus(Expression addend);

    Expression times(int multiplier);
}
