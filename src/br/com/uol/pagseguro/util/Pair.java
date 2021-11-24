/*    */ package br.com.uol.pagseguro.util;
/*    */ 
/*    */ import android.support.annotation.NonNull;
/*    */ import android.support.annotation.Nullable;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Pair<F, S>
/*    */ {
/*    */   @Nullable
/*    */   public final F mFirst;
/*    */   @Nullable
/*    */   public final S mSecond;
/*    */   
/*    */   public Pair(@Nullable F first, @Nullable S second) {
/* 35 */     this.mFirst = first;
/* 36 */     this.mSecond = second;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 46 */     if (!(o instanceof Pair)) {
/* 47 */       return false;
/*    */     }
/* 49 */     Pair<?, ?> p = (Pair<?, ?>)o;
/*    */     
/* 51 */     return (Objects.equals(this.mFirst, p.mFirst) && Objects.equals(this.mSecond, p.mSecond));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 61 */     return ((this.mFirst == null) ? 0 : this.mFirst.hashCode()) ^ ((this.mSecond == null) ? 0 : this.mSecond.hashCode());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 70 */     return "Pair{" + String.valueOf(this.mFirst) + " " + this.mSecond + "}";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NonNull
/*    */   public static <A, B> Pair<A, B> create(@Nullable A a, @Nullable B b) {
/* 84 */     return new Pair<>(a, b);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/util/Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */