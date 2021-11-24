/*    */ package br.com.uol.pagseguro.plugpag.base;
/*    */ 
/*    */ import android.support.annotation.IdRes;
/*    */ import android.support.v4.app.FragmentTransaction;
/*    */ import br.com.uol.pagseguro.plugpag.R;
/*    */ 
/*    */ public abstract class BaseFragmentActivity
/*    */   extends BaseActivity
/*    */ {
/*    */   @IdRes
/*    */   protected abstract int getContainerLayoutResource();
/*    */   
/*    */   public void replaceFragment(BaseFragment newFragment) {
/* 14 */     FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
/* 15 */     ft.setCustomAnimations(R.anim.enter_from_right, R.anim.fade_out, R.anim.fade_in, R.anim.exit_to_right);
/* 16 */     ft.replace(getContainerLayoutResource(), newFragment, newFragment.getTagName());
/* 17 */     ft.addToBackStack(null);
/* 18 */     ft.commit();
/*    */   }
/*    */   
/*    */   public void backFragment() {
/* 22 */     getSupportFragmentManager().popBackStack();
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/base/BaseFragmentActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */