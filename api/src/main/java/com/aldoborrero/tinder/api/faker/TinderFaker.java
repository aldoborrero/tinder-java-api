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

package com.aldoborrero.tinder.api.faker;

import com.aldoborrero.tinder.api.entities.Auth;
import com.aldoborrero.tinder.api.entities.Globals;
import com.aldoborrero.tinder.api.entities.Token;
import com.aldoborrero.tinder.api.entities.User;
import com.aldoborrero.tinder.api.entities.Versions;

public class TinderFaker {

    public static class AuthBuilder {

        public static Auth build() {
            Auth auth = new Auth();
            auth.setToken(new Token("554b4ed5cc9ee665fdbdf6c71a220a5732ae91db"));
            auth.setVersions(VersionsBuilder.build());
            auth.setUser(UserBuilder.createMe());
            //auth.setGlobals();
            return auth;
        }

    }

    public static class UserBuilder {

        public static User createMe() {
            return new User();
        }

    }

    public static class VersionsBuilder {

        public static Versions build() {
            Versions v = new Versions();
            v.setActiveText("0.0.0");
            v.setAgeFilter("2.1.0");
            v.setMatchmaker("2.1.0");
            v.setTrending("10.0.0");
            v.setTrendingActiveText("10.0.0");
            return v;
        }

    }

    public static class GlobalsBuilder {

        public static Globals build() {
            Globals g = new Globals();
            g.setFriends(true);
            g.setInviteType("client");
            return g;
        }

    }

}
