package pl.dziadkouskaya.graphql.utils;

import pl.dziadkouskaya.graphql.entity.enums.Location;

public class Constants {
    public static final String PERCENT = "%";


    // codes
    public static int POSITIVE_PAYLOAD_CODE = 200;
    public static int NEGATIVE_PAYLOAD_CODE = 500;

    // payload messages
    public static final String POSITIVE_PAYLOAD_MESSAGE = "Product is successfully created";
    public static final String NEGATIVE_PAYLOAD_MESSAGE = "Product is not created";


    //exception messages
    public static final String RESOURCE_NOT_FOUND = "Entity wasn't found.";
    public static final String ERROR_SERVER_INTERNAL = "Internal server error.";
    public static final String ERROR_ACCESS_DENIED = "Access is denied";
    public static final String ERROR_BAD_REQUEST = "Bad request.";
    public static final String RESOURCE_EXESTED = "Entity with such params existed";
    public static final String FIRM_NAME_NOT_NULL = "Firm name can't be null";
    public static final String FIRM_NAME_NOT_EMPTY = "Firm name can't be empty";


    //default values
    public static Location DEFAULT_LOCATION = Location.EN;

    //ActiveMQ queues
    public static final String ACTIVEMQ_QUEUE_SELLER_CREATED = "seller.created.queue";

}
