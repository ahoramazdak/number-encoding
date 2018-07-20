/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.tmputils;

/**
 *
 * @author amin
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

public class ReadWriteTextFile {

    /**
     * File reference to the resources directory.
     */
    private static final File EN_RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources" + File.separator + "en");
    final static String fr_words = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "words.txt";
    final static String fr_words_alpha = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "words_alpha.txt";
    final static String fr_words_au = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "wordlist_kevin_en_AU_20180416_123599w.txt";
    final static String frw_words_au = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "wordlist_en_au.txt";
    final static String frw_words_au_len_more_2 = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "wordlist_en_au_len_more_2.txt";
    final static int words_lc = 466544;
    final static int words_alpha_lc = 370098;
    final static int words_au_lc = 123599;

    final static String fw_words_20 = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_valid_dictionary_size_20_words.txt";
    final static String fw_words_alpha_20 = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_valid_dictionary_size_20_words_alpha.txt";
    final static String fw_words_au_20 = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_valid_dictionary_size_20_words_au.txt";
    final static String fw_words_73113 = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_valid_dictionary_size_73113.txt";
    final static String fw_words_73113_invalid_word_length = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_dictionary_size_73113_with_invalid_word_length.txt";
    final static String fw_words_83113_invalid_dic_size = EN_RESOURCES_DIRECTORY.getAbsolutePath() + File.separator + "en_invalid_dictionary_size_83113.txt";

    public static void main(String[] args) throws IOException {
        ReadWriteTextFile rwtf=new ReadWriteTextFile();
        ReadWriteTextFile.setLcme(0);
        readFile(frw_words_au_len_more_2);
//        readWriteFile(fr_words_au,new FileWriter(frw_words_au_len_more_2));
//        readWriteFile(fr_words_au,new FileWriter(frw_words_au));
        FileWriter fileWriter;
//        fileWriter = new FileWriter(fw_words_20);
//        makeTestFile(fr_words, words_lc, fileWriter, 20);
//        System.out.println("Generated " + fw_words_20);
//        fileWriter = new FileWriter(fw_words_alpha_20);
//        makeTestFile(fr_words_alpha, words_alpha_lc, fileWriter, 20);
//        System.out.println("Generated " + fw_words_alpha_20);
//        fileWriter = new FileWriter(fw_words_au_20);
//        makeTestFile(fr_words_au, words_au_lc, fileWriter, 20);
//        System.out.println("Generated " + fw_words_au_20);
//        fileWriter = new FileWriter(fw_words_73113);
//        makeTestFile(fr_words, words_lc, fileWriter, 73113);
//        System.out.println("Generated " + fw_words_73113);
//        fileWriter = new FileWriter(fw_words_83113_invalid_dic_size);
//        makeTestFile(fr_words, words_lc, fileWriter, 83113);
//        System.out.println("Generated " + fw_words_73113_invalid_word_length);
        
    }
    static int[] nlines;

    public static void makeTestFile(String fr, int frLineCounts, FileWriter fw, int lines) throws IOException {
        nlines = new Random().ints(1, frLineCounts).distinct().limit(lines).sorted().toArray();
        try (PrintWriter pw = new PrintWriter(fw)) {
            for (int nline : nlines) {
                try (Stream<String> rlines = Files.lines(Paths.get(fr))) {
                    String readLine = rlines.skip(nline - 1).findFirst().get();
                    System.out.println(nline + " : " + readLine);
                    pw.write(readLine + "\n");
                }
            }
        }
    }

    private static int lcme;

    static String formatMe(String str){

        return String.format(" %d : %s",++lcme,str);
    }
    public static void readFile(String filepath) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
            lines.filter((line) -> {
                return line.matches("[AINYXSZ^ainyxsz|ainyxsz^AINYXSZ](ME)|(AM)|(HE)|(OR)|\\w{3,50}");
            }).map(String::toUpperCase).sorted().distinct().map(ReadWriteTextFile::formatMe).forEach(System.out::println);

        }

    }

    public static void readWriteFile(String filepath, FileWriter fileW) throws IOException {
        try (PrintWriter pw = new PrintWriter(fileW)) {
            try (Stream<String> lines = Files.lines(Paths.get(filepath))) {
                lines.filter((line) -> {
                    return line.matches("[AINYXSZ^ainyxsz|ainyxsz^AINYXSZ]|(ME)|(AM)|(HE)|(OR)|\\w{3,50}");
                }).map(String::toUpperCase).sorted().distinct().forEach(pw::println);

            }
        }

    }

    /**
     * @return the lcme
     */
    public static int getLcme() {
        return lcme;
    }

    /**
     * @param aLcme the lcme to set
     */
    public static void setLcme(int aLcme) {
        lcme = aLcme;
    }

}