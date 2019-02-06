package com.internship.ipda3.semicolon.bloodbank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.model.posts.details.PostDetials;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;

public class PostDetailsActivity extends AppCompatActivity {
    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    @BindView(R.id.article_image_view)
    ImageView articleImageView;
    @BindView(R.id.article_text_view)
    TextView articleTextView;
    @BindView(R.id.title_text_view)
    TextView titleTextView;
    private ApiEndPoint mEndPoint;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            mId = getIntent().getStringExtra("post_id");
            verbose("getIntent: post id: " + mId);
        }

        mEndPoint = getClient().create(ApiEndPoint.class);

        getPostDetails();


    }

    private void getPostDetails() {
        mEndPoint.getPostDetails(API_TOKEN, mId)
                .enqueue(new Callback<PostDetials>() {
                    @Override
                    public void onResponse(Call<PostDetials> call, Response<PostDetials> response) {
                        String msg = response.body().getMsg();
                        long status = response.body().getStatus();

                        if (status == 1) {
                            String title = response.body().getData().getTitle();
                            String content = response.body().getData().getContent();
                            String thumbnail = response.body().getData().getThumbnailFullPath();

                            setData(title, content, thumbnail);
                        } else {
                            verbose("onResponse: response message: " + msg);
                        }

                    }

                    @Override
                    public void onFailure(Call<PostDetials> call, Throwable t) {
                        error("onFailure: getPostDetails: " + t.getMessage());

                    }
                });

    }

    private void setData(String title, String content, String thumbnail) {
        Glide.with(this).load(thumbnail).into(articleImageView);
        articleTextView.setText(content);
        titleTextView.setText(title);


    }

    @OnClick(R.id.favorite_toggle_button)
    public void onViewClicked() {
    }
}
