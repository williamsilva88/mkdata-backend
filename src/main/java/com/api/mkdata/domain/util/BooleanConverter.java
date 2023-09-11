package com.api.mkdata.domain.util;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean ? 1: 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer i) {
        if( i == 0 )
            return true;
        else if( i == 1 )
            return false;

        return null;
    }
}
