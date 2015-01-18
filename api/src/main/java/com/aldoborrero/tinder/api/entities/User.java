/*
 * Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aldoborrero.tinder.api.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class User implements Result {
    
    private String _id;
    private Date activeTime;
    private Date createDate;
    private int ageFilterMax;
    private int ageFilterMin;
    private String apiToken;
    private String bio;
    private Date birthDate;
    private int distanceFilter;
    private String fullName;
    private String name;
    private Gender gender;
    private List<Photo> photos;
    private List<Purchase> purchases;
    private Date pingTime;
    private boolean discoverable;
    private GenderFilter genderFilter;

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getAgeFilterMax() {
        return ageFilterMax;
    }

    public void setAgeFilterMax(int ageFilterMax) {
        this.ageFilterMax = ageFilterMax;
    }

    public int getAgeFilterMin() {
        return ageFilterMin;
    }

    public void setAgeFilterMin(int ageFilterMin) {
        this.ageFilterMin = ageFilterMin;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getDistanceFilter() {
        return distanceFilter;
    }

    public void setDistanceFilter(int distanceFilter) {
        this.distanceFilter = distanceFilter;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Date getPingTime() {
        return pingTime;
    }

    public void setPingTime(Date pingTime) {
        this.pingTime = pingTime;
    }

    public boolean isDiscoverable() {
        return discoverable;
    }

    public void setDiscoverable(boolean discoverable) {
        this.discoverable = discoverable;
    }

    public GenderFilter getGenderFilter() {
        return genderFilter;
    }

    public void setGenderFilter(GenderFilter genderFilter) {
        this.genderFilter = genderFilter;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", activeTime='" + activeTime + '\'' +
                ", createDate='" + createDate + '\'' +
                ", ageFilterMax=" + ageFilterMax +
                ", ageFilterMin=" + ageFilterMin +
                ", apiToken='" + apiToken + '\'' +
                ", bio='" + bio + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", distanceFilter=" + distanceFilter +
                ", fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", photos=" + photos +
                ", purchases=" + purchases +
                ", pingTime='" + pingTime + '\'' +
                ", discoverable=" + discoverable +
                ", genderFilter=" + genderFilter +
                '}';
    }

    public enum Gender {
        @SerializedName("0")
        MALE,

        @SerializedName("1")
        FEMALE
    }

    public enum GenderFilter {
        @SerializedName("0")
        MALE,

        @SerializedName("1")
        FEMALE,

        @SerializedName("2")
        BOTH
    }

}
