package com.late.myapplication.model;

public interface UsesZipcodes {
    /**
     * Performs a zipcode validity test
     *
     * @param zip Zipcode to be tested
     * @throws InvalidZipCodeException Thrown if zipcode is invalid
     */
    default void checkZip(int zip) throws InvalidZipCodeException {
        if (zip > 99999 || zip < 0) {
            throw new InvalidZipCodeException();
        }
    }
}
