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

public class LikeResponse<T extends Match> {
    
    private Response.Status status;
    private T match;

    public LikeResponse() {
    }

    public LikeResponse(Response.Status status, T match) {
        this.status = status;
        this.match = match;
    }

    public Response.Status getStatus() {
        return status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    @SuppressWarnings("unchecked")
    public <E extends Match> E getMatch() {
        return (E) (match != null ? this.match : Match.NOT_MUTUAL);
    }

    public void setMatch(T match) {
        this.match = match;
    }

    public boolean isMutual() {
        return this.match != null;
    }
    
    @Override
    public String toString() {
        return "MatchResponse{" +
                "status=" + status +
                ", match=" + match +
                '}';
    }
    
}
