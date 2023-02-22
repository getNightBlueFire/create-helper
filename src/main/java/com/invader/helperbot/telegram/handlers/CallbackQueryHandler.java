package com.invader.helperbot.telegram.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import com.invader.helperbot.api.dictionaries.DictionaryExcelService;
import com.invader.helperbot.api.dictionaries.DictionaryResourceFileService;
import com.invader.helperbot.api.tasks.TaskService;
import com.invader.helperbot.constants.bot.BotMessageEnum;
import com.invader.helperbot.constants.bot.CallbackDataPartsEnum;
import com.invader.helperbot.constants.resources.DictionaryResourcePathEnum;
import com.invader.helperbot.exceptions.UserDictionaryNotFoundException;
import com.invader.helperbot.telegram.TelegramApiClient;

import java.io.IOException;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CallbackQueryHandler {
    TelegramApiClient telegramApiClient;
    TaskService taskService;
    DictionaryExcelService dictionaryExcelService;
    DictionaryResourceFileService dictionaryResourceFileService;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {
        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        if (data.equals(CallbackDataPartsEnum.TASK_.name() + CallbackDataPartsEnum.USER_DICTIONARY.name())) {
            return getDictionaryTasks(chatId, chatId, "personal dictionary");
        } else if (data.equals(CallbackDataPartsEnum.TASK_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            return getAllDictionaryTasks(chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.USER_DICTIONARY.name())) {
            return getDictionary(chatId, chatId);
        } else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.ALL_GRADES.name())) {
            return getAllDefaultDictionaries(chatId);
        }else if (data.equals(CallbackDataPartsEnum.DICTIONARY_.name() + CallbackDataPartsEnum.TEMPLATE.name())) {
            return getTemplate(chatId);
        } else {
            return handleDefaultDictionary(chatId, data);
        }
    }

    private SendMessage handleDefaultDictionary(String chatId, String data) throws IOException {
        if (data.startsWith(CallbackDataPartsEnum.TASK_.name())) {
            DictionaryResourcePathEnum resourcePath = DictionaryResourcePathEnum.valueOf(
                    data.substring(CallbackDataPartsEnum.TASK_.name().length())
            );
            return getDictionaryTasks(chatId, resourcePath.name(), resourcePath.getFileName());
        } else if (data.startsWith(CallbackDataPartsEnum.DICTIONARY_.name())) {
            return getDictionary(chatId, data.substring(CallbackDataPartsEnum.DICTIONARY_.name().length()));
        } else {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_BUTTON_NAME_MESSAGE.getMessage());
        }
    }

    private SendMessage getDictionaryTasks(String chatId, String dictionaryId, String fileName) throws IOException {
        try {
            //telegramApiClient.uploadFile(chatId, taskService.getTasksDocument(dictionaryId, fileName));
            return new SendMessage(chatId, "2Простите я только сегодня появился, еще ничего не умею");

        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TASKS_WTF_MESSAGE.getMessage());
        }

    }

    private SendMessage getAllDictionaryTasks(String chatId) throws IOException {
        try {
            //telegramApiClient.uploadFile(chatId, taskService.getAllDefaultDictionariesTasksDocument());
            return new SendMessage(chatId, "1Простите я только сегодня появился, еще ничего не умею");
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TASKS_WTF_MESSAGE.getMessage());
        }
    }

    private SendMessage getDictionary(String chatId, String dictionaryId) {
        try {
            //telegramApiClient.uploadFile(chatId, dictionaryExcelService.getDictionaryWorkbook(dictionaryId));
            return new SendMessage(chatId, "3Простите я только сегодня появился, еще ничего не умею");
        } catch (UserDictionaryNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_WTF_MESSAGE.getMessage());
        }
    }

    private SendMessage getAllDefaultDictionaries(String chatId) {
        try {
            //telegramApiClient.uploadFile(chatId, dictionaryExcelService.getAllDefaultDictionariesWorkbook());
            return new SendMessage(chatId, "Простите я только сегодня появился, еще ничего не умею");
        } catch (UserDictionaryNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_DICTIONARY_WTF_MESSAGE.getMessage());
        }
    }

    private SendMessage getTemplate(String chatId) {
        try {
            //telegramApiClient.uploadFile(chatId, dictionaryResourceFileService.getTemplateWorkbook());
            return new SendMessage(chatId, "Простите я только сегодня появился, еще ничего не умею");
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TEMPLATE_WTF_MESSAGE.getMessage());
        }

    }
}
