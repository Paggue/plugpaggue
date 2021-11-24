/*    */ package br.com.uol.pagseguro.plugpag.persistence.converters;
/*    */ 
/*    */ import android.arch.persistence.room.TypeConverter;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateConverter
/*    */ {
/*    */   @TypeConverter
/*    */   public static Date toDate(Long value) {
/* 10 */     return (value == null) ? null : new Date(value.longValue());
/*    */   }
/*    */   
/*    */   @TypeConverter
/*    */   public static Long fromDate(Date date) {
/* 15 */     return (date == null) ? null : Long.valueOf(date.getTime());
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/persistence/converters/DateConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */