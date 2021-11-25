package br.com.uol.pagseguro.plugpag.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ObjectsCompat;

public class Pair<F, S> {
  @Nullable
  public final F first;
  
  @Nullable
  public final S second;
  
  public Pair(@Nullable F first, @Nullable S second) {
    this.first = first;
    this.second = second;
  }
  
  public boolean equals(Object o) {
    if (!(o instanceof android.support.v4.util.Pair))
      return false; 
    android.support.v4.util.Pair<?, ?> p = (android.support.v4.util.Pair<?, ?>)o;
    return (ObjectsCompat.equals(p.first, this.first) && ObjectsCompat.equals(p.second, this.second));
  }
  
  public int hashCode() {
    return ((this.first == null) ? 0 : this.first.hashCode()) ^ ((this.second == null) ? 0 : this.second.hashCode());
  }
  
  public String toString() {
    return "Pair{" + String.valueOf(this.first) + " " + this.second + "}";
  }
  
  @NonNull
  public static <A, B> android.support.v4.util.Pair<A, B> create(@Nullable A a, @Nullable B b) {
    return new android.support.v4.util.Pair(a, b);
  }
}
