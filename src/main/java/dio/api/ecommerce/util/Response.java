package dio.api.ecommerce.util;

public class Response {

    public static String getResponse(String message, int status) {
        message = message.replace("\"", "'");
        return "{ \"message\": \"" + message + "\", \"status\": " + status + " }";
    }
}
