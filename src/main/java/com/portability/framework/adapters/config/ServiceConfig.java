package com.portability.framework.adapters.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;
import com.portability.PortabilityApplication;
import com.portability.application.ports.out.KafkaService;
import com.portability.application.ports.out.PortabilityRepository;
import com.portability.application.services.PortabilityServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Configuration
@ComponentScan(basePackageClasses = PortabilityApplication.class)
public class ServiceConfig {

    public final String DATE_TIME_FORMATTER_WITH_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public final String DATE_TIME_FORMAT_YEAR_MONTH = "yyyyMM";
    public final String DATE_TIME_FORMATTER_YEAR_MONTH_DAY = "yyyy-MM-dd";

    @Bean
    PortabilityServiceImp portabilityService(PortabilityRepository repository, ModelMapper mapper, KafkaService kafkaService) {
        return new PortabilityServiceImp();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(
                (new JavaTimeModule())
                        .addDeserializer(LocalDateTime.class,
                                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_WITH_MILLISECONDS)))
                        .addSerializer(LocalDateTime.class,
                                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_WITH_MILLISECONDS)))
                        .addSerializer(YearMonth.class,
                                new YearMonthSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YEAR_MONTH)))
                        .addDeserializer(YearMonth.class,
                                new YearMonthDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_YEAR_MONTH)))
                        .addDeserializer(LocalDate.class,
                                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_YEAR_MONTH_DAY)))
                        .addSerializer(LocalDate.class,
                                new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_YEAR_MONTH_DAY)))
        );
        return mapper;
    }
}
