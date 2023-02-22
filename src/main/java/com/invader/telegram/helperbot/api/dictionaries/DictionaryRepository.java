package com.invader.telegram.helperbot.api.dictionaries;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.invader.telegram.helperbot.model.Dictionary;

@Repository
public interface DictionaryRepository  extends CrudRepository<Dictionary, String> {
}
