package com.invader.helperbot.api.dictionaries;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import com.invader.helperbot.utils.FileUtils;
import com.invader.helperbot.utils.ResourceLoader;

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
