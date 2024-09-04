/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mytelegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.ArrayList;
import java.util.List;
public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@PruebaAngarBot";
    }

    @Override
    public String getBotToken() {
        return "7424887337:AAHK3Mmfh16-nxTxtL-yKebOCsVio80tsAY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
             String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(Long.toString(chatId));
            switch (messageText) {
                case "/start":
                    message.setText("¡Bienvenido a la Tienda Gameplanet! \n ¿Qué desea hacer? ");
                    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                    row.add("Comprar o informacion sobre un videojuego.");
                    row.add("Informacion sobre la empresa y el bot");
                    keyboard.add(row);

                    keyboardMarkup.setKeyboard(keyboard);
                    message.setReplyMarkup(keyboardMarkup);
                    break;
                case "/help":
                    message.setText("Estos son los comandos que puedes usar:\n/start - Iniciar\n/help - Obtener ayuda");
                    break;
                case "/nobody":
                    message.setText("Nadie puede ayudarme");
                    break;
                default:
                    message.setText("No entiendo ese comando. Usa /help para ver los comandos disponibles.");
                    break;
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            
        }
    }
}
