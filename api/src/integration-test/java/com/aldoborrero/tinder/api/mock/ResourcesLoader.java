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

package com.aldoborrero.tinder.api.mock;

import com.google.common.base.Charsets;
import okio.Buffer;

import java.io.IOException;
import java.io.InputStream;

public class ResourcesLoader {

    private static InputStream loadResource(String resource) {
        return FakeResponses.class.getResourceAsStream(resource);
    }

    public static Buffer loadAsBuffer(String resource) {
        Buffer b = new Buffer();
        try {
            b.readFrom(loadResource(resource));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resources data", e);
        }
        return b;
    }
    
    public static String loadAsString(String resource) {
        return loadAsBuffer(resource).readString(Charsets.UTF_8);
    }
    
    private ResourcesLoader() {
        throw new AssertionError("No instances of this class are allowed!");
    }
    
}
