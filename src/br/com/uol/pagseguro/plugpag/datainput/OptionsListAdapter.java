/*    */ package br.com.uol.pagseguro.plugpag.datainput;
/*    */ 
/*    */ import android.annotation.SuppressLint;
/*    */ import android.content.Context;
/*    */ import android.support.v7.widget.RecyclerView;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.TextView;
/*    */ import br.com.uol.pagseguro.plugpag.R;
/*    */ 
/*    */ 
/*    */ public class OptionsListAdapter
/*    */   extends RecyclerView.Adapter<OptionsListAdapter.MyViewHolder>
/*    */ {
/*    */   private LayoutInflater mInflater;
/*    */   private Context mContext;
/*    */   private String[] mOptionList;
/*    */   private OptionsListClickListener mOnClickListener;
/*    */   
/*    */   public OptionsListAdapter(Context context, String[] optionList, OptionsListClickListener optionsClickListener) {
/* 22 */     this.mInflater = LayoutInflater.from(context);
/* 23 */     this.mContext = context;
/* 24 */     this.mOptionList = optionList;
/* 25 */     this.mOnClickListener = optionsClickListener;
/*    */   }
/*    */ 
/*    */   
/*    */   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
/* 30 */     View view = this.mInflater.inflate(R.layout.item_option, parent, false);
/* 31 */     MyViewHolder holder = new MyViewHolder(view);
/* 32 */     return holder;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBindViewHolder(MyViewHolder holder, @SuppressLint({"RecyclerView"}) final int position) {
/* 37 */     holder.mOptionItem.setText(this.mOptionList[position]);
/* 38 */     holder.mContainer.setOnClickListener(new View.OnClickListener()
/*    */         {
/*    */           public void onClick(View v) {
/* 41 */             OptionsListAdapter.this.mOnClickListener.onItemClicked(Integer.valueOf(position));
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemCount() {
/* 48 */     return this.mOptionList.length;
/*    */   }
/*    */   
/*    */   class MyViewHolder extends RecyclerView.ViewHolder {
/*    */     TextView mOptionItem;
/*    */     View mContainer;
/*    */     
/*    */     public MyViewHolder(View itemView) {
/* 56 */       super(itemView);
/* 57 */       this.mOptionItem = (TextView)itemView.findViewById(R.id.textview_option_item);
/* 58 */       this.mContainer = itemView.findViewById(R.id.container);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/datainput/OptionsListAdapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */