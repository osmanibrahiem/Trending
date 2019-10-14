package com.osman.trending.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName(value = "id")
    private Integer mId;
    @SerializedName(value = "vote_count")
    private Integer mVoteCount;
    @SerializedName(value = "video")
    private Boolean mVideo;
    @SerializedName(value = "vote_average")
    private Float mVoteAverage;
    @SerializedName(value="mTitle", alternate={"title", "name"})
    private String mTitle;
    @SerializedName(value = "popularity")
    private Float mPopularity;
    @SerializedName(value="mPosterPath", alternate={"poster_path", "profile_path"})
    private String mPosterPath;
    @SerializedName(value = "original_language")
    private String mOriginalLanguage;
    @SerializedName(value="mOriginalTitle", alternate={"original_title", "original_name"})
    private String mOriginalTitle;
    @SerializedName(value = "backdrop_path")
    private String mBackdropPath;
    @SerializedName(value = "adult")
    private Boolean mAdult;
    @SerializedName(value = "overview")
    private String mOverview;
    @SerializedName(value = "release_date")
    private String mReleaseDate;
    @SerializedName(value = "media_type")
    private String mMediaType;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Integer mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Float mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Float mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }


    public void setOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public void setMediaType(String mMediaType) {
        this.mMediaType = mMediaType;
    }

}
