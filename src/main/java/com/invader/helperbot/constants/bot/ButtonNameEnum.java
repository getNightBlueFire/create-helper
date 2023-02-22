package com.invader.helperbot.constants.bot;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ButtonNameEnum {
    GET_TASKS_BUTTON("Создать чтонибуть"),
    GET_DICTIONARY_BUTTON("Скачать текст"),
    UPLOAD_DICTIONARY_BUTTON("Загрузить текст"),
    HELP_BUTTON("Помощь");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
