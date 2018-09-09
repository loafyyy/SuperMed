package com.mongodb.stitch.sdk.examples.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.MyViewHolder> {

    private List<DrugPreview> drugs;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView drugName;
        public ImageView drugImage;

        public MyViewHolder(View view) {
            super(view);
            drugName = view.findViewById(R.id.drug_name);
            drugImage = view.findViewById(R.id.drug_image);
        }
    }

    public DrugAdapter(Context mContext, List<DrugPreview> drugs) {
        this.drugs = drugs;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drug_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrugPreview drug = drugs.get(position);
        holder.drugName.setText(drug.getDrugName());

        String url = drug.getDrugImageURL();
        if (url != null && !url.isEmpty()){
            Glide.with(mContext).load(url).into(holder.drugImage);
        }
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }
}