package br.com.leaf.usuarios.security.impl;

import br.com.leaf.usuarios.security.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateTimeProviderImpl implements DateTimeProvider {

    @Override
    public LocalDateTime dateTime() {
        return LocalDateTime.now();
    }

    @Override
    public LocalDate date() {
        return LocalDate.now();
    }

    @Override
    public LocalTime time() {
        return LocalTime.now();
    }

    @Override
    public Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime fromDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
