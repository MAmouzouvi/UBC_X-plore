package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Account testAccount;


    @BeforeEach
    public void setUp(){
        testAccount = new Account();
        testAccount.deposit(20000);
    }

    @Test
    public void testAccount(){
        assertEquals(20000,testAccount.balance);
    }

    @Test
    public void testPayFees(){
        testAccount.deposit(10000);
        assertEquals(30000,testAccount.getBalance());
        testAccount.deposit(5000);
        assertEquals(35000,testAccount.getBalance());
    }

    @Test
    public void testPayFeesInsufficientAmount(){
        assertFalse(testAccount.payFee(20001));
    }

    @Test
    public void testPayFeesSufficientAmount(){

        //Paying amount less than balance (with remainder)
        assertTrue(testAccount.payFee(10000));
        assertEquals(10000,testAccount.getBalance());

        //Paying exact amount without remainder
        assertTrue(testAccount.payFee(10000));
        assertEquals(0,testAccount.getBalance());
    }

    @Test
    public void testGetBalance(){
        assertEquals(20000,testAccount.balance);
        testAccount.deposit(5000);
        assertEquals(25000,testAccount.getBalance());
        testAccount.payFee(2000);
        assertEquals(23000,testAccount.balance);

    }
}
