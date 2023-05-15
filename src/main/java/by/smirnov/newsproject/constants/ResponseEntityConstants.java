package by.smirnov.newsproject.constants;

public interface ResponseEntityConstants {

    String ERROR_KEY = "Error Message";
    String NOT_VERIFIED_MESSAGE = "You did non pass email verification or Your email verification code is wrong";
    String ALREADY_DELETED_MESSAGE = "Object by this ID is already deleted";
    String FORBIDDEN_MESSAGE = "Authorization check failed";
    String NOT_FOUND_MESSAGE = "Wrong ID, object not found";
    String BAD_LOGIN_MESSAGE = "Incorrect credentials!";
    String USER_NOT_FOUND = "User not found!";
    String ORDER_STATUS = "Order Status";
    String GOOD_STATUS = "Good Status";
    String DELETED_STATUS = "isDeleted";
    String VERIFIED = "Verification successfull!";
    String HARD_DELETED = "Object by this ID successfully deleted";
}
