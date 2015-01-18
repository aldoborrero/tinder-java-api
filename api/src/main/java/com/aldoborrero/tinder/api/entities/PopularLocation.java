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

public class PopularLocation implements Result {
    
    private long lat;
    private long lon;
    private Locality locality;
    private Country country;
    private AdministrativeArea administrativeAreaLevel1;
    private AdministrativeArea administrativeAreaLevel2;
    
    public PopularLocation() {
    }

    public PopularLocation(long lat, long lon, Locality locality) {
        this.lat = lat;
        this.lon = lon;
        this.locality = locality;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLon() {
        return lon;
    }

    public void setLon(long lon) {
        this.lon = lon;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdministrativeArea getAdministrativeAreaLevel1() {
        return administrativeAreaLevel1;
    }

    public void setAdministrativeAreaLevel1(AdministrativeArea administrativeAreaLevel1) {
        this.administrativeAreaLevel1 = administrativeAreaLevel1;
    }

    public AdministrativeArea getAdministrativeAreaLevel2() {
        return administrativeAreaLevel2;
    }

    public void setAdministrativeAreaLevel2(AdministrativeArea administrativeAreaLevel2) {
        this.administrativeAreaLevel2 = administrativeAreaLevel2;
    }

    @Override
    public String toString() {
        return "PopularLocation{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", locality=" + locality +
                '}';
    }

    public static class Locality {

        private String shortName;
        private String longName;

        public Locality() {
        }

        public Locality(String shortName, String longName) {
            this.shortName = shortName;
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        @Override
        public String toString() {
            return "Locality{" +
                    "shortName='" + shortName + '\'' +
                    ", longName='" + longName + '\'' +
                    '}';
        }

    }
    
    public static class Country {

        private String shortName;
        private String longName;

        public Country() {
        }

        public Country(String shortName, String longName) {
            this.shortName = shortName;
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "shortName='" + shortName + '\'' +
                    ", longName='" + longName + '\'' +
                    '}';
        }

    }
    
    public static class AdministrativeArea {

        private String shortName;
        private String longName;

        public AdministrativeArea() {
        }

        public AdministrativeArea(String shortName, String longName) {
            this.shortName = shortName;
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        @Override
        public String toString() {
            return "AdministrativeArea{" +
                    "shortName='" + shortName + '\'' +
                    ", longName='" + longName + '\'' +
                    '}';
        }

    }
    
}
