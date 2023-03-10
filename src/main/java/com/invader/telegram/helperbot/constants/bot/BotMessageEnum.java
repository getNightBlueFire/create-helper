package com.invader.telegram.helperbot.constants.bot;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum BotMessageEnum {
	//ответы на команды с клавиатуры
	HELP_MESSAGE("\uD83D\uDC4B Привет" +
			"❗ *Что Вы можете сделать:*\n" +
			"Удачи!\n\n" +
			"Воспользуйтесь клавиатурой, чтобы начать работу\uD83D\uDC47"),
	CHOOSE_DICTIONARY_MESSAGE("Выберите словарь\uD83D\uDC47 "),
	UPLOAD_DICTIONARY_MESSAGE("Добавьте файл, соответствующий шаблону. Вы можете сделать это в любой момент"),
	NON_COMMAND_MESSAGE("Пожалуйста, воспользуйтесь клавиатурой\uD83D\uDC47"),

	//результаты загрузки словаря
	SUCCESS_UPLOAD_MESSAGE("\uD83D\uDC4D Словарь успешно загружен"),
	EXCEPTION_TELEGRAM_API_MESSAGE("Ошибка при попытку получить файл из API Telegram"),
	EXCEPTION_TOO_LARGE_DICTIONARY_MESSAGE("В словаре больше 1 000 слов. Едва ли такой большой набор словарных " +
			"слов действительно нужен, ведь я работаю для обучения детей"),
	EXCEPTION_BAD_FILE_MESSAGE("Файл не может быть обработан. Вы шлёте мне что-то не то, балуетесь, наверное"),

	//ошибки при обработке callback-ов
	EXCEPTION_BAD_BUTTON_NAME_MESSAGE("Неверное значение кнопки. Крайне странно. Попробуйте позже"),
	EXCEPTION_DICTIONARY_NOT_FOUND_MESSAGE("Словарь не найден"),
	EXCEPTION_DICTIONARY_WTF_MESSAGE("Нежиданная ошибка при попытке получить словарь. Сам в шоке"),
	EXCEPTION_TASKS_WTF_MESSAGE("Нежиданная ошибка при попытке получить задания. Сам в шоке"),
	EXCEPTION_TEMPLATE_WTF_MESSAGE("Нежиданная ошибка при попытке получить шаблон. Сам в шоке"),

	//прочие ошибки
	EXCEPTION_ILLEGAL_MESSAGE("Нет, к такому меня не готовили! Я работаю или с текстом, или с файлом"),
	EXCEPTION_WHAT_THE_FUCK("Что-то пошло не так. Обратитесь к программисту");

	private final String message;

	BotMessageEnum (String message) {
		this.message = message;
	}

	public String getMessage () {
		return message;
	}
}
