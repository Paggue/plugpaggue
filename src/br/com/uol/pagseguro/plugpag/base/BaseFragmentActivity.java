package br.com.uol.pagseguro.plugpag.base;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import br.com.uol.pagseguro.plugpag.R;

public abstract class BaseFragmentActivity extends BaseActivity {
  @IdRes
  protected abstract int getContainerLayoutResource();
  
  public void replaceFragment(BaseFragment newFragment) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.fade_out, R.anim.fade_in, R.anim.exit_to_right);
    ft.replace(getContainerLayoutResource(), newFragment, newFragment.getTagName());
    ft.addToBackStack(null);
    ft.commit();
  }
  
  public void backFragment() {
    getSupportFragmentManager().popBackStack();
  }
}
