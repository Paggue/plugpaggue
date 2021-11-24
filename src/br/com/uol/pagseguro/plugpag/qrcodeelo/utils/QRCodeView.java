/*    */ package br.com.uol.pagseguro.plugpag.qrcodeelo.utils;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.support.v7.widget.AppCompatImageView;
/*    */ import android.util.AttributeSet;
/*    */ import com.google.zxing.BarcodeFormat;
/*    */ import com.google.zxing.MultiFormatWriter;
/*    */ import com.google.zxing.common.BitMatrix;
/*    */ 
/*    */ public class QRCodeView
/*    */   extends AppCompatImageView
/*    */ {
/*    */   public QRCodeView(Context context) {
/* 15 */     super(context);
/*    */   }
/*    */   
/*    */   public QRCodeView(Context context, AttributeSet attrs) {
/* 19 */     super(context, attrs);
/*    */   }
/*    */   
/*    */   public QRCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
/* 23 */     super(context, attrs, defStyleAttr);
/*    */   }
/*    */   
/*    */   public void setQRCode(String code) {
/* 27 */     float size = (getResources().getDisplayMetrics()).widthPixels * 0.8F;
/* 28 */     Bitmap bitmap = generateQRBipMap(code, (int)size);
/* 29 */     setImageBitmap(bitmap);
/*    */   }
/*    */   
/*    */   private Bitmap generateQRBipMap(String code, int size) {
/*    */     try {
/* 34 */       BitMatrix qrCodeMatrix = (new MultiFormatWriter()).encode(code, BarcodeFormat.QR_CODE, size, size, null);
/* 35 */       int[] pixels = new int[size * size];
/*    */       
/* 37 */       for (int y = 0; y < size; y++) {
/* 38 */         for (int x = 0; x < size; x++) {
/* 39 */           int index = y * size + x;
/* 40 */           pixels[index] = qrCodeMatrix.get(x, y) ? -16777216 : -1;
/*    */         } 
/*    */       } 
/*    */       
/* 44 */       Bitmap qrCode = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
/* 45 */       qrCode.setPixels(pixels, 0, size, 0, 0, size, size);
/*    */       
/* 47 */       return qrCode;
/* 48 */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/qrcodeelo/utils/QRCodeView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */