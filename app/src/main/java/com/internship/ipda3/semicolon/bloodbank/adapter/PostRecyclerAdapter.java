package com.internship.ipda3.semicolon.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsDatum;
import com.internship.ipda3.semicolon.bloodbank.model.posts.toggle.FavoritePostToggle;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.ui.activity.PostDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.intentWithExtra;
import static com.internship.ipda3.semicolon.bloodbank.helper.HelperMethod.showToast;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    Activity activity;
    long postId;
    private List<PostsDatum> postsList = new ArrayList<>();
    private Context context;
    private ApiEndPoint endPoint;
    private String apiToken;


    public PostRecyclerAdapter(List<PostsDatum> postsList, Context context, Activity activity) {
        this.postsList = postsList;
        this.context = context;
        this.activity = activity;
        endPoint = getClient().create(ApiEndPoint.class);
        apiToken = LoadStringData(activity, API_TOKEN);
        verbose("api token: " + apiToken);
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
        postId = postsData.getId();

        boolean isFavorite = postsData.getIsFavourite();


        if (isFavorite) {
            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }


        setData(holder, position, postsData);
        setAction(holder, position, postsData);


    }

    private void setData(ViewHolder holder, int position, PostsDatum postsList) {

        holder.articleTextView.setText(postsList.getTitle());

        Glide.with(context)
                .load(postsList.getThumbnailFullPath())
                .into(holder.articleImageView);

    }

    private void setAction(final ViewHolder holder, final int position, final PostsDatum postsList) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = postsList.getId();
                intentWithExtra(context, PostDetailsActivity.class, "post_id", String.valueOf(id));

            }
        });

        holder.favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                long id = postsList.getId();
                if (isChecked) {
                    endPoint.postToggleFavourite(id, apiToken)
                            .enqueue(new Callback<FavoritePostToggle>() {
                                @Override
                                public void onResponse(Call<FavoritePostToggle> call, Response<FavoritePostToggle> response) {
                                    if (response.code() == 200) {
                                        String msg = response.body().getMsg();
                                        long status = response.body().getStatus();
                                        if (status == 1) {
                                            showToast(context, context.getString(R.string.added_to_favorite));
                                            holder.favoriteButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<FavoritePostToggle> call, Throwable t) {
                                    error("onResponse: recycler view adapter: " + t.getMessage());

                                }
                            });
                }


            }
        });
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
