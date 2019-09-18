package com.example.netcallback.bean;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.util.List;

public class GetJson implements Serializable {
    /**
     * bean类
     */
    private String txt;
    private String postid;
    private List<PhotosBeans> photos;
    private List relatedids;
    private String series;

    public String getSeries() {
        return series;
    }

    public List getRelatedids() {
        return relatedids;
    }

    public void setPhotos(List<PhotosBeans> photos) {
        this.photos = photos;
    }

    public List<PhotosBeans> getPhotos() {
        return photos;
    }

    public String getDesc() {
        return desc;
    }

    private String desc;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public class PhotosBeans implements Serializable{
        private String timgurl;
        private String photohtml;

        public String getPhotohtml() {
            return photohtml;
        }

        public String getTimgurl() {
            return timgurl;
        }
    }


}
