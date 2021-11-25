package br.com.uol.pagseguro.plugpag.persistence.converters;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

public class DateConverter {
  @TypeConverter
  public static Date toDate(Long value) {
    return (value == null) ? null : new Date(value.longValue());
  }
  
  @TypeConverter
  public static Long fromDate(Date date) {
    return (date == null) ? null : Long.valueOf(date.getTime());
  }
}
