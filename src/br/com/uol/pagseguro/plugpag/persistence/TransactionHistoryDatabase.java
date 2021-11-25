package br.com.uol.pagseguro.plugpag.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import br.com.uol.pagseguro.plugpag.persistence.converters.DateConverter;

@Database(entities = {TransactionHistory.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class TransactionHistoryDatabase extends RoomDatabase {
  private static volatile TransactionHistoryDatabase INSTANCE;
  
  public abstract TransactionHistoryDao transactionHistoryDao();
  
  public static TransactionHistoryDatabase getDatabase(Context context) {
    if (INSTANCE == null)
      synchronized (TransactionHistoryDatabase.class) {
        if (INSTANCE == null)
          INSTANCE = buildDatabase(context); 
      }  
    return INSTANCE;
  }
  
  private static TransactionHistoryDatabase buildDatabase(Context context) {
    return (TransactionHistoryDatabase)Room.databaseBuilder(context.getApplicationContext(), TransactionHistoryDatabase.class, "transaction_history.db")
      
      .fallbackToDestructiveMigration()
      .build();
  }
}
