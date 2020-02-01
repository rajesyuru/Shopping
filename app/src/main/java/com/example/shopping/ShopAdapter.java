package com.example.shopping;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Shopping> mShoppings;
    private SetClickListener msetClickListener;


    public ShopAdapter(Context context, int resouce, ArrayList<Shopping> shoppings, SetClickListener setClickListener) {
        mContext = context;
        mResource = resouce;
        mShoppings = shoppings;
        msetClickListener = setClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(mResource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Shopping shopping = mShoppings.get(position);

        holder.shoppingCheckbox.setChecked(shopping.isDone());
        holder.shoppingText.setText(shopping.getDesc());
        if (shopping.isDone()) {
            holder.shoppingText.setPaintFlags(holder.shoppingText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.shoppingText.setTypeface(null, Typeface.BOLD_ITALIC);
        } else {
            holder.shoppingText.setPaintFlags(holder.shoppingText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.shoppingText.setTypeface(null, Typeface.NORMAL);
        }

        holder.shoppingCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                msetClickListener.onCheck(holder.getAdapterPosition(), checkBox.isChecked());
            }
        });

        holder.deleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetClickListener.Remove(holder.getAdapterPosition());
            }
        });

        holder.imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetClickListener.Edit(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mShoppings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox shoppingCheckbox;
        TextView shoppingText;
        ImageView deleteList;
        ImageView imageEdit;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            shoppingCheckbox = itemView.findViewById(R.id.shoppingCheckbox);
            shoppingText = itemView.findViewById(R.id.shoppingText);
            deleteList = itemView.findViewById(R.id.deleteList);
            imageEdit = itemView.findViewById(R.id.imageEdit);



        }
    }

    public interface SetClickListener {
        void onCheck (int position, boolean isChecked);
        void Remove (int position);
        void Edit (int position);
    }
}
