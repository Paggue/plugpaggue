/*     */ package br.com.uol.pagseguro.plugpag.persistence;
/*     */ 
/*     */ import android.arch.persistence.db.SupportSQLiteQuery;
/*     */ import android.arch.persistence.db.SupportSQLiteStatement;
/*     */ import android.arch.persistence.room.EntityInsertionAdapter;
/*     */ import android.arch.persistence.room.RoomDatabase;
/*     */ import android.arch.persistence.room.RoomSQLiteQuery;
/*     */ import android.arch.persistence.room.SharedSQLiteStatement;
/*     */ import android.database.Cursor;
/*     */ import br.com.uol.pagseguro.plugpag.persistence.converters.DateConverter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransactionHistoryDao_Impl
/*     */   extends TransactionHistoryDao
/*     */ {
/*     */   private final RoomDatabase __db;
/*     */   private final EntityInsertionAdapter __insertionAdapterOfTransactionHistory;
/*     */   private final SharedSQLiteStatement __preparedStmtOfDeleteTransactions;
/*     */   
/*     */   public TransactionHistoryDao_Impl(RoomDatabase __db) {
/*  27 */     this.__db = __db;
/*  28 */     this.__insertionAdapterOfTransactionHistory = new EntityInsertionAdapter<TransactionHistory>(__db)
/*     */       {
/*     */         public String createQuery() {
/*  31 */           return "INSERT OR REPLACE INTO `transaction_history`(`id`,`result`,`message`,`error_code`,`transaction_code`,`date`,`time`,`host_nsu`,`card_brand`,`bin`,`holder`,`user_reference`,`terminal_serial_number`,`transaction_id`,`amount`,`available_balance`,`card_application`,`label`,`holder_name`,`extended_holder_name`,`network_preference`,`reader_model`,`nsu`,`auto_code`,`installments`,`original_amount`,`date_transaction`,`app_identification`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */         }
/*     */ 
/*     */         
/*     */         public void bind(SupportSQLiteStatement stmt, TransactionHistory value) {
/*  36 */           stmt.bindLong(1, value.getId());
/*  37 */           stmt.bindLong(2, value.getResult());
/*  38 */           if (value.getMessage() == null) {
/*  39 */             stmt.bindNull(3);
/*     */           } else {
/*  41 */             stmt.bindString(3, value.getMessage());
/*     */           } 
/*  43 */           if (value.getErrorCode() == null) {
/*  44 */             stmt.bindNull(4);
/*     */           } else {
/*  46 */             stmt.bindString(4, value.getErrorCode());
/*     */           } 
/*  48 */           if (value.getTransactionCode() == null) {
/*  49 */             stmt.bindNull(5);
/*     */           } else {
/*  51 */             stmt.bindString(5, value.getTransactionCode());
/*     */           } 
/*  53 */           if (value.getDate() == null) {
/*  54 */             stmt.bindNull(6);
/*     */           } else {
/*  56 */             stmt.bindString(6, value.getDate());
/*     */           } 
/*  58 */           if (value.getTime() == null) {
/*  59 */             stmt.bindNull(7);
/*     */           } else {
/*  61 */             stmt.bindString(7, value.getTime());
/*     */           } 
/*  63 */           if (value.getHostNsu() == null) {
/*  64 */             stmt.bindNull(8);
/*     */           } else {
/*  66 */             stmt.bindString(8, value.getHostNsu());
/*     */           } 
/*  68 */           if (value.getCardBrand() == null) {
/*  69 */             stmt.bindNull(9);
/*     */           } else {
/*  71 */             stmt.bindString(9, value.getCardBrand());
/*     */           } 
/*  73 */           if (value.getBin() == null) {
/*  74 */             stmt.bindNull(10);
/*     */           } else {
/*  76 */             stmt.bindString(10, value.getBin());
/*     */           } 
/*  78 */           if (value.getHolder() == null) {
/*  79 */             stmt.bindNull(11);
/*     */           } else {
/*  81 */             stmt.bindString(11, value.getHolder());
/*     */           } 
/*  83 */           if (value.getUserReference() == null) {
/*  84 */             stmt.bindNull(12);
/*     */           } else {
/*  86 */             stmt.bindString(12, value.getUserReference());
/*     */           } 
/*  88 */           if (value.getTerminalSerialNumber() == null) {
/*  89 */             stmt.bindNull(13);
/*     */           } else {
/*  91 */             stmt.bindString(13, value.getTerminalSerialNumber());
/*     */           } 
/*  93 */           if (value.getTransactionId() == null) {
/*  94 */             stmt.bindNull(14);
/*     */           } else {
/*  96 */             stmt.bindString(14, value.getTransactionId());
/*     */           } 
/*  98 */           if (value.getAmount() == null) {
/*  99 */             stmt.bindNull(15);
/*     */           } else {
/* 101 */             stmt.bindString(15, value.getAmount());
/*     */           } 
/* 103 */           if (value.getAvailableBalance() == null) {
/* 104 */             stmt.bindNull(16);
/*     */           } else {
/* 106 */             stmt.bindString(16, value.getAvailableBalance());
/*     */           } 
/* 108 */           if (value.getCardApplication() == null) {
/* 109 */             stmt.bindNull(17);
/*     */           } else {
/* 111 */             stmt.bindString(17, value.getCardApplication());
/*     */           } 
/* 113 */           if (value.getLabel() == null) {
/* 114 */             stmt.bindNull(18);
/*     */           } else {
/* 116 */             stmt.bindString(18, value.getLabel());
/*     */           } 
/* 118 */           if (value.getHolderName() == null) {
/* 119 */             stmt.bindNull(19);
/*     */           } else {
/* 121 */             stmt.bindString(19, value.getHolderName());
/*     */           } 
/* 123 */           if (value.getExtendedHolderName() == null) {
/* 124 */             stmt.bindNull(20);
/*     */           } else {
/* 126 */             stmt.bindString(20, value.getExtendedHolderName());
/*     */           } 
/* 128 */           stmt.bindLong(21, value.getNetworkPreference());
/* 129 */           if (value.getReaderModel() == null) {
/* 130 */             stmt.bindNull(22);
/*     */           } else {
/* 132 */             stmt.bindString(22, value.getReaderModel());
/*     */           } 
/* 134 */           if (value.getNsu() == null) {
/* 135 */             stmt.bindNull(23);
/*     */           } else {
/* 137 */             stmt.bindString(23, value.getNsu());
/*     */           } 
/* 139 */           if (value.getAutoCode() == null) {
/* 140 */             stmt.bindNull(24);
/*     */           } else {
/* 142 */             stmt.bindString(24, value.getAutoCode());
/*     */           } 
/* 144 */           stmt.bindLong(25, value.getInstallments());
/* 145 */           stmt.bindLong(26, value.getOriginalAmount());
/*     */           
/* 147 */           Long _tmp = DateConverter.fromDate(value.getDateTransaction());
/* 148 */           if (_tmp == null) {
/* 149 */             stmt.bindNull(27);
/*     */           } else {
/* 151 */             stmt.bindLong(27, _tmp.longValue());
/*     */           } 
/* 153 */           if (value.getAppIdentification() == null) {
/* 154 */             stmt.bindNull(28);
/*     */           } else {
/* 156 */             stmt.bindString(28, value.getAppIdentification());
/*     */           } 
/*     */         }
/*     */       };
/* 160 */     this.__preparedStmtOfDeleteTransactions = new SharedSQLiteStatement(__db)
/*     */       {
/*     */         public String createQuery() {
/* 163 */           String _query = "DELETE FROM transaction_history WHERE date_transaction NOT IN (SELECT DISTINCT date_transaction FROM transaction_history ORDER BY date_transaction DESC LIMIT ?)";
/* 164 */           return "DELETE FROM transaction_history WHERE date_transaction NOT IN (SELECT DISTINCT date_transaction FROM transaction_history ORDER BY date_transaction DESC LIMIT ?)";
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   Long saveTransaction(TransactionHistory transactionHistory) {
/* 171 */     this.__db.beginTransaction();
/*     */     try {
/* 173 */       long _result = this.__insertionAdapterOfTransactionHistory.insertAndReturnId(transactionHistory);
/* 174 */       this.__db.setTransactionSuccessful();
/* 175 */       return Long.valueOf(_result);
/*     */     } finally {
/* 177 */       this.__db.endTransaction();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int deleteTransactions(int days) {
/* 183 */     SupportSQLiteStatement _stmt = this.__preparedStmtOfDeleteTransactions.acquire();
/* 184 */     this.__db.beginTransaction();
/*     */     try {
/* 186 */       int _argIndex = 1;
/* 187 */       _stmt.bindLong(_argIndex, days);
/* 188 */       int _result = _stmt.executeUpdateDelete();
/* 189 */       this.__db.setTransactionSuccessful();
/* 190 */       return _result;
/*     */     } finally {
/* 192 */       this.__db.endTransaction();
/* 193 */       this.__preparedStmtOfDeleteTransactions.release(_stmt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   List<TransactionHistory> getTransactions() {
/* 199 */     String _sql = "SELECT * FROM transaction_history";
/* 200 */     RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM transaction_history", 0);
/* 201 */     Cursor _cursor = this.__db.query((SupportSQLiteQuery)_statement);
/*     */     try {
/* 203 */       int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
/* 204 */       int _cursorIndexOfResult = _cursor.getColumnIndexOrThrow("result");
/* 205 */       int _cursorIndexOfMessage = _cursor.getColumnIndexOrThrow("message");
/* 206 */       int _cursorIndexOfErrorCode = _cursor.getColumnIndexOrThrow("error_code");
/* 207 */       int _cursorIndexOfTransactionCode = _cursor.getColumnIndexOrThrow("transaction_code");
/* 208 */       int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
/* 209 */       int _cursorIndexOfTime = _cursor.getColumnIndexOrThrow("time");
/* 210 */       int _cursorIndexOfHostNsu = _cursor.getColumnIndexOrThrow("host_nsu");
/* 211 */       int _cursorIndexOfCardBrand = _cursor.getColumnIndexOrThrow("card_brand");
/* 212 */       int _cursorIndexOfBin = _cursor.getColumnIndexOrThrow("bin");
/* 213 */       int _cursorIndexOfHolder = _cursor.getColumnIndexOrThrow("holder");
/* 214 */       int _cursorIndexOfUserReference = _cursor.getColumnIndexOrThrow("user_reference");
/* 215 */       int _cursorIndexOfTerminalSerialNumber = _cursor.getColumnIndexOrThrow("terminal_serial_number");
/* 216 */       int _cursorIndexOfTransactionId = _cursor.getColumnIndexOrThrow("transaction_id");
/* 217 */       int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
/* 218 */       int _cursorIndexOfAvailableBalance = _cursor.getColumnIndexOrThrow("available_balance");
/* 219 */       int _cursorIndexOfCardApplication = _cursor.getColumnIndexOrThrow("card_application");
/* 220 */       int _cursorIndexOfLabel = _cursor.getColumnIndexOrThrow("label");
/* 221 */       int _cursorIndexOfHolderName = _cursor.getColumnIndexOrThrow("holder_name");
/* 222 */       int _cursorIndexOfExtendedHolderName = _cursor.getColumnIndexOrThrow("extended_holder_name");
/* 223 */       int _cursorIndexOfNetworkPreference = _cursor.getColumnIndexOrThrow("network_preference");
/* 224 */       int _cursorIndexOfReaderModel = _cursor.getColumnIndexOrThrow("reader_model");
/* 225 */       int _cursorIndexOfNsu = _cursor.getColumnIndexOrThrow("nsu");
/* 226 */       int _cursorIndexOfAutoCode = _cursor.getColumnIndexOrThrow("auto_code");
/* 227 */       int _cursorIndexOfInstallments = _cursor.getColumnIndexOrThrow("installments");
/* 228 */       int _cursorIndexOfOriginalAmount = _cursor.getColumnIndexOrThrow("original_amount");
/* 229 */       int _cursorIndexOfDateTransaction = _cursor.getColumnIndexOrThrow("date_transaction");
/* 230 */       int _cursorIndexOfAppIdentification = _cursor.getColumnIndexOrThrow("app_identification");
/* 231 */       List<TransactionHistory> _result = new ArrayList<>(_cursor.getCount());
/* 232 */       while (_cursor.moveToNext()) {
/*     */         Long _tmp;
/* 234 */         TransactionHistory _item = new TransactionHistory();
/*     */         
/* 236 */         int _tmpId = _cursor.getInt(_cursorIndexOfId);
/* 237 */         _item.setId(_tmpId);
/*     */         
/* 239 */         int _tmpResult = _cursor.getInt(_cursorIndexOfResult);
/* 240 */         _item.setResult(_tmpResult);
/*     */         
/* 242 */         String _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
/* 243 */         _item.setMessage(_tmpMessage);
/*     */         
/* 245 */         String _tmpErrorCode = _cursor.getString(_cursorIndexOfErrorCode);
/* 246 */         _item.setErrorCode(_tmpErrorCode);
/*     */         
/* 248 */         String _tmpTransactionCode = _cursor.getString(_cursorIndexOfTransactionCode);
/* 249 */         _item.setTransactionCode(_tmpTransactionCode);
/*     */         
/* 251 */         String _tmpDate = _cursor.getString(_cursorIndexOfDate);
/* 252 */         _item.setDate(_tmpDate);
/*     */         
/* 254 */         String _tmpTime = _cursor.getString(_cursorIndexOfTime);
/* 255 */         _item.setTime(_tmpTime);
/*     */         
/* 257 */         String _tmpHostNsu = _cursor.getString(_cursorIndexOfHostNsu);
/* 258 */         _item.setHostNsu(_tmpHostNsu);
/*     */         
/* 260 */         String _tmpCardBrand = _cursor.getString(_cursorIndexOfCardBrand);
/* 261 */         _item.setCardBrand(_tmpCardBrand);
/*     */         
/* 263 */         String _tmpBin = _cursor.getString(_cursorIndexOfBin);
/* 264 */         _item.setBin(_tmpBin);
/*     */         
/* 266 */         String _tmpHolder = _cursor.getString(_cursorIndexOfHolder);
/* 267 */         _item.setHolder(_tmpHolder);
/*     */         
/* 269 */         String _tmpUserReference = _cursor.getString(_cursorIndexOfUserReference);
/* 270 */         _item.setUserReference(_tmpUserReference);
/*     */         
/* 272 */         String _tmpTerminalSerialNumber = _cursor.getString(_cursorIndexOfTerminalSerialNumber);
/* 273 */         _item.setTerminalSerialNumber(_tmpTerminalSerialNumber);
/*     */         
/* 275 */         String _tmpTransactionId = _cursor.getString(_cursorIndexOfTransactionId);
/* 276 */         _item.setTransactionId(_tmpTransactionId);
/*     */         
/* 278 */         String _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
/* 279 */         _item.setAmount(_tmpAmount);
/*     */         
/* 281 */         String _tmpAvailableBalance = _cursor.getString(_cursorIndexOfAvailableBalance);
/* 282 */         _item.setAvailableBalance(_tmpAvailableBalance);
/*     */         
/* 284 */         String _tmpCardApplication = _cursor.getString(_cursorIndexOfCardApplication);
/* 285 */         _item.setCardApplication(_tmpCardApplication);
/*     */         
/* 287 */         String _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
/* 288 */         _item.setLabel(_tmpLabel);
/*     */         
/* 290 */         String _tmpHolderName = _cursor.getString(_cursorIndexOfHolderName);
/* 291 */         _item.setHolderName(_tmpHolderName);
/*     */         
/* 293 */         String _tmpExtendedHolderName = _cursor.getString(_cursorIndexOfExtendedHolderName);
/* 294 */         _item.setExtendedHolderName(_tmpExtendedHolderName);
/*     */         
/* 296 */         int _tmpNetworkPreference = _cursor.getInt(_cursorIndexOfNetworkPreference);
/* 297 */         _item.setNetworkPreference(_tmpNetworkPreference);
/*     */         
/* 299 */         String _tmpReaderModel = _cursor.getString(_cursorIndexOfReaderModel);
/* 300 */         _item.setReaderModel(_tmpReaderModel);
/*     */         
/* 302 */         String _tmpNsu = _cursor.getString(_cursorIndexOfNsu);
/* 303 */         _item.setNsu(_tmpNsu);
/*     */         
/* 305 */         String _tmpAutoCode = _cursor.getString(_cursorIndexOfAutoCode);
/* 306 */         _item.setAutoCode(_tmpAutoCode);
/*     */         
/* 308 */         char _tmpInstallments = (char)_cursor.getInt(_cursorIndexOfInstallments);
/* 309 */         _item.setInstallments(_tmpInstallments);
/*     */         
/* 311 */         int _tmpOriginalAmount = _cursor.getInt(_cursorIndexOfOriginalAmount);
/* 312 */         _item.setOriginalAmount(_tmpOriginalAmount);
/*     */ 
/*     */         
/* 315 */         if (_cursor.isNull(_cursorIndexOfDateTransaction)) {
/* 316 */           _tmp = null;
/*     */         } else {
/* 318 */           _tmp = Long.valueOf(_cursor.getLong(_cursorIndexOfDateTransaction));
/*     */         } 
/* 320 */         Date _tmpDateTransaction = DateConverter.toDate(_tmp);
/* 321 */         _item.setDateTransaction(_tmpDateTransaction);
/*     */         
/* 323 */         String _tmpAppIdentification = _cursor.getString(_cursorIndexOfAppIdentification);
/* 324 */         _item.setAppIdentification(_tmpAppIdentification);
/* 325 */         _result.add(_item);
/*     */       } 
/* 327 */       return _result;
/*     */     } finally {
/* 329 */       _cursor.close();
/* 330 */       _statement.release();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/persistence/TransactionHistoryDao_Impl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */