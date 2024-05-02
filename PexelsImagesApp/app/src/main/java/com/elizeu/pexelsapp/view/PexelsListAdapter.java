package com.elizeu.pexelsapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elizeu.pexelsapp.model.PexelsImagesModel;
import com.elizeu.pexelsapp.model.PexelsSearchResultModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pexelsapp.R;

public class PexelsListAdapter extends RecyclerView.Adapter<PexelsListAdapter.PexelsViewHolder> {
    private List<PexelsImagesModel> pxImages;
    public PexelsListAdapter(List<PexelsImagesModel> pxImages){
        this.pxImages = pxImages;
    }

    public void updatepxImages(PexelsSearchResultModel pxResult){
        List<PexelsImagesModel> newPxImages = pxResult.getPhotos();
        pxImages.clear();
        pxImages.addAll(newPxImages);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PexelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pexelsimage, parent, false);
        return new PexelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PexelsViewHolder holder, int position ) {
        holder.bind(pxImages.get(position));
    }

    @Override
    public int getItemCount() {
        return pxImages.size();
    }

    class PexelsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView pxImageView;

        @BindView(R.id.photographer)
        TextView photographer;

        @BindView(R.id.description)
        TextView description;

        public PexelsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(PexelsImagesModel pxImage){
            photographer.setText(pxImage.getPhotographer());
            description.setText((pxImage.getDescription()));
            Util.loadImage(pxImageView, pxImage.getSrc().getOriginal(), Util.getProgressDrawable(pxImageView.getContext()));
        }
    }
}
