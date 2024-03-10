package com.rrobinvip.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Extends the ObjectMapper to provide a customized Jackson ObjectMapper configured for specific serialization and deserialization behaviors.
 * This custom JacksonObjectMapper is tailored for handling Java 8 Date and Time API (java.time) objects with predefined formats for date, time, and datetime.
 * It disables the failure on encountering unknown properties during deserialization to enhance compatibility and flexibility in handling JSON data with potentially evolving schemas.
 * Custom serializers and deserializers for LocalDate, LocalTime, and LocalDateTime are registered to ensure that these types are processed correctly according to the specified formats.
 *
 * Default formats:
 * - Date format: "yyyy-MM-dd"
 * - DateTime format: "yyyy-MM-dd HH:mm" (previously "yyyy-MM-dd HH:mm:ss" but now without seconds)
 * - Time format: "HH:mm:ss"
 *
 * This setup allows for a more resilient and adaptable JSON processing environment, particularly useful for applications that require robust date and time handling across different layers of the application architecture.
 */
public class JacksonObjectMapper extends ObjectMapper {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    //public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public JacksonObjectMapper() {
        super();
        // No exceptions with unknown properties
        this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Compatibility handling for non-existent properties during deserialization.
        this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

        // For the registration feature module, for example, you can add custom serializers and deserializers.
        this.registerModule(simpleModule);
    }
}
