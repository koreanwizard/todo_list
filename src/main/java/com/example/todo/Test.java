package com.example.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {


        LocalDateTime dt = LocalDateTime.of(2025, 3, 24, 8, 30);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");

        String formattedDate = dt.format(myFormatObj);
        System.out.println("After Formatting: " + formattedDate);


    }
}
