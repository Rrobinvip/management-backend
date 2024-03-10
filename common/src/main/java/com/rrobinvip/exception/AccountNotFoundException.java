package com.rrobinvip.exception;

/**
 * Account is not exist exception
 */
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}
