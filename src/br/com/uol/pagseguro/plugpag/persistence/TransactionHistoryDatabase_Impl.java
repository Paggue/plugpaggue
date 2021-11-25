package br.com.uol.pagseguro.plugpag.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.util.TableInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TransactionHistoryDatabase_Impl extends TransactionHistoryDatabase {
  private volatile TransactionHistoryDao _transactionHistoryDao;
  
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    RoomOpenHelper roomOpenHelper = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
          public void createAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `result` INTEGER NOT NULL, `message` TEXT, `error_code` TEXT, `transaction_code` TEXT, `date` TEXT, `time` TEXT, `host_nsu` TEXT, `card_brand` TEXT, `bin` TEXT, `holder` TEXT, `user_reference` TEXT, `terminal_serial_number` TEXT, `transaction_id` TEXT, `amount` TEXT, `available_balance` TEXT, `card_application` TEXT, `label` TEXT, `holder_name` TEXT, `extended_holder_name` TEXT, `network_preference` INTEGER NOT NULL, `reader_model` TEXT, `nsu` TEXT, `auto_code` TEXT, `installments` INTEGER NOT NULL, `original_amount` INTEGER NOT NULL, `date_transaction` INTEGER, `app_identification` TEXT)");
            _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fdd530a2577b9f9c347085029845e01d\")");
          }
          
          public void dropAllTables(SupportSQLiteDatabase _db) {
            _db.execSQL("DROP TABLE IF EXISTS `transaction_history`");
          }
          
          protected void onCreate(SupportSQLiteDatabase _db) {
            if (TransactionHistoryDatabase_Impl.this.mCallbacks != null)
              for (int _i = 0, _size = TransactionHistoryDatabase_Impl.this.mCallbacks.size(); _i < _size; _i++)
                ((RoomDatabase.Callback)TransactionHistoryDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);  
          }
          
          public void onOpen(SupportSQLiteDatabase _db) {
            TransactionHistoryDatabase_Impl.this.mDatabase = _db;
            TransactionHistoryDatabase_Impl.this.internalInitInvalidationTracker(_db);
            if (TransactionHistoryDatabase_Impl.this.mCallbacks != null)
              for (int _i = 0, _size = TransactionHistoryDatabase_Impl.this.mCallbacks.size(); _i < _size; _i++)
                ((RoomDatabase.Callback)TransactionHistoryDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);  
          }
          
          protected void validateMigration(SupportSQLiteDatabase _db) {
            HashMap<String, TableInfo.Column> _columnsTransactionHistory = new HashMap<>(28);
            _columnsTransactionHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
            _columnsTransactionHistory.put("result", new TableInfo.Column("result", "INTEGER", true, 0));
            _columnsTransactionHistory.put("message", new TableInfo.Column("message", "TEXT", false, 0));
            _columnsTransactionHistory.put("error_code", new TableInfo.Column("error_code", "TEXT", false, 0));
            _columnsTransactionHistory.put("transaction_code", new TableInfo.Column("transaction_code", "TEXT", false, 0));
            _columnsTransactionHistory.put("date", new TableInfo.Column("date", "TEXT", false, 0));
            _columnsTransactionHistory.put("time", new TableInfo.Column("time", "TEXT", false, 0));
            _columnsTransactionHistory.put("host_nsu", new TableInfo.Column("host_nsu", "TEXT", false, 0));
            _columnsTransactionHistory.put("card_brand", new TableInfo.Column("card_brand", "TEXT", false, 0));
            _columnsTransactionHistory.put("bin", new TableInfo.Column("bin", "TEXT", false, 0));
            _columnsTransactionHistory.put("holder", new TableInfo.Column("holder", "TEXT", false, 0));
            _columnsTransactionHistory.put("user_reference", new TableInfo.Column("user_reference", "TEXT", false, 0));
            _columnsTransactionHistory.put("terminal_serial_number", new TableInfo.Column("terminal_serial_number", "TEXT", false, 0));
            _columnsTransactionHistory.put("transaction_id", new TableInfo.Column("transaction_id", "TEXT", false, 0));
            _columnsTransactionHistory.put("amount", new TableInfo.Column("amount", "TEXT", false, 0));
            _columnsTransactionHistory.put("available_balance", new TableInfo.Column("available_balance", "TEXT", false, 0));
            _columnsTransactionHistory.put("card_application", new TableInfo.Column("card_application", "TEXT", false, 0));
            _columnsTransactionHistory.put("label", new TableInfo.Column("label", "TEXT", false, 0));
            _columnsTransactionHistory.put("holder_name", new TableInfo.Column("holder_name", "TEXT", false, 0));
            _columnsTransactionHistory.put("extended_holder_name", new TableInfo.Column("extended_holder_name", "TEXT", false, 0));
            _columnsTransactionHistory.put("network_preference", new TableInfo.Column("network_preference", "INTEGER", true, 0));
            _columnsTransactionHistory.put("reader_model", new TableInfo.Column("reader_model", "TEXT", false, 0));
            _columnsTransactionHistory.put("nsu", new TableInfo.Column("nsu", "TEXT", false, 0));
            _columnsTransactionHistory.put("auto_code", new TableInfo.Column("auto_code", "TEXT", false, 0));
            _columnsTransactionHistory.put("installments", new TableInfo.Column("installments", "INTEGER", true, 0));
            _columnsTransactionHistory.put("original_amount", new TableInfo.Column("original_amount", "INTEGER", true, 0));
            _columnsTransactionHistory.put("date_transaction", new TableInfo.Column("date_transaction", "INTEGER", false, 0));
            _columnsTransactionHistory.put("app_identification", new TableInfo.Column("app_identification", "TEXT", false, 0));
            HashSet<TableInfo.ForeignKey> _foreignKeysTransactionHistory = new HashSet<>(0);
            HashSet<TableInfo.Index> _indicesTransactionHistory = new HashSet<>(0);
            TableInfo _infoTransactionHistory = new TableInfo("transaction_history", _columnsTransactionHistory, _foreignKeysTransactionHistory, _indicesTransactionHistory);
            TableInfo _existingTransactionHistory = TableInfo.read(_db, "transaction_history");
            if (!_infoTransactionHistory.equals(_existingTransactionHistory))
              throw new IllegalStateException("Migration didn't properly handle transaction_history(br.com.uol.pagseguro.plugpag.persistence.TransactionHistory).\n Expected:\n" + _infoTransactionHistory + "\n Found:\n" + _existingTransactionHistory); 
          }
        }"fdd530a2577b9f9c347085029845e01d", "09c7b647cea6d9af92cae74939bb3f7c");
    SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback((SupportSQLiteOpenHelper.Callback)roomOpenHelper).build();
    SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }
  
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, new String[] { "transaction_history" });
  }
  
  public void clearAllTables() {
    assertNotMainThread();
    SupportSQLiteDatabase _db = getOpenHelper().getWritableDatabase();
    try {
      beginTransaction();
      _db.execSQL("DELETE FROM `transaction_history`");
      setTransactionSuccessful();
    } finally {
      endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction())
        _db.execSQL("VACUUM"); 
    } 
  }
  
  public TransactionHistoryDao transactionHistoryDao() {
    if (this._transactionHistoryDao != null)
      return this._transactionHistoryDao; 
    synchronized (this) {
      if (this._transactionHistoryDao == null)
        this._transactionHistoryDao = new TransactionHistoryDao_Impl(this); 
      return this._transactionHistoryDao;
    } 
  }
}
