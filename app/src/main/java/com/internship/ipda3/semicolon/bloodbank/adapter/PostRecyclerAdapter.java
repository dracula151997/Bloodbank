package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.Posts;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsData;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.readToggleButtonState;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil.saveToggleButtonState;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private List<PostsDatum> postsList = new ArrayList<>();
    private Context context;
    boolean isFavorite;


    public PostRecyclerAdapter(List<PostsDatum> postsList, Context context) {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View postItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.article_recycler_view_item, viewGroup, false);
        return new ViewHolder(postItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        PostsDatum postsData = postsList.get(position);


        holder.articleTextView.setText(postsData.getTitle());
        Glide.with(context)
                .load(postsData.getThumbnailFullPath())
                .into(holder.articleImageView);

        holder.favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp));
   /*     holder.favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isFavorite = readToggleButtonState(context);
                if (isChecked){
                    if (isFavorite){
                        holder.favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp));
                        isFavorite = false;
                        saveToggleButtonState(isFavorite, context);
                        showToast(context, "تم إزالة المقال فى المفضلة.");
                    }else{
                        holder.favoriteButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_black_24dp));
                        isFavorite = true;
                        saveToggleButtonState(isFavorite, context);
                        showToast(context, "تم إضافة المقال فى المفضلة.");


                    }


                }
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.article_text_view)
        TextView articleTextView;
        @BindView(R.id.favorite_button)
        ToggleButton favoriteButton;
        @BindView(R.id.article_image_view)
        ImageView articleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
