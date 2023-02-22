package com.invader.telegram.helperbot.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import com.invader.telegram.helperbot.utils.FileUtils;
import com.invader.telegram.helperbot.utils.ResourceLoader;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DictionaryResourceFileService {
    private final ResourceLoader resourceLoader;

    public ByteArrayResource getTemplateWorkbook() throws IOException {
        return FileUtils.createOfficeDocumentResource(
                resourceLoader.loadTemplateWorkbook(),
                "Template",
                "xlsx");
    }
}
