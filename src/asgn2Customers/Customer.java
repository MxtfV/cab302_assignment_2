package asgn2Customers;


import asgn2Exceptions.CustomerException;

/**
 * An abstract class to represent a customer at the Pizza Palace restaurant. The
 * Customer class is used as a base class of PickUpCustomer,
 * DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses
 * overwrites the abstract method getDeliveryDistance. A description of the
 * class's fields and their constraints is provided in Section 5.2 of the
 * Assignment Specification.
 * 
 * @author Daniel Gilchrist
 */
public abstract class Customer {
    private String name;
    private String mobileNumber;
    private int locationX;
    private int locationY;
    private String type;

    /**
     * This class represents a customer of the Pizza Palace restaurant. A
     * detailed description of the class's fields and parameters is provided in
     * the Assignment Specification, in particular in Section 5.2. A
     * CustomerException is thrown if the any of the constraints listed in
     * Section 5.2 of the Assignment Specification are violated.
     * 
     * <P>
     * PRE: True
     * <P>
     * POST: All field values are set
     * 
     * @param name
     *            - The Customer's name
     * @param mobileNumber
     *            - The customer mobile number
     * @param locationX
     *            - The customer x location relative to the Pizza Palace
     *            Restaurant measured in units of 'blocks'
     * @param locationY
     *            - The customer y location relative to the Pizza Palace
     *            Restaurant measured in units of 'blocks'
     * @param type
     *            - A human understandable description of this Customer type
     * @throws CustomerException
     *             if the name is empty, white space, or longer than 20
     *             characters, and/or if mobile number is not 10 digits starting
     *             by 0, and/or locationX and locationY are less than -10 or
     *             greater than 10
     * 
     */
    public Customer(String name, String mobileNumber, int locationX, int locationY, String type)
            throws CustomerException {
        // check if name is valid
        if (name.length() >= 1 && name.length() <= 20 && name.trim().length() > 0) {
            this.name = name;
        } else {
            throw new CustomerException(
                    "Customer name is invalid. (Length must be between 1 and 20 (inclusive) and cannot be only whitespaces");
        }

        // check if mobile number is valid
        if (mobileNumber.length() == 10 && mobileNumber.charAt(0) == '0') {
            this.mobileNumber = mobileNumber;
        } else {
            throw new CustomerException("Mobile number is invalid. (Length must be 10 and must begin with 0)");
        }

        // check that location is valid
        if (locationX > 10 || locationX < -10 || locationY > 10 || locationY < -10) {
            throw new CustomerException("Customer is located too far away from the restaurant to deliver.");
        } else {
            if (type.equals("Pick Up") && (locationX != 0 || locationY != 0)) {
                throw new CustomerException(
                        "Pick Up coordinates are invalid. (Must be 0 for both x and y coordinates)");
            } else if (type.equals("Driver Delivery") && locationX == 0 && locationY == 0) {
                throw new CustomerException(
                        "Driver Delivery coordinates are invalid. (Can't be 0 for both x any y coordinates)");
            } else if (type.equals("Drone Delivery") && locationX == 0 && locationY == 0) {
                throw new CustomerException(
                        "Drone Delivery coordinates are invalid. (Can't be 0 for both x and y coordinates)");
            } else {
                this.locationX = locationX;
                this.locationY = locationY;
            }
        }

        this.type = type;
    }

    /**
     * Returns the Customer's name.
     * 
     * @return The Customer's name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the Customer's mobile number.
     * 
     * @return The Customer's mobile number.
     */
    public final String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Returns a human understandable description of the Customer's type. The
     * valid alternatives are listed in Section 5.2 of the Assignment
     * Specification.
     * 
     * @return A human understandable description of the Customer's type.
     */
    public final String getCustomerType() {
        return type;
    }

    /**
     * Returns the Customer's X location which is the number of blocks East or
     * West that the Customer is located relative to the Pizza Palace
     * restaurant.
     * 
     * @return The Customer's X location
     */
    public final int getLocationX() {
        return locationX;
    }

    /**
     * Returns the Customer's Y location which is the number of blocks North or
     * South that the Customer is located relative to the Pizza Palace
     * restaurant.
     * 
     * @return The Customer's Y location
     */
    public final int getLocationY() {
        return locationY;
    }

    /**
     * An abstract method that returns the distance between the Customer and the
     * restaurant depending on the mode of delivery.
     * 
     * @return The distance between the restaurant and the Customer depending on
     *         the mode of delivery.
     */
    public abstract double getDeliveryDistance();

    /**
     * Compares *this* Customer object with an instance of an *other* Customer
     * object and returns true if if the two objects are equivalent, that is, if
     * the values exposed by public methods are equal. You do not need to test
     * this method.
     * 
     * @return true if *this* Customer object and the *other* Customer object
     *         have the same values returned for
     *         getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
     */
    @Override
    public boolean equals(Object other) {
        Customer otherCustomer = (Customer) other;

        return ((this.getName().equals(otherCustomer.getName()))
                && (this.getMobileNumber().equals(otherCustomer.getMobileNumber()))
                && (this.getLocationX() == otherCustomer.getLocationX())
                && (this.getLocationY() == otherCustomer.getLocationY())
                && (this.getCustomerType().equals(otherCustomer.getCustomerType())));
    }

}
