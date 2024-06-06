package com.example.activity1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
    private Context context;
    private List<Car> carList;

    public CustomListAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < carList.size()) {
            Car car = carList.get(position);
            if (car != null) {
                holder.modelTextView.setText(car.getModel());
                Glide.with(context).load(car.getImage()).into(holder.carImageView);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView modelTextView;
        public ImageView carImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            modelTextView = itemView.findViewById(R.id.textView);
            carImageView = itemView.findViewById(R.id.imageView);

            if (modelTextView == null || carImageView == null) {
                throw new IllegalArgumentException("TextView or ImageView is null in custom_list_item.xml");
            }
        }
    }


    @Override
    public int getItemCount() {
        return carList.size();
    }
}
