package com.internship.ipda3.semicolon.bloodbank.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.internship.ipda3.semicolon.bloodbank.R;
import com.internship.ipda3.semicolon.bloodbank.adapter.PostRecyclerAdapter;
import com.internship.ipda3.semicolon.bloodbank.model.posts.favorite.FavoritePost;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.PostsDatum;
import com.internship.ipda3.semicolon.bloodbank.rest.ApiEndPoint;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.internship.ipda3.semicolon.bloodbank.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.internship.ipda3.semicolon.bloodbank.rest.RetrofitClient.getClient;
import static com.internship.ipda3.semicolon.bloodbank.util.LogUtil.verbose;
import static com.internship.ipda3.semicolon.bloodbank.util.SharedPreferencesManger.LoadStringData;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Unbinder unbinder;
    String apiToken;
    private ApiEndPoint mEndPoint;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        unbinder = ButterKnife.bind(this, view);
        apiToken = LoadStringData(getActivity(), API_TOKEN);

        mEndPoint = getClient().create(ApiEndPoint.class);
        setupRecyclerView();
        loadFavoritePosts();
        return view;
    }

    private void loadFavoritePosts() {
        mEndPoint.getFavouritesList(apiToken)
                .enqueue(new Callback<FavoritePost>() {
                    @Override
                    public void onResponse(Call<FavoritePost> call, Response<FavoritePost> response) {
                        if (response.code() == 200) {
                            long status = response.body().getStatus();
                            String msg = response.body().getMsg();
                            if (status == 1) {
                                verbose("response message: " + msg);
                                List<PostsDatum> favoriteData = response.body().getData().getData();
                                verbose("favorite posts: " + favoriteData.toString());
                                recyclerView.setAdapter(new PostRecyclerAdapter(favoriteData, getContext(), getActivity()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<FavoritePost> call, Throwable t) {

                    }
                });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
