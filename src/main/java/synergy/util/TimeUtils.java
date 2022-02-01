package synergy.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final ZoneOffset ZONE_OFFSET_UKRAINE = ZoneOffset.of("+02:00");

    public LocalTime getDifferences(LocalDateTime from,
                                    LocalDateTime to, ZoneOffset offset) {
        if (offset == null) {
            offset = ZONE_OFFSET_UKRAINE;
        }
        long epochSecond = to.toEpochSecond(offset) - from.toEpochSecond(offset);
        return Instant.ofEpochSecond(epochSecond)
                .atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
