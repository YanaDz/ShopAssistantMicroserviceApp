package pl.dziadkouskaya.search_server.exception;

import pl.dziadkouskaya.search_server.entity.Seller;

public class SellerParsingException extends ApplicationException {
    public SellerParsingException() {
    }

    public SellerParsingException(String message) {
        super(message);
    }

    public SellerParsingException(String s, Seller seller) {

    }
}
