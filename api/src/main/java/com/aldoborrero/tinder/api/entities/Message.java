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

public class Message {

    private String _id;
    private String matchId;
    private String to;
    private String from;
    private String message;
    private Date sentDate;
    private Date createdDate;
    private long timestamp;

    public Message() {
    }

    public Message(String _id, String matchId, String to, String from, String message, Date sentDate, Date createdDate, long timestamp) {
        this._id = _id;
        this.matchId = matchId;
        this.to = to;
        this.from = from;
        this.message = message;
        this.sentDate = sentDate;
        this.createdDate = createdDate;
        this.timestamp = timestamp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "_id='" + _id + '\'' +
                ", matchId='" + matchId + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", sentDate=" + sentDate +
                ", createdDate=" + createdDate +
                ", timestamp=" + timestamp +
                '}';
    }
    
}
