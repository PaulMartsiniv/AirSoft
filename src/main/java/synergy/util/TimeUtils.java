package synergy.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class TimeUtils {
    public LocalTime getDifferences(LocalDateTime from,
                                    LocalDateTime to, ZoneOffset offset) {
        if (offset == null) {
            offset = ZoneOffset.of("+02:00");
        }
        long epochSecond = to.toEpochSecond(offset) - from.toEpochSecond(offset);
        return Instant.ofEpochSecond(epochSecond)
                .atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
