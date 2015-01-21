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

public class Updates {
    
    private List<MutualMatch> matches;
    private List<String> blocks;
    //private List<Matchmaker> matchmaker;
    //private List<Lists> lists;
    //private List<DeletedLists> deletedLists;
    private Date lastActivityDate;

    public Updates() {
    }

    public Updates(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public List<MutualMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<MutualMatch> matches) {
        this.matches = matches;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }
    
}
