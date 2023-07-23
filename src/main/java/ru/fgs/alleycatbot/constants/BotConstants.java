package ru.fgs.alleycatbot.constants;

import java.util.List;
import java.util.Random;

public class BotConstants {

    public static final String START = "/start";
    public static final String ON_THE_POINT = "/onthespot";
    public static final String HELP = "/help";
    public static final String TOP = "/top";
    public static final String DELETE = "/delete112233";
    public static final String VISITED_POINTS = "/visitedpoints112233";
    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String UNDERSCORE = "_";
    public static final String ENTER_CODE = "Введи код точки: ";
    public static final String BEST_OF_THE_BESTS = "Лучшие: ";
    public static final String HELP_MESSAGE = """
            Давай еще раз объясню, ты же особенный:
            /start - нажал 1 раз, получил точку куда надо ехать, нашел на точке стикер с кодом, ввел следующую команду.
            /onthespot - нажал, ввел код, получил вопрос, ответил на него и получил следующую точку.
            
            Ты пробился? Потерялся? Тебе 40, а ты все катаешь на фиксе? Крч по всем вопросам пиши сюда @I2l2l2I2
            """;
    public static final String INCORRECT_CODE = "Это что за код? Ты меня наебать хочешь?";
    public static final String NEXT_POINT = "Твоя следующая точка: ";
    public static final String CORRECT_ANSWER = "Молодец, все правильно. ";
    public static final String INCORRECT_ANSWER = "Ты дубина, не верно, ладно похуй. ";
    public static final List<String> ALREADY_SAY_HELLO_LIST = List.of("Мы с тобой уже здоровались, дружок",
            "Да да привет");
    public static final List<String> ALREADY_WAS_HERE_LIST = List.of("Ты уже был здесь",
            "Пиздуй дальше",
            "Ты че назад приехал?",
            "Ты ввел код точки, на которой уже был");
    public static final List<String> UNEXPECTED_COMMAND_LIST = List.of("Нихуя не понимаю че тебе надо. Доедешь до финиша, побазарим",
            "Это ты мне?",
            "肏你妈",
            "Не вразумляю шо ты хочишь");
    public static final Random RANDOM = new Random();

}
