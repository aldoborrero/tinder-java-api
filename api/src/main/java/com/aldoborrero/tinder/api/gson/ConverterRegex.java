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

package com.aldoborrero.tinder.api.gson;

import java.util.regex.Pattern;

final class ConverterRegex {

    public static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("No session found for user with api_token:");

    public static final Pattern USER_ID = Pattern.compile("\"_id\":\\s?\".*\"");

    private ConverterRegex() {
        throw new AssertionError("No instances of this class are allowed!");
    }

}
