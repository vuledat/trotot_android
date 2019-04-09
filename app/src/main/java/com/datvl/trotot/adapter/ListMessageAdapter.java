package com.datvl.trotot.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.datvl.trotot.PostDetail;
import com.datvl.trotot.R;
import com.datvl.trotot.library.NumberFormat;
import com.datvl.trotot.model.Message;
import com.datvl.trotot.post.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListMessageAdapter extends RecyclerView.Adapter<ListMessageAdapter.RecyclerViewHolder>{

    private List<Message> data = new ArrayList<>();

    public ListMessageAdapter(List<Message> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_message, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
//        String timeAgo = data.get(position).getTime();
//        data.get(position).getContent()
          holder.txtContent.setText(data.get(position).getContent());
//        holder.txtPrice.setText("" + NumberFormat.getFormatedNum((int) price) + " đ");
//        holder.txtAddress.setText(address);
//        holder.txtTime.setText(timeAgo + " ");

    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.message_content);
        }
    }
}
