package SevereShop.Model.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {

    }
    public OrderNotFoundException(String message) {
        super(message);
    }
}
