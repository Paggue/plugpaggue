package br.com.uol.pagseguro.plugpag.persistence;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import br.com.uol.pagseguro.plugpag.persistence.converters.DateConverter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHistoryDao_Impl extends TransactionHistoryDao {
  private final RoomDatabase __db;
  
  private final EntityInsertionAdapter __insertionAdapterOfTransactionHistory;
  
  private final SharedSQLiteStatement __preparedStmtOfDeleteTransactions;
  
  public TransactionHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionHistory = new EntityInsertionAdapter<TransactionHistory>(__db) {
        public String createQuery() {
          return "INSERT OR REPLACE INTO `transaction_history`(`id`,`result`,`message`,`error_code`,`transaction_code`,`date`,`time`,`host_nsu`,`card_brand`,`bin`,`holder`,`user_reference`,`terminal_serial_number`,`transaction_id`,`amount`,`available_balance`,`card_application`,`label`,`holder_name`,`extended_holder_name`,`network_preference`,`reader_model`,`nsu`,`auto_code`,`installments`,`original_amount`,`date_transaction`,`app_identification`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
        
        public void bind(SupportSQLiteStatement stmt, TransactionHistory value) {
          stmt.bindLong(1, value.getId());
          stmt.bindLong(2, value.getResult());
          if (value.getMessage() == null) {
            stmt.bindNull(3);
          } else {
            stmt.bindString(3, value.getMessage());
          } 
          if (value.getErrorCode() == null) {
            stmt.bindNull(4);
          } else {
            stmt.bindString(4, value.getErrorCode());
          } 
          if (value.getTransactionCode() == null) {
            stmt.bindNull(5);
          } else {
            stmt.bindString(5, value.getTransactionCode());
          } 
          if (value.getDate() == null) {
            stmt.bindNull(6);
          } else {
            stmt.bindString(6, value.getDate());
          } 
          if (value.getTime() == null) {
            stmt.bindNull(7);
          } else {
            stmt.bindString(7, value.getTime());
          } 
          if (value.getHostNsu() == null) {
            stmt.bindNull(8);
          } else {
            stmt.bindString(8, value.getHostNsu());
          } 
          if (value.getCardBrand() == null) {
            stmt.bindNull(9);
          } else {
            stmt.bindString(9, value.getCardBrand());
          } 
          if (value.getBin() == null) {
            stmt.bindNull(10);
          } else {
            stmt.bindString(10, value.getBin());
          } 
          if (value.getHolder() == null) {
            stmt.bindNull(11);
          } else {
            stmt.bindString(11, value.getHolder());
          } 
          if (value.getUserReference() == null) {
            stmt.bindNull(12);
          } else {
            stmt.bindString(12, value.getUserReference());
          } 
          if (value.getTerminalSerialNumber() == null) {
            stmt.bindNull(13);
          } else {
            stmt.bindString(13, value.getTerminalSerialNumber());
          } 
          if (value.getTransactionId() == null) {
            stmt.bindNull(14);
          } else {
            stmt.bindString(14, value.getTransactionId());
          } 
          if (value.getAmount() == null) {
            stmt.bindNull(15);
          } else {
            stmt.bindString(15, value.getAmount());
          } 
          if (value.getAvailableBalance() == null) {
            stmt.bindNull(16);
          } else {
            stmt.bindString(16, value.getAvailableBalance());
          } 
          if (value.getCardApplication() == null) {
            stmt.bindNull(17);
          } else {
            stmt.bindString(17, value.getCardApplication());
          } 
          if (value.getLabel() == null) {
            stmt.bindNull(18);
          } else {
            stmt.bindString(18, value.getLabel());
          } 
          if (value.getHolderName() == null) {
            stmt.bindNull(19);
          } else {
            stmt.bindString(19, value.getHolderName());
          } 
          if (value.getExtendedHolderName() == null) {
            stmt.bindNull(20);
          } else {
            stmt.bindString(20, value.getExtendedHolderName());
          } 
          stmt.bindLong(21, value.getNetworkPreference());
          if (value.getReaderModel() == null) {
            stmt.bindNull(22);
          } else {
            stmt.bindString(22, value.getReaderModel());
          } 
          if (value.getNsu() == null) {
            stmt.bindNull(23);
          } else {
            stmt.bindString(23, value.getNsu());
          } 
          if (value.getAutoCode() == null) {
            stmt.bindNull(24);
          } else {
            stmt.bindString(24, value.getAutoCode());
          } 
          stmt.bindLong(25, value.getInstallments());
          stmt.bindLong(26, value.getOriginalAmount());
          Long _tmp = DateConverter.fromDate(value.getDateTransaction());
          if (_tmp == null) {
            stmt.bindNull(27);
          } else {
            stmt.bindLong(27, _tmp.longValue());
          } 
          if (value.getAppIdentification() == null) {
            stmt.bindNull(28);
          } else {
            stmt.bindString(28, value.getAppIdentification());
          } 
        }
      };
    this.__preparedStmtOfDeleteTransactions = new SharedSQLiteStatement(__db) {
        public String createQuery() {
          String _query = "DELETE FROM transaction_history WHERE date_transaction NOT IN (SELECT DISTINCT date_transaction FROM transaction_history ORDER BY date_transaction DESC LIMIT ?)";
          return "DELETE FROM transaction_history WHERE date_transaction NOT IN (SELECT DISTINCT date_transaction FROM transaction_history ORDER BY date_transaction DESC LIMIT ?)";
        }
      };
  }
  
  Long saveTransaction(TransactionHistory transactionHistory) {
    this.__db.beginTransaction();
    try {
      long _result = this.__insertionAdapterOfTransactionHistory.insertAndReturnId(transactionHistory);
      this.__db.setTransactionSuccessful();
      return Long.valueOf(_result);
    } finally {
      this.__db.endTransaction();
    } 
  }
  
  int deleteTransactions(int days) {
    SupportSQLiteStatement _stmt = this.__preparedStmtOfDeleteTransactions.acquire();
    this.__db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, days);
      int _result = _stmt.executeUpdateDelete();
      this.__db.setTransactionSuccessful();
      return _result;
    } finally {
      this.__db.endTransaction();
      this.__preparedStmtOfDeleteTransactions.release(_stmt);
    } 
  }
  
  List<TransactionHistory> getTransactions() {
    String _sql = "SELECT * FROM transaction_history";
    RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM transaction_history", 0);
    Cursor _cursor = this.__db.query((SupportSQLiteQuery)_statement);
    try {
      int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      int _cursorIndexOfResult = _cursor.getColumnIndexOrThrow("result");
      int _cursorIndexOfMessage = _cursor.getColumnIndexOrThrow("message");
      int _cursorIndexOfErrorCode = _cursor.getColumnIndexOrThrow("error_code");
      int _cursorIndexOfTransactionCode = _cursor.getColumnIndexOrThrow("transaction_code");
      int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      int _cursorIndexOfTime = _cursor.getColumnIndexOrThrow("time");
      int _cursorIndexOfHostNsu = _cursor.getColumnIndexOrThrow("host_nsu");
      int _cursorIndexOfCardBrand = _cursor.getColumnIndexOrThrow("card_brand");
      int _cursorIndexOfBin = _cursor.getColumnIndexOrThrow("bin");
      int _cursorIndexOfHolder = _cursor.getColumnIndexOrThrow("holder");
      int _cursorIndexOfUserReference = _cursor.getColumnIndexOrThrow("user_reference");
      int _cursorIndexOfTerminalSerialNumber = _cursor.getColumnIndexOrThrow("terminal_serial_number");
      int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transaction_id");
      int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
      int _cursorIndexOfAvailableBalance = _cursor.getColumnIndexOrThrow("available_balance");
      int _cursorIndexOfCardApplication = _cursor.getColumnIndexOrThrow("card_application");
      int _cursorIndexOfLabel = _cursor.getColumnIndexOrThrow("label");
      int _cursorIndexOfHolderName = _cursor.getColumnIndexOrThrow("holder_name");
      int _cursorIndexOfExtendedHolderName = _cursor.getColumnIndexOrThrow("extended_holder_name");
      int _cursorIndexOfNetworkPreference = _cursor.getColumnIndexOrThrow("network_preference");
      int _cursorIndexOfReaderModel = _cursor.getColumnIndexOrThrow("reader_model");
      int _cursorIndexOfNsu = _cursor.getColumnIndexOrThrow("nsu");
      int _cursorIndexOfAutoCode = _cursor.getColumnIndexOrThrow("auto_code");
      int _cursorIndexOfInstallments = _cursor.getColumnIndexOrThrow("installments");
      int _cursorIndexOfOriginalAmount = _cursor.getColumnIndexOrThrow("original_amount");
      int _cursorIndexOfDateTransaction = _cursor.getColumnIndexOrThrow("date_transaction");
      int _cursorIndexOfAppIdentification = _cursor.getColumnIndexOrThrow("app_identification");
      List<TransactionHistory> _result = new ArrayList<>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        Long _tmp;
        TransactionHistory _item = new TransactionHistory();
        int _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        int _tmpResult = _cursor.getInt(_cursorIndexOfResult);
        _item.setResult(_tmpResult);
        String _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        _item.setMessage(_tmpMessage);
        String _tmpErrorCode = _cursor.getString(_cursorIndexOfErrorCode);
        _item.setErrorCode(_tmpErrorCode);
        String _tmpTransactionCode = _cursor.getString(_cursorIndexOfTransactionCode);
        _item.setTransactionCode(_tmpTransactionCode);
        String _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        String _tmpTime = _cursor.getString(_cursorIndexOfTime);
        _item.setTime(_tmpTime);
        String _tmpHostNsu = _cursor.getString(_cursorIndexOfHostNsu);
        _item.setHostNsu(_tmpHostNsu);
        String _tmpCardBrand = _cursor.getString(_cursorIndexOfCardBrand);
        _item.setCardBrand(_tmpCardBrand);
        String _tmpBin = _cursor.getString(_cursorIndexOfBin);
        _item.setBin(_tmpBin);
        String _tmpHolder = _cursor.getString(_cursorIndexOfHolder);
        _item.setHolder(_tmpHolder);
        String _tmpUserReference = _cursor.getString(_cursorIndexOfUserReference);
        _item.setUserReference(_tmpUserReference);
        String _tmpTerminalSerialNumber = _cursor.getString(_cursorIndexOfTerminalSerialNumber);
        _item.setTerminalSerialNumber(_tmpTerminalSerialNumber);
        String _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
        _item.setTransactionId(_tmpTransactionId);
        String _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        String _tmpAvailableBalance = _cursor.getString(_cursorIndexOfAvailableBalance);
        _item.setAvailableBalance(_tmpAvailableBalance);
        String _tmpCardApplication = _cursor.getString(_cursorIndexOfCardApplication);
        _item.setCardApplication(_tmpCardApplication);
        String _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
        _item.setLabel(_tmpLabel);
        String _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
        _item.setHolderName(_tmpHolderName);
        String _tmpExtendedHolderName = _cursor.getString(_cursorIndexOfExtendedHolderName);
        _item.setExtendedHolderName(_tmpExtendedHolderName);
        int _tmpNetworkPreference = _cursor.getInt(_cursorIndexOfNetworkPreference);
        _item.setNetworkPreference(_tmpNetworkPreference);
        String _tmpReaderModel = _cursor.getString(_cursorIndexOfReaderModel);
        _item.setReaderModel(_tmpReaderModel);
        String _tmpNsu = _cursor.getString(_cursorIndexOfNsu);
        _item.setNsu(_tmpNsu);
        String _tmpAutoCode = _cursor.getString(_cursorIndexOfAutoCode);
        _item.setAutoCode(_tmpAutoCode);
        char _tmpInstallments = (char)_cursor.getInt(_cursorIndexOfInstallments);
        _item.setInstallments(_tmpInstallments);
        int _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
        _item.setOriginalAmount(_tmpOriginalAmount);
        if (_cursor.isNull(_cursorIndexOfDateTransaction)) {
          _tmp = null;
        } else {
          _tmp = Long.valueOf(_cursor.getLong(_cursorIndexOfDateTransaction));
        } 
        Date _tmpDateTransaction = DateConverter.toDate(_tmp);
        _item.setDateTransaction(_tmpDateTransaction);
        String _tmpAppIdentification = _cursor.getString(_cursorIndexOfAppIdentification);
        _item.setAppIdentification(_tmpAppIdentification);
        _result.add(_item);
      } 
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    } 
  }
}
