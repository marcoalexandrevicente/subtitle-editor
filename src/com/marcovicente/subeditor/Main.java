package com.marcovicente.subeditor;

import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.model.Subtitles;

public class Main {

    public static void main(String[] args) throws SubtitleWritingException {
        String filename = "./src/com/marcovicente/subeditor/resources/test.srt";
        String destFilename = "./out/main.srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        subtitles.add("01:02:03,456");

        subtitles.toFile(destFilename);

        System.out.println(subtitles);
    }
}
