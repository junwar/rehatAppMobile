package com.example.rehat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rehat.model.upload.Data;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Data> uploads;

    public PostAdapter(Context context, List<Data> uploads) {
        this.context = context;
        this.uploads = uploads;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_beranda, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Data upload = uploads.get(position);
        holder.judul.setText(upload.getJudul());
        holder.deskripsi.setText(upload.getDeskripsi());
        Glide.with(context).load("http://192.168.1.14/uploads/" + upload.getGambar()).into(holder.gambar); // Perbarui URL sesuai kebutuhan
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView judul, deskripsi;
        ImageView gambar;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            gambar = itemView.findViewById(R.id.gambar);
        }
    }
}
