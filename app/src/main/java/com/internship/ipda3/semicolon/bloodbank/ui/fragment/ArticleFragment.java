package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.ipda3.semicolon.bloodbank.EndlessRecyclerOnScrollListener;
import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.adapter.PostRecyclerAdapter;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.Posts;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsData;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsDatum;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;
import com.internship.ipda3.semicolon.bloodbank.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.error;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    int max;
    String apiToken;


    @BindView(R.id.article_recycler_view)
    RecyclerView articleRecyclerView;
    Unbinder unbinder;

    private ApiEndPoint mEndPoints;

    public ArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        unbinder = ButterKnife.bind(this, view);

        apiToken = LoadStringData(getActivity(), API_TOKEN);
        verbose("user api token: " + apiToken);


        mEndPoints = getClient().create(ApiEndPoint.class);
        setupRecyclerView();



        loadPosts(1);
        return view;
    }

    private void loadPosts(int page) {
        mEndPoints.getPosts(apiToken, page)
                .enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        verbose("onPostsResponse: response raw: " + response.raw());
                        List<PostsDatum> data = response.body().getData().getData();
                        articleRecyclerView.setAdapter(new PostRecyclerAdapter(data, getContext(), getActivity()));
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {
                        error("onPostsResponse: failure: " + t.getMessage());

                    }
                });
    }


    private void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        articleRecyclerView.setHasFixedSize(true);
        articleRecyclerView.setLayoutManager(manager);

        articleRecyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= max){
                    max = current_page;
                    loadPosts(current_page);

                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
