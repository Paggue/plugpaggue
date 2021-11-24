/*    */ package br.com.uol.pagseguro.plugpag.util;
/*    */ 
/*    */ import android.support.annotation.NonNull;
/*    */ import android.support.annotation.Nullable;
/*    */ import android.support.v4.util.ObjectsCompat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pair<F, S>
/*    */ {
/*    */   @Nullable
/*    */   public final F first;
/*    */   @Nullable
/*    */   public final S second;
/*    */   
/*    */   public Pair(@Nullable F first, @Nullable S second) {
/* 19 */     this.first = first;
/* 20 */     this.second = second;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 24 */     if (!(o instanceof android.support.v4.util.Pair)) {
/* 25 */       return false;
/*    */     }
/* 27 */     android.support.v4.util.Pair<?, ?> p = (android.support.v4.util.Pair<?, ?>)o;
/* 28 */     return (ObjectsCompat.equals(p.first, this.first) && ObjectsCompat.equals(p.second, this.second));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 33 */     return ((this.first == null) ? 0 : this.first.hashCode()) ^ ((this.second == null) ? 0 : this.second.hashCode());
/*    */   }
/*    */   
/*    */   public String toString() {
/* 37 */     return "Pair{" + String.valueOf(this.first) + " " + this.second + "}";
/*    */   }
/*    */   
/*    */   @NonNull
/*    */   public static <A, B> android.support.v4.util.Pair<A, B> create(@Nullable A a, @Nullable B b) {
/* 42 */     return new android.support.v4.util.Pair(a, b);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/util/Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */