package com.marcovicente.subeditor;

import com.marcovicente.subeditor.exception.SubtitleReadingException;
import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.model.Subtitles;

public class Main {

    public static void main(String[] args) throws SubtitleWritingException, SubtitleReadingException {
        String filename = "./resources/test.srt";
        String destFilename = "./out/main.srt";

        // filename ="/Users/marcovicente/Downloads/Fargo Season 3 Complete 720p.HDTV.x264.[FREDDY1714]/Fargo.S03E02.720p.HDTV.x264.[FREDDY1714].org.srt";

        // destFilename = "/Users/marcovicente/Downloads/Fargo Season 3 Complete 720p.HDTV.x264.[FREDDY1714]/Fargo.S03E02.720p.HDTV.x264.[FREDDY1714].srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        subtitles.add("00:00:18,500");

        // subtitles.setRelativeSpeed(1.007);

        subtitles.toFile(destFilename);

        System.out.println(subtitles);
    }
}
