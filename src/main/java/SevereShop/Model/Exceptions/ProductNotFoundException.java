package SevereShop.Model.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {

    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
