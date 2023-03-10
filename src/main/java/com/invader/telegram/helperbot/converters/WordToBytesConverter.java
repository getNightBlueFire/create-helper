package com.invader.telegram.helperbot.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import com.invader.telegram.helperbot.model.Word;

import javax.annotation.Nullable;

public class WordToBytesConverter implements Converter<Word, byte[]> {
    private final Jackson2JsonRedisSerializer<Word> serializer;

    public WordToBytesConverter() {
        serializer = new Jackson2JsonRedisSerializer<>(Word.class);
        serializer.setObjectMapper(new ObjectMapper());
    }

    @Override
    public byte[] convert(@Nullable Word value) {
        return serializer.serialize(value);
    }
}
