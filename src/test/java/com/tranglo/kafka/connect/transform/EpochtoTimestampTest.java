package com.tranglo.kafka.connect.transform;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpochtoTimestampTest {
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private static final Calendar EPOCH;
    private static final Calendar TIME;
    private static final Calendar DATE;
    private static final Calendar DATE_PLUS_TIME;
    private static final long DATE_PLUS_TIME_UNIX;
    private static final String STRING_DATE_FMT = "yyyy-MM-dd HH:mm:ss.SS";
    private static final String DATE_PLUS_TIME_STRING;

    private final EpochtoTimestamp<SourceRecord> xformKey = new EpochtoTimestamp.Key<>();
    private final EpochtoTimestamp<SourceRecord> xformValue = new EpochtoTimestamp.Value<>();

    static {
        EPOCH = GregorianCalendar.getInstance(UTC);
        EPOCH.setTimeInMillis(0L);

        TIME = GregorianCalendar.getInstance(UTC);
        TIME.setTimeInMillis(0L);
        TIME.add(Calendar.MILLISECOND, 1234);

        DATE = GregorianCalendar.getInstance(UTC);
        DATE.setTimeInMillis(0L);
        DATE.set(1970, Calendar.JANUARY, 1, 0, 0, 0);
        DATE.add(Calendar.DATE, 1);

        DATE_PLUS_TIME = GregorianCalendar.getInstance(UTC);
        DATE_PLUS_TIME.setTimeInMillis(0L);
        DATE_PLUS_TIME.add(Calendar.DATE, 1);
        DATE_PLUS_TIME.add(Calendar.MILLISECOND, 1234);

        DATE_PLUS_TIME_UNIX = DATE_PLUS_TIME.getTime().getTime();
        DATE_PLUS_TIME_STRING = "1970-01-02 00:00:01.234";

    }

    @AfterEach
    public void teardown() {
        xformKey.close();
        xformValue.close();
    }


    @Test
    public void UnixTimestampConverts() {
        Map<String, String> config = new HashMap<>();
        config.put(EpochtoTimestamp.FIELD_CONFIG, "non_nullable_date");
        xformValue.configure(config);

        Schema payload_schema = SchemaBuilder.struct()
                .field("some_other_value", Schema.STRING_SCHEMA)
                .field("value_boolean_value", Schema.BOOLEAN_SCHEMA)
                .field("non_nullable_date", Schema.INT64_SCHEMA)
                .build();

        Struct payload_values = new Struct(payload_schema);

        payload_values.put("some_other_value", "jeff");
        payload_values.put("value_boolean_value", true);
        payload_values.put("non_nullable_date", DATE_PLUS_TIME_UNIX);

        SourceRecord transformed = xformValue.apply(new SourceRecord(null, null, "topic", 0, payload_schema, payload_values));

        assertEquals(DATE_PLUS_TIME_STRING, ((Struct) transformed.value()).get("non_nullable_date"));
        assertEquals(true, ((Struct) transformed.value()).get("value_boolean_value"));
        assertEquals("jeff", ((Struct) transformed.value()).get("some_other_value"));

    }

    @Test
    public void NullTimestampisDropped() {
        Map<String, String> config = new HashMap<>();
        config.put(EpochtoTimestamp.FIELD_CONFIG, "nullable_date");
        xformValue.configure(config);

        Schema payload_schema = SchemaBuilder.struct()
                .field("some_other_value", Schema.STRING_SCHEMA)
                .field("value_boolean_value", Schema.BOOLEAN_SCHEMA)
                .field("nullable_date", Schema.STRING_SCHEMA)
                .build();

        Struct payload_values = new Struct(payload_schema);

        payload_values.put("some_other_value", "jeff");
        payload_values.put("value_boolean_value", true);
        payload_values.put("nullable_date", "null");

        SourceRecord transformed = xformValue.apply(new SourceRecord(null, null, "topic", 0, payload_schema, payload_values));

        assertEquals("jeff", ((Struct) transformed.value()).get("some_other_value"));
        assertEquals(true, ((Struct) transformed.value()).get("value_boolean_value"));
        assertEquals("null", ((Struct) transformed.value()).get("nullable_date"));
    }

//    @Test
//    public void testDateTime2() {
//        Map<String, String> config = new HashMap<>();
//        config.put(EpochtoTimestamp.FIELD_CONFIG, "datetime2_value");
//        xformValue.configure(config);
//
//        Schema payload_schema = SchemaBuilder.struct()
//                .field("datetime2_value", Schema.INT64_SCHEMA)
//                .build();
//
//        Struct payload_values = new Struct(payload_schema);
//
//        long datetime2_value = 1552395600769451400L;
//
//        payload_values.put("datetime2_value", datetime2_value);
//
//        SourceRecord transformed = xformValue.apply(new SourceRecord(null, null, "topic", 0, payload_schema, payload_values));
//
//        System.out.println(transformed.value());
//        assertEquals("2019-03-12 13:00:00.769", ((Struct) transformed.value()).get("datetime2_value"));
//
//    }

    @Test
    public void testDateTime3() {
//        long ns = Long.parseLong("1473724800000000000");
////        long datetime2_value = 1552395600769451400L;
//
//        System.out.println(convertNS(ns));

        Date now = new Date();
        System.out.println(now);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        String formattedDate = format.format(now);

        // print that date
        System.out.println(formattedDate);
    }

    private String convertNS(long ns) {
        long ms = TimeUnit.NANOSECONDS.toMillis(ns);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        format.setTimeZone(UTC);

        return format.format(ms);

    }

    @Test
    public void testDateTime4() {
//        1382659200000000000 => Nanoseconds (19 digits)
//        1483232054547470 => Microseconds (16 digits)
//        1590969600021 => Milliseconds (13 digits)
//        14025 =>   2008-05-26
//        17749 =>   Aug 06 2018
//        18386 =>   2020-05-04
//        2922

        long unix_seconds = Long.parseLong("2922");

// ==========================================================================================================
        Instant instant;
        int l = String.valueOf(unix_seconds).length();

        if(l == 19) {
            instant = getInstantFromNanos(unix_seconds);
            System.out.println(instant);

            Date myDate = Date.from(instant);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
            formatter.setTimeZone(UTC);

            String formattedDate = formatter.format(myDate);
            System.out.println(formattedDate);
        } else if (l == 16) {
            instant = getInstantFromMicros(unix_seconds);
            System.out.println(instant);

            Date myDate = Date.from(instant);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");
            formatter.setTimeZone(UTC);

            String formattedDate = formatter.format(myDate);
            System.out.println(formattedDate);
        } else if(l == 4) {
//            LocalDate lt = LocalDate.now();
//            int toEpochDay = (int) lt.toEpochDay();
//            System.out.println(toEpochDay);
            // ==========================================================================
//            LocalDate date = Conversions.toLocalDate(value);
//            new java.util.Date (epoch*1000)

            LocalDate local_date = LocalDate.ofEpochDay(unix_seconds);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = local_date.format(formatter);

            System.out.println(formattedString);
        }  else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            formatter.setTimeZone(UTC);

            String formattedDate = formatter.format(unix_seconds);
            System.out.println(formattedDate);
        }
// ==========================================================================================================

//        Instant instant = getInstantFromMicros(unix_seconds);
//        Instant instant = getInstantFromNanos(unix_seconds);

//        System.out.println(instant);

//        Date myDate = Date.from(instant);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");
//        formatter.setTimeZone(UTC);
//        String formattedDate = formatter.format(myDate);
//        System.out.println(formattedDate);
    }

    static Instant getInstantFromMicros(Long microsSinceEpoch) {
        return Instant.ofEpochSecond(TimeUnit.MICROSECONDS.toSeconds(microsSinceEpoch), TimeUnit.MICROSECONDS.toNanos(Math.floorMod(microsSinceEpoch, TimeUnit.SECONDS.toMicros(1))));
    }

    static Instant getInstantFromNanos(Long nanosSinceEpoch) {
        return Instant.ofEpochSecond(0L, nanosSinceEpoch);
    }
}
