package br.com.uol.pagseguro.plugpag.datainput;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.uol.pagseguro.plugpag.R;

public class OptionsListAdapter extends RecyclerView.Adapter<OptionsListAdapter.MyViewHolder> {
  private LayoutInflater mInflater;
  
  private Context mContext;
  
  private String[] mOptionList;
  
  private OptionsListClickListener mOnClickListener;
  
  public OptionsListAdapter(Context context, String[] optionList, OptionsListClickListener optionsClickListener) {
    this.mInflater = LayoutInflater.from(context);
    this.mContext = context;
    this.mOptionList = optionList;
    this.mOnClickListener = optionsClickListener;
  }
  
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = this.mInflater.inflate(R.layout.item_option, parent, false);
    MyViewHolder holder = new MyViewHolder(view);
    return holder;
  }
  
  public void onBindViewHolder(MyViewHolder holder, @SuppressLint({"RecyclerView"}) final int position) {
    holder.mOptionItem.setText(this.mOptionList[position]);
    holder.mContainer.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            OptionsListAdapter.this.mOnClickListener.onItemClicked(Integer.valueOf(position));
          }
        });
  }
  
  public int getItemCount() {
    return this.mOptionList.length;
  }
  
  class MyViewHolder extends RecyclerView.ViewHolder {
    TextView mOptionItem;
    
    View mContainer;
    
    public MyViewHolder(View itemView) {
      super(itemView);
      this.mOptionItem = (TextView)itemView.findViewById(R.id.textview_option_item);
      this.mContainer = itemView.findViewById(R.id.container);
    }
  }
}
