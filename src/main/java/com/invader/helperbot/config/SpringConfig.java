package com.invader.helperbot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import com.invader.helperbot.telegram.HelperMasterBot;
import com.invader.helperbot.telegram.handlers.CallbackQueryHandler;
import com.invader.helperbot.telegram.handlers.MessageHandler;

@Configuration
@AllArgsConstructor
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public HelperMasterBot springWebhookBot(SetWebhook setWebhook,
                                            MessageHandler messageHandler,
                                            CallbackQueryHandler callbackQueryHandler) {
        HelperMasterBot bot = new HelperMasterBot(setWebhook, messageHandler, callbackQueryHandler);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUsername(telegramConfig.getBotName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;
    }
}
