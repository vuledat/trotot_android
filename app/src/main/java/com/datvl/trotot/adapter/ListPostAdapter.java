package com.datvl.trotot.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datvl.trotot.PostDetail;
import com.datvl.trotot.R;
import com.datvl.trotot.library.NumberFormat;
import com.datvl.trotot.library.TimeAgo;
import com.datvl.trotot.post.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListPostAdapter extends RecyclerView.Adapter<ListPostAdapter.RecyclerViewHolder>{

    private List<Post> data = new ArrayList<>();

    public static final long MAGIC=86400000L;

    public ListPostAdapter(List<Post> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_home, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        String name = data.get(position).getName();
        String name_sub = name.length() > 40 ? name.substring(0,40) + "..." : name;
        int price = data.get(position).getPrice();
        String address = data.get(position).getAddress();
        String timeAgo = data.get(position).getTime();

        holder.txtUserName.setText(name_sub);
        holder.txtPrice.setText("" + NumberFormat.getFormatedNum((int) price) + " đ");
        holder.txtAddress.setText(address);
        holder.txtTime.setText(timeAgo + " | ");

        Picasso.get()
                .load(data.get(position).getImage())
                .resize(60,60)
                .into(holder.imgPost);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), data.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent intent= new Intent(v.getContext(), PostDetail.class);

                intent.putExtra("post", data.get(position));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtUserName, txtPrice, txtAddress, txtTime;
        ImageView imgPost, imgHeart;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtUserName = (TextView) itemView.findViewById(R.id.user_name);
            txtPrice = (TextView) itemView.findViewById(R.id.id_price);
            imgPost = (ImageView) itemView.findViewById(R.id.img_view);
            txtAddress = (TextView) itemView.findViewById(R.id.address);
            txtTime = (TextView) itemView.findViewById(R.id.time);
        }
    }

//    public String getFormatedNum(int amount){
//        return NumberFormat.getNumberInstance(Locale.US).format(amount);
//    }

    public int DateToDays (Date date){
        //  convert a date to an integer and back again
        long currentTime=date.getTime();
        currentTime=currentTime/MAGIC;
        return (int) currentTime;
    }

}
