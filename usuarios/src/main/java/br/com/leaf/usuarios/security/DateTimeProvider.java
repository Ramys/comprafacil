package br.com.leaf.usuarios.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public interface DateTimeProvider {

    LocalDateTime dateTime();

    LocalDate date();

    LocalTime time();

    Date toDate(LocalDateTime localDateTime);

    LocalDateTime fromDate(Date date);

}
