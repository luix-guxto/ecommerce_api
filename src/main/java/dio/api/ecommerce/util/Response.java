package dio.api.ecommerce.util;

public class Response {

    public static String getResponse(String message, int status) {
        return "{ \"message\": \"" + message + "\", \"status\": " + status + " }";
    }
}
