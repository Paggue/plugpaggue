package br.com.uol.pagseguro.plugpag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface IPlugPag extends IPlugPagTransactionState, IPlugPagRequirements {
  public static final int RET_OK = 0;
  
  public static final int INSTALLMENT_TYPE_A_VISTA = 1;
  
  public static final int INSTALLMENT_TYPE_PARC_VENDEDOR = 2;
  
  public static final int INSTALLMENT_TYPE_PARC_COMPRADOR = 3;
  
  public static final int TYPE_CREDITO = 1;
  
  public static final int TYPE_DEBITO = 2;
  
  public static final int TYPE_VOUCHER = 3;
  
  public static final int TYPE_QRCODEELO = 4;
  
  public static final int PPTRS_QRCODE_SALE = 1;
  
  public static final int PPTRS_QRCODE_VOID = 2;
  
  public static final int ERROR_REQUIREMENTS_MISSING_PERMISSIONS = -3000;
  
  public static final int ERROR_REQUIREMENTS_ROOT_PERMISSION = -3001;
  
  public static final int ERROR_NO_ACTIVITY = -3300;
  
  public static final int REQUEST_CODE_AUTHENTICATION = 43981;
  
  public static final int MENUOPT_GENERIC = 0;
  
  public static final int MENUOPT_CARD_TRANS_TYPE = 1;
  
  public static final int MENUOPT_CARD_TRANS_MODE = 2;
  
  public static final int MENUOPT_NO_CVV = 3;
  
  public static final int MENUOPT_PPCOMP_DISP = 4;
  
  public static final int MENUOPT_MOB_OPTIONS = 5;
  
  public static final int MENUOPT_MOB_VALUES = 6;
  
  public static final int MENUOPT_CARD_TRANS_MOB_TYPE = 7;
  
  public static final int TEXT_GENERIC = 0;
  
  public static final int TEXT_PAN = 1;
  
  public static final int TEXT_BIN = 2;
  
  public static final int TEXT_HOLDER = 3;
  
  public static final int TEXT_CARD_CCV_MANDATORY = 4;
  
  public static final int TEXT_CARD_CCV = 5;
  
  public static final int TEXT_DDD_NUMBER = 6;
  
  public static final int TEXT_MOB_CONFIRMATION = 7;
  
  public static final int RECEIPT_MOB_SALE = 2;
  
  public static final int LEDS = 4;
  
  public static final String PPS_VERSION_PREV_KEY = "ppsVersion_prev";
  
  public static final String PLUGPAG_VERSION_PREV_KEY = "ppVersion_prev";
  
  public static final String PPS_VERSION_CUR_KEY = "ppsVersion_cur";
  
  public static final String PLUGPAG_VERSION_CUR_KEY = "ppVersion_cur";
  
  public static final String PPS_SP_VERSION_KEY = "PPS_CUR_VER";
  
  public static final String PPS_TRANSACTION_EVENT_TYPE = "PlugPagTransaction";
  
  public static final String PPS_TRANSACTION_EVENT_NAME = "CommunicationMetrics";
  
  public static final String PPS_CHANGE_CONNECTION_EVENT_TYPE = "PlugPagNetworkChangeConnection";
  
  public static final String PPS_CHANGE_CONNECTION_EVENT_NAME = "ChangeConnectionMetrics";
  
  public static final int ERR_BASE = -1000;
  
  public static final int BUFF_SIZE = -1001;
  
  public static final int NULL_PTR = -1002;
  
  public static final int POS_NOT_READY = -1003;
  
  public static final int TRANS_DENIED = -1004;
  
  public static final int DATA_INV_RESULT_MESSAGE = -1005;
  
  public static final int INV_AMOUNT_PARAM = -1006;
  
  public static final int INV_TOT_AMOUNT_PARAM = -1007;
  
  public static final int INV_USER_REF_PARAM = -1008;
  
  public static final int INV_TRS_RESULT_PARAM = -1009;
  
  public static final int DRIVER_NOT_FOUND = -1010;
  
  public static final int DRIVER_FUNCTION_ERROR = -1011;
  
  public static final int INV_FORMAT_AMOUNT_PARAM = -1012;
  
  public static final int INV_LEN_USER_REF_PARAM = -1013;
  
  public static final int INVALID_BUFFER = -1014;
  
  public static final int INV_APP_NAME_PARAM = -1015;
  
  public static final int INV_APP_VERSION_PARAM = -1016;
  
  public static final int APP_NAME_VERSION_NOT_SET = -1017;
  
  public static final int TRANS_NODATA = -1018;
  
  public static final int COMMUNICATION_ERROR = -1019;
  
  public static final int SHARE_MODE_NOT_ALLOWED = -1020;
  
  public static final int APPL_NOT_SUPORTED = -1021;
  
  public static final int INVALID_CARD = -1022;
  
  public static final int PSCINIT_EXECERR = -1023;
  
  public static final int INVALID_TABLES = -1024;
  
  public static final int PINPADERROR = -1025;
  
  public static final int INV_TRANS_TYPE_PARAM = -1026;
  
  public static final int INV_PARAM = -1027;
  
  public static final int OPERATION_ABORTED = -1028;
  
  public static final int MISSING_TOKEN = -1030;
  
  public static final int INVALID_AMOUNT = -1031;
  
  public static final int INVALID_INSTALLMENT = -1032;
  
  public static final int NO_PRINTER_DEVICE = -1040;
  
  public static final int DOING_TRANSACTION = -1047;
  
  public static final int JNI_EXIT_EXCEPTION = -3000;
  
  PlugPagReaderInfo getBcInfo();
  
  PlugPagCardInfo getCardInfo();
  
  PlugPagTransactionResult doPayment(@NonNull PlugPagPaymentData paramPlugPagPaymentData);
  
  PlugPagTransactionResult voidPayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
  
  PlugPagTransactionResult voidQRCodePayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
  
  PlugPagTransactionResult voidPayment();
  
  String[] calculateInstallments(String paramString);
  
  PlugPagInstallmentResult calculateInstallments(String paramString, int paramInt);
  
  PlugPagTransactionResult getLastApprovedTransaction();
  
  void setEventListener(PlugPagEventListener paramPlugPagEventListener);
  
  void transactionMetricEvent(TransactionMetric paramTransactionMetric);
  
  void updateTimeMetricEvent(UpdateTimeMetric paramUpdateTimeMetric);
  
  void setup(@NonNull Context paramContext);
  
  int initBTConnection(@NonNull PlugPagDevice paramPlugPagDevice);
  
  boolean isAuthenticated();
  
  void invalidateAuthentication();
  
  PlugPagUserData getUserData();
  
  PlugPagInitializationResult initializeAndActivatePinpad(PlugPagActivationData paramPlugPagActivationData);
  
  PlugPagInitializationResult deactivate(PlugPagActivationData paramPlugPagActivationData);
  
  int requestAuthentication(PlugPagAuthenticationListener paramPlugPagAuthenticationListener);
  
  PlugPagAbortResult abort();
  
  int setVersionName(@NonNull String paramString1, @NonNull String paramString2);
  
  PlugPagAppIdentification getAppIdentification();
  
  PlugPagReaderInfo getReaderInfo();
  
  boolean getMockState();
  
  PlugPagMockResult getMockResult();
  
  void setConnectionChangeSupport(boolean paramBoolean);
  
  void logEvent(PlugPagEventLoggerData paramPlugPagEventLoggerData);
  
  void setPlugPagCustomPrinterLayout(PlugPagCustomPrinterLayout paramPlugPagCustomPrinterLayout);
  
  boolean updateVersion(String paramString);
  
  void setMockState(boolean paramBoolean);
  
  void setSelectMock(int paramInt);
  
  void createMobTablePendings();
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/IPlugPag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */