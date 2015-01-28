package com.aldoborrero.tinder.api.gson

import com.google.gson.JsonPrimitive
import spock.lang.Specification

class IsoDateConverterTestGroovy extends Specification {

    def String VALID_ISO_DATE = "2015-01-14T06:18:29.116Z";
    def Date VALID_DATE;

    def String INVALID_ISO_DATE = "invalid-date";

    def IsoDateConverter converter;

    def setup () {
        setupValidDate()
        setupIsoDateConverter()
    }

    def setupValidDate () {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
        c.setTimeInMillis(1421216309116L);
        VALID_DATE = c.getTime();
    }

    def setupIsoDateConverter () {
        converter = new IsoDateConverter();
    }

    def "should deserialize correctly the iso strings" () {
        setup:"Create the date variable to deserialize"
        Date deserialized;

        when:"We deserialize the date"
        deserialized = converter.deserialize(new JsonPrimitive(VALID_ISO_DATE), IsoDateConverter.TYPE, null);


        then:"The comparison should be correct"
        VALID_DATE.compareTo(deserialized) == 0;
    }

    def "should serialize correctly to iso string" () {
        setup:"Create the string variable to serialized"
        String deserialized;

        when:"We serialized the date"
        deserialized = converter.serialize(VALID_DATE, IsoDateConverter.TYPE, null).toString();
        deserialized = deserialized.substring(1, deserialized.length() - 1);


        then:"The comparison should be correct"
        VALID_ISO_DATE.equals(deserialized)
    }

}
