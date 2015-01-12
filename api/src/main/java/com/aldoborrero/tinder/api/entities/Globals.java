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

public class Globals {

    private boolean friends;
    private String inviteType;
    private Integer recsInterval;
    private Integer updatesInterval;
    private Integer recsSize;
    private String matchmakerDefaultMessage;
    private String shareDefaultText;
    private Integer boostDecay;
    private Integer boostUp;
    private Integer boostDown;
    private boolean sparks;
    private boolean kontagent;
    private boolean sparksEnabled;
    private boolean kontagentEnabled;
    private boolean mqtt;
    private boolean tinderSparks;
    private int momentsInterval;
    private boolean plus;

    public boolean isFriends() {
        return friends;
    }

    public void setFriends(boolean friends) {
        this.friends = friends;
    }

    public String getInviteType() {
        return inviteType;
    }

    public void setInviteType(String inviteType) {
        this.inviteType = inviteType;
    }

    public Integer getRecsInterval() {
        return recsInterval;
    }

    public void setRecsInterval(Integer recsInterval) {
        this.recsInterval = recsInterval;
    }

    public Integer getUpdatesInterval() {
        return updatesInterval;
    }

    public void setUpdatesInterval(Integer updatesInterval) {
        this.updatesInterval = updatesInterval;
    }

    public Integer getRecsSize() {
        return recsSize;
    }

    public void setRecsSize(Integer recsSize) {
        this.recsSize = recsSize;
    }

    public String getMatchmakerDefaultMessage() {
        return matchmakerDefaultMessage;
    }

    public void setMatchmakerDefaultMessage(String matchmakerDefaultMessage) {
        this.matchmakerDefaultMessage = matchmakerDefaultMessage;
    }

    public String getShareDefaultText() {
        return shareDefaultText;
    }

    public void setShareDefaultText(String shareDefaultText) {
        this.shareDefaultText = shareDefaultText;
    }

    public Integer getBoostDecay() {
        return boostDecay;
    }

    public void setBoostDecay(Integer boostDecay) {
        this.boostDecay = boostDecay;
    }

    public Integer getBoostUp() {
        return boostUp;
    }

    public void setBoostUp(Integer boostUp) {
        this.boostUp = boostUp;
    }

    public Integer getBoostDown() {
        return boostDown;
    }

    public void setBoostDown(Integer boostDown) {
        this.boostDown = boostDown;
    }

    public boolean isSparks() {
        return sparks;
    }

    public void setSparks(boolean sparks) {
        this.sparks = sparks;
    }

    public boolean isKontagent() {
        return kontagent;
    }

    public void setKontagent(boolean kontagent) {
        this.kontagent = kontagent;
    }

    public boolean isSparksEnabled() {
        return sparksEnabled;
    }

    public void setSparksEnabled(boolean sparksEnabled) {
        this.sparksEnabled = sparksEnabled;
    }

    public boolean isKontagentEnabled() {
        return kontagentEnabled;
    }

    public void setKontagentEnabled(boolean kontagentEnabled) {
        this.kontagentEnabled = kontagentEnabled;
    }

    public boolean isMqtt() {
        return mqtt;
    }

    public void setMqtt(boolean mqtt) {
        this.mqtt = mqtt;
    }

    public boolean isTinderSparks() {
        return tinderSparks;
    }

    public void setTinderSparks(boolean tinderSparks) {
        this.tinderSparks = tinderSparks;
    }

    public int getMomentsInterval() {
        return momentsInterval;
    }

    public void setMomentsInterval(int momentsInterval) {
        this.momentsInterval = momentsInterval;
    }

    public boolean isPlus() {
        return plus;
    }

    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    @Override
    public String toString() {
        return "Globals{" +
                "friends=" + friends +
                ", inviteType='" + inviteType + '\'' +
                ", recsInterval=" + recsInterval +
                ", updatesInterval=" + updatesInterval +
                ", recsSize=" + recsSize +
                ", matchmakerDefaultMessage='" + matchmakerDefaultMessage + '\'' +
                ", shareDefaultText='" + shareDefaultText + '\'' +
                ", boostDecay=" + boostDecay +
                ", boostUp=" + boostUp +
                ", boostDown=" + boostDown +
                ", sparks=" + sparks +
                ", kontagent=" + kontagent +
                ", sparksEnabled=" + sparksEnabled +
                ", kontagentEnabled=" + kontagentEnabled +
                ", mqtt=" + mqtt +
                ", tinderSparks=" + tinderSparks +
                ", momentsInterval=" + momentsInterval +
                ", plus=" + plus +
                '}';
    }

}
