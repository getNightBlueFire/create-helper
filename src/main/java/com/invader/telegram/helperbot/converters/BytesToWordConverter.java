package com.invader.telegram.helperbot.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.invader.telegram.helperbot.model.Word;

import javax.annotation.Nullable;

public class BytesToWordConverter implements Converter<byte[], Word> {
    private final Jackson2JsonRedisSerializer<Word> serializer;

    public BytesToWordConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(Word.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public Word convert(@Nullable byte[] value) {
        return serializer.deserialize(value);
    }
}
