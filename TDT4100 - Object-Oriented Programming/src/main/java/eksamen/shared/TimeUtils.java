package eksamen.shared;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * A utility class that provides some methods for working with time.
 */
public class TimeUtils {

    /**
     * Compares the given {@link LocalDateTime datetimes} and checks if the second
     * time is within 24 hours after the first
     * 
     * @param time1 The first {@link LocalDateTime}
     * @param time2 The second {@link LocalDateTime}
     * @return true if time2 is within 24 hours after time1
     */
    public static boolean isWithin24Hours(LocalDateTime time1, LocalDateTime time2) {
        Duration duration = Duration.between(time1, time2);
        return duration.toHours() >= 0 && duration.toHours() <= 24;
    }

}