package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        Faker faker = new Faker(new Locale("ru"));

        public static RegistrationInfo generateInfo() {
            return new RegistrationInfo(generateCity(), generateName(), generatePhone());
        }

        public static String generateDate(int addDays) {
            return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String generateCity() {
            String[] regionCentre = {"Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск"};
            return regionCentre[new Random().nextInt(regionCentre.length)];
        }

        public static String generateName() {
            return faker.name().lastName() + " " + faker.name().firstName();
        }

        public static String generatePhone() {
            return faker.phoneNumber().phoneNumber();
        }
    }

}
