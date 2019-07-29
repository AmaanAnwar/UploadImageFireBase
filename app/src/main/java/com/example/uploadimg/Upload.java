package com.example.uploadimg;
public class Upload
{
    private String imgName;
    private String imgUrl;
    private String time;
    private String description;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Upload() {
    }

    public Upload(String imgName, String imgUrl, String time, String description,String uid) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.time = time;
        this.description = description;
        this.uid=uid;
    }

    public String getImgName() {

        return imgName;
    }

    public void setImgName(String imgName) {

        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
