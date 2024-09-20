package com.TDD.Testing.TDD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplicationDollar(){
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
            }

        @Test
        void testEqualityDollar() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        }

    @Test
    void testMultiplicationNaira(){
        Money five = Money.naira(5);
        assertEquals(Money.naira(10), five.times(2));
        assertEquals(Money.naira(15), five.times(3));
    }

    @Test
    void testEqualityNaira() {
        assertEquals(Money.naira(5), Money.naira(5));
        assertNotEquals(Money.naira(5), Money.naira(8));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("NAIRA", Money.naira(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addend);
    }

    @Test
    void testReducedCost() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReducedMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);

    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("NAIRA", "USD", 2);
        Money result = bank.reduce(Money.naira(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentifyRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals(1, new Bank().rate("NAIRA", "NAIRA"));

    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenNaira = Money.naira(10);
        Bank bank = new Bank();
        bank.addRate("NAIRA", "USD", 2);
        Money result = bank.reduce(fiveBucks.plus(tenNaira), "USD");
        assertEquals(Money.dollar(10), result);

    }

    @Test
    void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenNaira = Money.naira(10);
        Bank bank = new Bank();
        bank.addRate("NAIRA", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenNaira).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);

    }

    @Test
    void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenNaira = Money.naira(10);
        Bank bank = new Bank();
        bank.addRate("NAIRA", "USD", 2);
        Expression sum = new Sum(fiveBucks, tenNaira).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }
}
