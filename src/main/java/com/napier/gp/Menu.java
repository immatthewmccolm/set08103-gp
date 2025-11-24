///*
// * Menu.java
// * Class file for handling User Input within the programme
// */
//
//package com.napier.gp;
//
///**
// * Class for handling code relating to menus and general formatting throughout the program.
// */
//public class Menu {
//
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//
//    /**
//     * Takes a title as a string and prints it to the console formatted as a page header. Additionaly it clears the console for a clean user experience.
//     * @param title page title that is displayed
//     */
//    static void pageTitle(String title) {
//        String line = "======================================";
//
//        System.out.println(ANSI_CYAN + line);
//        System.out.println(title);
//        System.out.println(line + ANSI_RESET);
//    }
//
//    /**
//     * Takes id as an int and title as a string and prints to the console a menu item in a consistent style.
//     * @param id number that is associated with menu item
//     * @param title title that is associated with menu item
//     */
//    static void menuItem(int id, String title) {
//        System.out.println(ANSI_RED + "["+id+"]\t"+ ANSI_RESET + title);
//    }
//}
