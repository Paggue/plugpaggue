/*     */ package br.com.uol.pagseguro.plugpag.persistence;
/*     */ 
/*     */ import android.arch.persistence.db.SupportSQLiteDatabase;
/*     */ import android.arch.persistence.db.SupportSQLiteOpenHelper;
/*     */ import android.arch.persistence.room.DatabaseConfiguration;
/*     */ import android.arch.persistence.room.InvalidationTracker;
/*     */ import android.arch.persistence.room.RoomDatabase;
/*     */ import android.arch.persistence.room.RoomOpenHelper;
/*     */ import android.arch.persistence.room.util.TableInfo;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransactionHistoryDatabase_Impl
/*     */   extends TransactionHistoryDatabase
/*     */ {
/*     */   private volatile TransactionHistoryDao _transactionHistoryDao;
/*     */   
/*     */   protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
/*  28 */     RoomOpenHelper roomOpenHelper = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1)
/*     */         {
/*     */           public void createAllTables(SupportSQLiteDatabase _db) {
/*  31 */             _db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `result` INTEGER NOT NULL, `message` TEXT, `error_code` TEXT, `transaction_code` TEXT, `date` TEXT, `time` TEXT, `host_nsu` TEXT, `card_brand` TEXT, `bin` TEXT, `holder` TEXT, `user_reference` TEXT, `terminal_serial_number` TEXT, `transaction_id` TEXT, `amount` TEXT, `available_balance` TEXT, `card_application` TEXT, `label` TEXT, `holder_name` TEXT, `extended_holder_name` TEXT, `network_preference` INTEGER NOT NULL, `reader_model` TEXT, `nsu` TEXT, `auto_code` TEXT, `installments` INTEGER NOT NULL, `original_amount` INTEGER NOT NULL, `date_transaction` INTEGER, `app_identification` TEXT)");
/*  32 */             _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
/*  33 */             _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fdd530a2577b9f9c347085029845e01d\")");
/*     */           }
/*     */ 
/*     */           
/*     */           public void dropAllTables(SupportSQLiteDatabase _db) {
/*  38 */             _db.execSQL("DROP TABLE IF EXISTS `transaction_history`");
/*     */           }
/*     */ 
/*     */           
/*     */           protected void onCreate(SupportSQLiteDatabase _db) {
/*  43 */             if (TransactionHistoryDatabase_Impl.this.mCallbacks != null) {
/*  44 */               for (int _i = 0, _size = TransactionHistoryDatabase_Impl.this.mCallbacks.size(); _i < _size; _i++) {
/*  45 */                 ((RoomDatabase.Callback)TransactionHistoryDatabase_Impl.this.mCallbacks.get(_i)).onCreate(_db);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           public void onOpen(SupportSQLiteDatabase _db) {
/*  52 */             TransactionHistoryDatabase_Impl.this.mDatabase = _db;
/*  53 */             TransactionHistoryDatabase_Impl.this.internalInitInvalidationTracker(_db);
/*  54 */             if (TransactionHistoryDatabase_Impl.this.mCallbacks != null) {
/*  55 */               for (int _i = 0, _size = TransactionHistoryDatabase_Impl.this.mCallbacks.size(); _i < _size; _i++) {
/*  56 */                 ((RoomDatabase.Callback)TransactionHistoryDatabase_Impl.this.mCallbacks.get(_i)).onOpen(_db);
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           protected void validateMigration(SupportSQLiteDatabase _db) {
/*  63 */             HashMap<String, TableInfo.Column> _columnsTransactionHistory = new HashMap<>(28);
/*  64 */             _columnsTransactionHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
/*  65 */             _columnsTransactionHistory.put("result", new TableInfo.Column("result", "INTEGER", true, 0));
/*  66 */             _columnsTransactionHistory.put("message", new TableInfo.Column("message", "TEXT", false, 0));
/*  67 */             _columnsTransactionHistory.put("error_code", new TableInfo.Column("error_code", "TEXT", false, 0));
/*  68 */             _columnsTransactionHistory.put("transaction_code", new TableInfo.Column("transaction_code", "TEXT", false, 0));
/*  69 */             _columnsTransactionHistory.put("date", new TableInfo.Column("date", "TEXT", false, 0));
/*  70 */             _columnsTransactionHistory.put("time", new TableInfo.Column("time", "TEXT", false, 0));
/*  71 */             _columnsTransactionHistory.put("host_nsu", new TableInfo.Column("host_nsu", "TEXT", false, 0));
/*  72 */             _columnsTransactionHistory.put("card_brand", new TableInfo.Column("card_brand", "TEXT", false, 0));
/*  73 */             _columnsTransactionHistory.put("bin", new TableInfo.Column("bin", "TEXT", false, 0));
/*  74 */             _columnsTransactionHistory.put("holder", new TableInfo.Column("holder", "TEXT", false, 0));
/*  75 */             _columnsTransactionHistory.put("user_reference", new TableInfo.Column("user_reference", "TEXT", false, 0));
/*  76 */             _columnsTransactionHistory.put("terminal_serial_number", new TableInfo.Column("terminal_serial_number", "TEXT", false, 0));
/*  77 */             _columnsTransactionHistory.put("transaction_id", new TableInfo.Column("transaction_id", "TEXT", false, 0));
/*  78 */             _columnsTransactionHistory.put("amount", new TableInfo.Column("amount", "TEXT", false, 0));
/*  79 */             _columnsTransactionHistory.put("available_balance", new TableInfo.Column("available_balance", "TEXT", false, 0));
/*  80 */             _columnsTransactionHistory.put("card_application", new TableInfo.Column("card_application", "TEXT", false, 0));
/*  81 */             _columnsTransactionHistory.put("label", new TableInfo.Column("label", "TEXT", false, 0));
/*  82 */             _columnsTransactionHistory.put("holder_name", new TableInfo.Column("holder_name", "TEXT", false, 0));
/*  83 */             _columnsTransactionHistory.put("extended_holder_name", new TableInfo.Column("extended_holder_name", "TEXT", false, 0));
/*  84 */             _columnsTransactionHistory.put("network_preference", new TableInfo.Column("network_preference", "INTEGER", true, 0));
/*  85 */             _columnsTransactionHistory.put("reader_model", new TableInfo.Column("reader_model", "TEXT", false, 0));
/*  86 */             _columnsTransactionHistory.put("nsu", new TableInfo.Column("nsu", "TEXT", false, 0));
/*  87 */             _columnsTransactionHistory.put("auto_code", new TableInfo.Column("auto_code", "TEXT", false, 0));
/*  88 */             _columnsTransactionHistory.put("installments", new TableInfo.Column("installments", "INTEGER", true, 0));
/*  89 */             _columnsTransactionHistory.put("original_amount", new TableInfo.Column("original_amount", "INTEGER", true, 0));
/*  90 */             _columnsTransactionHistory.put("date_transaction", new TableInfo.Column("date_transaction", "INTEGER", false, 0));
/*  91 */             _columnsTransactionHistory.put("app_identification", new TableInfo.Column("app_identification", "TEXT", false, 0));
/*  92 */             HashSet<TableInfo.ForeignKey> _foreignKeysTransactionHistory = new HashSet<>(0);
/*  93 */             HashSet<TableInfo.Index> _indicesTransactionHistory = new HashSet<>(0);
/*  94 */             TableInfo _infoTransactionHistory = new TableInfo("transaction_history", _columnsTransactionHistory, _foreignKeysTransactionHistory, _indicesTransactionHistory);
/*  95 */             TableInfo _existingTransactionHistory = TableInfo.read(_db, "transaction_history");
/*  96 */             if (!_infoTransactionHistory.equals(_existingTransactionHistory)) {
/*  97 */               throw new IllegalStateException("Migration didn't properly handle transaction_history(br.com.uol.pagseguro.plugpag.persistence.TransactionHistory).\n Expected:\n" + _infoTransactionHistory + "\n Found:\n" + _existingTransactionHistory);
/*     */             }
/*     */           }
/*     */         }"fdd530a2577b9f9c347085029845e01d", "09c7b647cea6d9af92cae74939bb3f7c");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback((SupportSQLiteOpenHelper.Callback)roomOpenHelper).build();
/* 107 */     SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
/* 108 */     return _helper;
/*     */   }
/*     */ 
/*     */   
/*     */   protected InvalidationTracker createInvalidationTracker() {
/* 113 */     return new InvalidationTracker(this, new String[] { "transaction_history" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAllTables() {
/* 118 */     assertNotMainThread();
/* 119 */     SupportSQLiteDatabase _db = getOpenHelper().getWritableDatabase();
/*     */     try {
/* 121 */       beginTransaction();
/* 122 */       _db.execSQL("DELETE FROM `transaction_history`");
/* 123 */       setTransactionSuccessful();
/*     */     } finally {
/* 125 */       endTransaction();
/* 126 */       _db.query("PRAGMA wal_checkpoint(FULL)").close();
/* 127 */       if (!_db.inTransaction()) {
/* 128 */         _db.execSQL("VACUUM");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TransactionHistoryDao transactionHistoryDao() {
/* 135 */     if (this._transactionHistoryDao != null) {
/* 136 */       return this._transactionHistoryDao;
/*     */     }
/* 138 */     synchronized (this) {
/* 139 */       if (this._transactionHistoryDao == null) {
/* 140 */         this._transactionHistoryDao = new TransactionHistoryDao_Impl(this);
/*     */       }
/* 142 */       return this._transactionHistoryDao;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/persistence/TransactionHistoryDatabase_Impl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */