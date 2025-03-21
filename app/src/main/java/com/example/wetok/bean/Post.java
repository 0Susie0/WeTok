package com.example.wetok.bean;

import java.io.Serializable;
import java.util.List;

/**
 * The Post class
 * @author Xinyue Hu
 * @author Yuxin Hong
 * @author Xinyu Kang
 */
public class Post implements Comparable, Serializable {
    private String content;
    // user info
    private String uid;
    private String author;
    private String email;
    private String u_img;

    // post info
    private String time;
    private List<String> tag;

    // post property
    private int likes;
    private int dislikes = 0;

    public Post(List<String> tag, String content, String id) {
        this.tag = tag;
        this.content = content;
        this.uid = id;
    }

    @Override
    public String toString() {
        return content;
    }

    public Post(){}

    public Post(String content, String uid, String author, String email, String u_img, String time,
                List<String> tag, int likes, int star) {
        this.content = content;
        this.uid = uid;
        this.author = author;
        this.email = email;
        this.u_img = u_img;
        this.time = time;
        this.tag = tag;
        this.likes = likes;
        this.dislikes = star;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getU_img() {
        return u_img;
    }

    public void setU_img(String u_img) {
        this.u_img = u_img;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    /**
     * Compare the publish time between this post and o
     * @param o another post
     * @return an integer indicating the relationship
     */
    @Override
    public int compareTo(Object o) {
        String o_time = ((Post) o).getTime().replaceAll("[-: ]","");
        String this_time = this.getTime().replaceAll("[-: ]","");
        long diff = Long.parseLong(o_time) - Long.parseLong(this_time);
        if (diff > 0){
            return 1;
        } else if (diff < 0){
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Check if the content and uid of two posts are equal
     * @param o another post
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        Post p = (Post) o;
        boolean b1 = content.equals(p.getContent());
        boolean b2 = uid.equals(p.getUid());
        return (b1 && b2);
    }
}
