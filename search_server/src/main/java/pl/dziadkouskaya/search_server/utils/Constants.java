package pl.dziadkouskaya.search_server.utils;

public class Constants {
    //exception messages
    public static final String RESOURCE_NOT_FOUND = "Entity wasn't found.";
    public static final String ERROR_SERVER_INTERNAL = "Internal server error.";
    public static final String ERROR_ACCESS_DENIED = "Access is denied";
    public static final String ERROR_BAD_REQUEST = "Bad request.";
    public static final String RESOURCE_EXISTED = "Entity with such params existed";
    public static final String FIRM_NAME_NOT_NULL = "Firm name can't be null";
    public static final String FIRM_NAME_NOT_EMPTY = "Firm name can't be empty";
    public static final String SHOP_CONNECTION_IS_NOT_AVAILABLE = "Shop %s is not available now, exception: {}.";
    public static final String ERROR_PARSING_PRICE = "The product's prices are not found for the seller %s.";
    public static final String ERROR_PARSING_TITLE ="The product's titles are not found for the seller %s.";
    public static final String ERROR_PARSING_NO_ONE_OR_MAIN_PRIORITY ="The titles %s for the seller %s contains no ONE or MAIN priority title.";
    public static final String ERROR_SELLER_EXISTED = "Seller with name %s or searchUrl %s existed";
    public static final String ERROR_SELLER_NOT_EXIST = "Seller with id %s doesn't exist";


    // Utils
    public static final String SPACE = " ";
    public static final String SEMICOLON = ";";

    //parsing
    public static final String DIV_STARTS_WITH = "div[class^='%s']";
    public static final String CUSTOM_TAG_STARTS_WITH = "%s[class^='%s']";
    public static final String DIV_CONTAINS = "div[class*='%s']";
    public static final String LINK = "a";
    public static final String HREF_ATTRIBUTE = "href";

    // default values
    public static final int PRODUCT_SEARCH_DEFAULT_WAIT = 10000;
    public static final int DISTANCE_THRESHOLD = 5;

}
