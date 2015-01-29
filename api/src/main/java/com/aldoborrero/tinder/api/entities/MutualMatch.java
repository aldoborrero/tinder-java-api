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

import java.util.Date;
import java.util.List;

public class MutualMatch implements Match {

    private String _id;
    private String id;
    private boolean closed;
    private int commonFriendCount;
    private int commonLikeCount;
    private Date createdDate;
    private boolean dead;
    private Date lastActivityDate;
    private int messageCount;
    private List<Message> messages;
    private List<String> participants;
    private boolean pending;
    private boolean following;
    private boolean followingMoments;
    private User person;

    public MutualMatch() {
    }

    public MutualMatch(String _id, String id, boolean closed, int commonFriendCount, int commonLikeCount, 
                       Date createdDate, boolean dead, Date lastActivityDate, int messageCount, List<Message> messages, 
                       List<String> participants, boolean pending, boolean following, boolean followingMoments, 
                       User person) {
        this._id = _id;
        this.id = id;
        this.closed = closed;
        this.commonFriendCount = commonFriendCount;
        this.commonLikeCount = commonLikeCount;
        this.createdDate = createdDate;
        this.dead = dead;
        this.lastActivityDate = lastActivityDate;
        this.messageCount = messageCount;
        this.messages = messages;
        this.participants = participants;
        this.pending = pending;
        this.following = following;
        this.followingMoments = followingMoments;
        this.person = person;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getCommonFriendCount() {
        return commonFriendCount;
    }

    public void setCommonFriendCount(int commonFriendCount) {
        this.commonFriendCount = commonFriendCount;
    }

    public int getCommonLikeCount() {
        return commonLikeCount;
    }

    public void setCommonLikeCount(int commonLikeCount) {
        this.commonLikeCount = commonLikeCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isFollowingMoments() {
        return followingMoments;
    }

    public void setFollowingMoments(boolean followingMoments) {
        this.followingMoments = followingMoments;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public boolean isMutual() {
        return this._id != null;
    }

    @Override
    public Type getType() {
        return Type.MUTUAL;
    }

    @Override
    public String toString() {
        return "Match{" +
                "_id='" + _id + '\'' +
                ", id='" + id + '\'' +
                ", closed=" + closed +
                ", commonFriendCount=" + commonFriendCount +
                ", commonLikeCount=" + commonLikeCount +
                ", createdDate=" + createdDate +
                ", dead=" + dead +
                ", lastActivityDate=" + lastActivityDate +
                ", messageCount=" + messageCount +
                ", messages=" + messages +
                ", participants=" + participants +
                ", pending=" + pending +
                ", following=" + following +
                ", followingMoments=" + followingMoments +
                ", person=" + person +
                '}';
    }

}
