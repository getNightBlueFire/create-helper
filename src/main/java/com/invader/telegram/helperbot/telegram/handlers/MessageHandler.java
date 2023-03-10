package com.invader.telegram.helperbot.telegram.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import com.invader.telegram.helperbot.api.dictionaries.DictionaryAdditionService;
import com.invader.telegram.helperbot.api.dictionaries.DictionaryExcelService;
import com.invader.telegram.helperbot.constants.bot.BotMessageEnum;
import com.invader.telegram.helperbot.constants.bot.ButtonNameEnum;
import com.invader.telegram.helperbot.constants.bot.CallbackDataPartsEnum;
import com.invader.telegram.helperbot.exceptions.DictionaryTooBigException;
import com.invader.telegram.helperbot.exceptions.TelegramFileNotFoundException;
import com.invader.telegram.helperbot.telegram.TelegramApiClient;
import com.invader.telegram.helperbot.telegram.keyboards.InlineKeyboardMaker;
import com.invader.telegram.helperbot.telegram.keyboards.ReplyKeyboardMaker;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MessageHandler {
    DictionaryAdditionService dictionaryAdditionService;
    DictionaryExcelService dictionaryExcelService;

    TelegramApiClient telegramApiClient;
    ReplyKeyboardMaker replyKeyboardMaker;
    InlineKeyboardMaker inlineKeyboardMaker;

    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();

        if (message.hasDocument()) {
            return addUserDictionary(chatId, message.getDocument().getFileId());
        }

        String inputText = message.getText();

        if (inputText == null) {
            throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
            return getStartMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_TASKS_BUTTON.getButtonName())) {
            return getTasksMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.GET_DICTIONARY_BUTTON.getButtonName())) {
            return getDictionaryMessage(chatId);
        } else if (inputText.equals(ButtonNameEnum.UPLOAD_DICTIONARY_BUTTON.getButtonName())) {
            return new SendMessage(chatId, BotMessageEnum.UPLOAD_DICTIONARY_MESSAGE.getMessage());
        } else if (inputText.equals(ButtonNameEnum.HELP_BUTTON.getButtonName())) {
            SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
            sendMessage.enableMarkdown(true);
            return sendMessage;
        } else {
            return new SendMessage(chatId, BotMessageEnum.NON_COMMAND_MESSAGE.getMessage());
        }
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.HELP_MESSAGE.getMessage());
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getTasksMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_DICTIONARY_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtons(
                CallbackDataPartsEnum.TASK_.name(),
                dictionaryExcelService.isUserDictionaryExist(chatId)
        ));
        return sendMessage;
    }

    private SendMessage getDictionaryMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, BotMessageEnum.CHOOSE_DICTIONARY_MESSAGE.getMessage());
        sendMessage.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtonsWithTemplate(
                CallbackDataPartsEnum.DICTIONARY_.name(),
                dictionaryExcelService.isUserDictionaryExist(chatId)
        ));
        return sendMessage;
    }

    private SendMessage addUserDictionary(String chatId, String fileId) {
        try {
            dictionaryAdditionService.addUserDictionary(chatId, telegramApiClient.getDocumentFile(fileId));
            return new SendMessage(chatId, BotMessageEnum.SUCCESS_UPLOAD_MESSAGE.getMessage());
        } catch (TelegramFileNotFoundException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TELEGRAM_API_MESSAGE.getMessage());
        } catch (DictionaryTooBigException e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_TOO_LARGE_DICTIONARY_MESSAGE.getMessage());
        } catch (Exception e) {
            return new SendMessage(chatId, BotMessageEnum.EXCEPTION_BAD_FILE_MESSAGE.getMessage());
        }
    }
}
