package com.marcovicente.subeditor.io;

import com.marcovicente.subeditor.exception.SubtitleReadingException;
import com.marcovicente.subeditor.model.Subtitle;
import com.marcovicente.subeditor.model.Subtitles;
import com.marcovicente.subeditor.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.marcovicente.subeditor.util.Constants.*;

public class SubtitlesReader {

    String filename;

    public SubtitlesReader(String filename) {
        this.filename = filename;
    }

    public Subtitles read() throws SubtitleReadingException {

        List<Subtitle> subtitles = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1"))) {

            List<String> lines = br.lines().collect(Collectors.toList());

            lines = removeEverythingUntilFirstChar(lines);

            List<String> tmp = new ArrayList<>();

            for (String line : lines) {

                if (isEmptyLine(line)) {
                    Subtitle subtitle = getSubtitle(tmp);
                    subtitles.add(subtitle);

                    tmp = new ArrayList<>();
                } else {
                    tmp.add(line);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return new Subtitles(subtitles);
    }

    private List<String> removeEverythingUntilFirstChar(List<String> lines) {
        int idxChar = 0;
        int idxLine = 0;

        boolean found = false;

        for(String line : lines){
            for(char ch : line.toCharArray()){
                if(isDigit(ch)){
                    found = true;
                    break;
                }

                idxChar++;
            }
            if(found){
                break;
            }
            idxLine++;
        }

        if(idxLine != 0){
            lines.subList(0, idxLine).clear();
        }

        if(idxChar != 0){
            lines.set(0, lines.get(0).substring(idxChar));
        }

        return lines;
    }

    private boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }

    private static boolean isEmptyLine(String line) {

        return line != null && "".equals(line.trim());
    }

    private static Subtitle getSubtitle(List<String> lines) throws SubtitleReadingException {
        try {
            Subtitle subtitle = new Subtitle();
            int id = getId(lines.get(0).trim());
            subtitle.setId(id);
            Date start = getStart(lines.get(1));
            Date end = getEnd(lines.get(1));
            subtitle.setStart(start);
            subtitle.setEnd(end);
            String text = getText(lines);
            subtitle.setText(text);
            return subtitle;
        } catch (Exception e) {
            e.printStackTrace();
            String linesAsString = String.join("\n", lines);
            throw new SubtitleReadingException("Error reading subtitle from file.\n"+linesAsString);
        }
    }

    private static int getId(String line) {
        return Integer.parseInt(line);
    }

    private static Date getStart(String line) {
        String dateStr = line.substring(0, line.indexOf(DATE_SEPARATOR));

        return Util.getDateFromString(dateStr);
    }

    private static Date getEnd(String line) {
        String dateStr = line.substring(line.indexOf(DATE_SEPARATOR) + DATE_SEPARATOR.length());

        return Util.getDateFromString(dateStr);
    }

    private static String getText(List<String> lines) {
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < lines.size(); i++) {
            sb.append(lines.get(i));
            sb.append("\n");
        }

        return sb.toString();
    }
}
