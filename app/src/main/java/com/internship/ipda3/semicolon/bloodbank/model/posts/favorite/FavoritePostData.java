
package com.internship.ipda3.semicolon.bloodbank.model.posts.favorite;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FavoritePostData {

    @SerializedName("current_page")
    private Long mCurrentPage;
    @SerializedName("data")
    private List<FavoritePostDatum> mData;
    @SerializedName("first_page_url")
    private String mFirstPageUrl;
    @SerializedName("from")
    private Long mFrom;
    @SerializedName("last_page")
    private Long mLastPage;
    @SerializedName("last_page_url")
    private String mLastPageUrl;
    @SerializedName("next_page_url")
    private Object mNextPageUrl;
    @SerializedName("path")
    private String mPath;
    @SerializedName("per_page")
    private Long mPerPage;
    @SerializedName("prev_page_url")
    private Object mPrevPageUrl;
    @SerializedName("to")
    private Long mTo;
    @SerializedName("total")
    private Long mTotal;

    public Long getCurrentPage() {
        return mCurrentPage;
    }

    public void setCurrentPage(Long currentPage) {
        mCurrentPage = currentPage;
    }

    public List<FavoritePostDatum> getData() {
        return mData;
    }

    public void setData(List<FavoritePostDatum> data) {
        mData = data;
    }

    public String getFirstPageUrl() {
        return mFirstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        mFirstPageUrl = firstPageUrl;
    }

    public Long getFrom() {
        return mFrom;
    }

    public void setFrom(Long from) {
        mFrom = from;
    }

    public Long getLastPage() {
        return mLastPage;
    }

    public void setLastPage(Long lastPage) {
        mLastPage = lastPage;
    }

    public String getLastPageUrl() {
        return mLastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        mLastPageUrl = lastPageUrl;
    }

    public Object getNextPageUrl() {
        return mNextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        mNextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public Long getPerPage() {
        return mPerPage;
    }

    public void setPerPage(Long perPage) {
        mPerPage = perPage;
    }

    public Object getPrevPageUrl() {
        return mPrevPageUrl;
    }

    public void setPrevPageUrl(Object prevPageUrl) {
        mPrevPageUrl = prevPageUrl;
    }

    public Long getTo() {
        return mTo;
    }

    public void setTo(Long to) {
        mTo = to;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
