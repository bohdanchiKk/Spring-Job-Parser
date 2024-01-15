package com.example.springjobparser;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "JavaJobParserBot";
    }

    @Override
    public String getBotToken() {
        return "6521264168:AAFmaP9ZV6pYQz14Of2o04DpdMIYjEGmaIA";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}


