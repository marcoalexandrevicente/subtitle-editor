package com.marcovicente.subeditor;

import com.marcovicente.subeditor.exception.SubtitleReadingException;
import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.model.Subtitles;

public class Main {

    public static void main(String[] args) throws SubtitleWritingException, SubtitleReadingException {
        String filename = "./resources/test.srt";
        String destFilename = "./out/main.srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        subtitles.add("00:00:18,500");

        subtitles.toFile(destFilename);

        System.out.println(subtitles);
    }
}
