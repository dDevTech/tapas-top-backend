package com.mycompany.myapp.service;

public class AddressAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AddressAlreadyUsedException() {
        super("Address is already in use!");
    }
}
