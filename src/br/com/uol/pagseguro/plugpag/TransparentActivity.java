package br.com.uol.pagseguro.plugpag;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class TransparentActivity extends PlugPagActivity {
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transparent);
  }
}
