package dio.api.ecommerce.util;

import java.util.Arrays;
import java.util.List;

public class Status {

    public final static String ACTIVE = "ACTIVE";
    public final static String INACTIVE = "INACTIVE";
    public final static String DRAFT = "DRAFT";
    private final static String[] status = {ACTIVE, INACTIVE, DRAFT};
    public final static List<String> STATUS = Arrays.asList(status);
}
