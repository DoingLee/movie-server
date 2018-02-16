package com.homework.dao.doc.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author：ldy on 16/02/2018 12:58
 */
@Entity(value = "user_doc", noClassnameStored = true)
public class UserDoc {

    @Id
    Long userId;
    String password;
    String photoUrl;
    String gender;
    String city;
    Long birthday;
    String introduction;
    Long createTime;

    public UserDoc() {
    }

    public UserDoc(Long userId, String password, String photoUrl, String gender, String city, Long birthday, String introduction, Long createTime) {
        this.userId = userId;
        this.password = password;
        this.photoUrl = photoUrl;
        this.gender = gender;
        this.city = city;
        this.birthday = birthday;
        this.introduction = introduction;
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDoc{" +
                       "userId=" + userId +
                       ", password='" + password + '\'' +
                       ", photoUrl='" + photoUrl + '\'' +
                       ", gender='" + gender + '\'' +
                       ", city='" + city + '\'' +
                       ", birthday=" + birthday +
                       ", introduction='" + introduction + '\'' +
                       ", createTime=" + createTime +
                       '}';
    }
}
