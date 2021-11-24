/*    */ package br.com.uol.pagseguro.plugpag.persistence;
/*    */ 
/*    */ import android.arch.persistence.room.Database;
/*    */ import android.arch.persistence.room.Room;
/*    */ import android.arch.persistence.room.RoomDatabase;
/*    */ import android.arch.persistence.room.TypeConverters;
/*    */ import android.content.Context;
/*    */ import br.com.uol.pagseguro.plugpag.persistence.converters.DateConverter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Database(entities = {TransactionHistory.class}, version = 1)
/*    */ @TypeConverters({DateConverter.class})
/*    */ public abstract class TransactionHistoryDatabase
/*    */   extends RoomDatabase
/*    */ {
/*    */   private static volatile TransactionHistoryDatabase INSTANCE;
/*    */   
/*    */   public abstract TransactionHistoryDao transactionHistoryDao();
/*    */   
/*    */   public static TransactionHistoryDatabase getDatabase(Context context) {
/* 23 */     if (INSTANCE == null) {
/* 24 */       synchronized (TransactionHistoryDatabase.class) {
/* 25 */         if (INSTANCE == null) {
/* 26 */           INSTANCE = buildDatabase(context);
/*    */         }
/*    */       } 
/*    */     }
/*    */     
/* 31 */     return INSTANCE;
/*    */   }
/*    */   
/*    */   private static TransactionHistoryDatabase buildDatabase(Context context) {
/* 35 */     return (TransactionHistoryDatabase)Room.databaseBuilder(context.getApplicationContext(), TransactionHistoryDatabase.class, "transaction_history.db")
/*    */       
/* 37 */       .fallbackToDestructiveMigration()
/* 38 */       .build();
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/persistence/TransactionHistoryDatabase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */