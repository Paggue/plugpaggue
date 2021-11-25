package br.com.uol.pagseguro.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Objects;

public class Pair<F, S> {
  @Nullable
  public final F mFirst;
  
  @Nullable
  public final S mSecond;
  
  public Pair(@Nullable F first, @Nullable S second) {
    this.mFirst = first;
    this.mSecond = second;
  }
  
  public boolean equals(Object o) {
    if (!(o instanceof Pair))
      return false; 
    Pair<?, ?> p = (Pair<?, ?>)o;
    return (Objects.equals(this.mFirst, p.mFirst) && Objects.equals(this.mSecond, p.mSecond));
  }
  
  public int hashCode() {
    return ((this.mFirst == null) ? 0 : this.mFirst.hashCode()) ^ ((this.mSecond == null) ? 0 : this.mSecond.hashCode());
  }
  
  public String toString() {
    return "Pair{" + String.valueOf(this.mFirst) + " " + this.mSecond + "}";
  }
  
  @NonNull
  public static <A, B> Pair<A, B> create(@Nullable A a, @Nullable B b) {
    return new Pair<>(a, b);
  }
}
