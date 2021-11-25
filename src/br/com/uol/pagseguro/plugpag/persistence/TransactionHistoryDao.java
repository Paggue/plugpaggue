package br.com.uol.pagseguro.plugpag.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public abstract class TransactionHistoryDao {
  @Insert(onConflict = 1)
  abstract Long saveTransaction(TransactionHistory paramTransactionHistory);
  
  @Query("SELECT * FROM transaction_history")
  abstract List<TransactionHistory> getTransactions();
  
  @Query("DELETE FROM transaction_history WHERE date_transaction NOT IN (SELECT DISTINCT date_transaction FROM transaction_history ORDER BY date_transaction DESC LIMIT :days)")
  abstract int deleteTransactions(int paramInt);
}
