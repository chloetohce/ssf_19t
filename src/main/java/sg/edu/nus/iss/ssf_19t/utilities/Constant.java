package sg.edu.nus.iss.ssf_19t.utilities;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class Constant {
    public static final String REDIS_TEMPLATE = "redisTemplate";
    public static final String KEY_TODO = "todos";

    public static final long DATE_TODAY = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    
}
