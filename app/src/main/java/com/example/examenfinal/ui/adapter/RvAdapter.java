package com.example.examenfinal.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.examenfinal.CrudRoomApp;
import com.example.examenfinal.R;
import com.example.examenfinal.database.Word;
import com.example.examenfinal.ui.activity.FuntionActivity;
import com.example.examenfinal.ui.common.DataListListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<Word> dataList = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Word> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Word data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Word data) {
        int position = -1;

        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void removeData(Word data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RequestOptions requestOptions;
        private TextView tvNama;
        private ImageView imageView;
        private Button btnEliminar;
        private Word data;
        private DataListListener listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .circleCrop()
                    .placeholder(R.drawable.ic_account)
                    .error(R.drawable.ic_account);

            tvNama = itemView.findViewById(R.id.tv_word);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
            btnEliminar.setOnClickListener(this);
        }

        void bind(Word data, DataListListener listener) {
            this.data = data;
            this.listener = listener;

            tvNama.setText(data.getWord());

            loadImage(new File(data.getImagen()));
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_eliminar) {

                CrudRoomApp.getInstance().getDataBase().wordDao().delete(data);
                listener.onRemoveClick(data);
                Toast.makeText(itemView.getContext(), "Eliminado con exito!", Toast.LENGTH_SHORT).show();

            } else if (view.getId() == R.id.item_list) {

                Intent intent = new Intent(itemView.getContext(), FuntionActivity.class);
                intent.putExtra(FuntionActivity.TAG_DATA_INTENT, data.getId());
                itemView.getContext().startActivity(intent);

            }
        }

        private void loadImage(File image) {
            if (image == null) return;

            Glide.with(itemView.getContext())
                    .asBitmap()
                    .apply(requestOptions)
                    .load(image)
                    .into(imageView);
        }
    }

}
