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

public class Versions {

    private String activeText;
    private String ageFilter;
    private String matchmaker;
    private String trending;
    private String trendingActiveText;

    public String getActiveText() {
        return activeText;
    }

    public void setActiveText(String activeText) {
        this.activeText = activeText;
    }

    public String getAgeFilter() {
        return ageFilter;
    }

    public void setAgeFilter(String ageFilter) {
        this.ageFilter = ageFilter;
    }

    public String getMatchmaker() {
        return matchmaker;
    }

    public void setMatchmaker(String matchmaker) {
        this.matchmaker = matchmaker;
    }

    public String getTrending() {
        return trending;
    }

    public void setTrending(String trending) {
        this.trending = trending;
    }

    public String getTrendingActiveText() {
        return trendingActiveText;
    }

    public void setTrendingActiveText(String trendingActiveText) {
        this.trendingActiveText = trendingActiveText;
    }

}
