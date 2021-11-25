package br.com.uol.pagseguro.plugpag.qrcodeelo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeView extends AppCompatImageView {
  public QRCodeView(Context context) {
    super(context);
  }
  
  public QRCodeView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public QRCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  
  public void setQRCode(String code) {
    float size = (getResources().getDisplayMetrics()).widthPixels * 0.8F;
    Bitmap bitmap = generateQRBipMap(code, (int)size);
    setImageBitmap(bitmap);
  }
  
  private Bitmap generateQRBipMap(String code, int size) {
    try {
      BitMatrix qrCodeMatrix = (new MultiFormatWriter()).encode(code, BarcodeFormat.QR_CODE, size, size, null);
      int[] pixels = new int[size * size];
      for (int y = 0; y < size; y++) {
        for (int x = 0; x < size; x++) {
          int index = y * size + x;
          pixels[index] = qrCodeMatrix.get(x, y) ? -16777216 : -1;
        } 
      } 
      Bitmap qrCode = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
      qrCode.setPixels(pixels, 0, size, 0, 0, size, size);
      return qrCode;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
}
