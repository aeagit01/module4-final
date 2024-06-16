package com.javarush.converter;

import com.javarush.domain.Continent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContinentToInt implements AttributeConverter<Continent, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Continent continent) {
        return continent != null ? continent.getValue() : null;
    }

    @Override
    public Continent convertToEntityAttribute(Integer value) {
        return value != null ? Continent.fromValue(value) : null;
    }

}
