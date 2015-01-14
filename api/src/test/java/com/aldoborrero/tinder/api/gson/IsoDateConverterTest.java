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

import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertTrue;

public class IsoDateConverterTest {

    private static final String VALID_ISO_DATE = "2015-01-14T06:18:29.116Z";
    private static final Date VALID_DATE;
    
    private static final String INVALID_ISO_DATE = "invalid-date";

    static {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
        c.setTimeInMillis(1421216309116L);
        VALID_DATE = c.getTime();
    }
    
    private IsoDateConverter converter = Mockito.spy(new IsoDateConverter());

    @Test
    public void shouldDeserializeCorrectlyIsoStrings() throws Exception {
        Date deserialized = this.converter.deserialize(new JsonPrimitive(VALID_ISO_DATE), IsoDateConverter.TYPE, null);
        assertTrue(VALID_DATE.compareTo(deserialized) == 0);
    }

    @Test(expected = JsonSyntaxException.class)
    public void shouldRaiseExceptionIfIsoDateIsInvalid() throws Exception {
        this.converter.deserialize(new JsonPrimitive(INVALID_ISO_DATE), IsoDateConverter.TYPE, null);
    }

    @Test(expected = JsonParseException.class)
    public void shouldRaiseParseExceptionIfDateIsNotPrimitive() throws Exception {
        this.converter.deserialize(JsonNull.INSTANCE, IsoDateConverter.TYPE, null);
    }

    @Test
    public void shouldSerializeCorrectlyToIsoString() throws Exception {
        String deserialized = this.converter.serialize(VALID_DATE, IsoDateConverter.TYPE, null).toString();
        // Cleanups added "
        deserialized = deserialized.substring(1, deserialized.length() - 1);
        assertTrue(VALID_ISO_DATE.equals(deserialized));
    }

}
