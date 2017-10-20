package com.marcovicente.subeditor.model;


import com.marcovicente.subeditor.exception.SubtitleReadingException;
import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.model.Subtitle;
import com.marcovicente.subeditor.model.Subtitles;
import com.marcovicente.subeditor.util.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


public class SubtitlesTest {

    @Before
    public void setup(){

    }

    @Test
    public void loadFromFileAndPrintOnSystemOut() throws SubtitleReadingException {
        String filename = "./resources/test.srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        assertEquals(435, subtitles.getSubtitles().size());
        System.out.println(subtitles);
    }

    @Test
    public void loadFromFileAndSaveToNewFile() throws SubtitleWritingException, SubtitleReadingException {
        String srcFilename = "./resources/test.srt";
        String destFilename = "./out/test.srt";

        Subtitles subtitles = Subtitles.fromFile(srcFilename);

        assertEquals(435, subtitles.getSubtitles().size());

        subtitles.toFile(destFilename);

        Subtitles generated = Subtitles.fromFile(destFilename);

        assertEquals(subtitles.toString(), generated.toString());

        assertEquals(subtitles, generated);

        assertEquals(subtitles.hashCode(), generated.hashCode());
    }

    @Test
    public void loadFromFileAndAddTime() throws SubtitleReadingException {
        String filename = "./resources/test.srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        subtitles.add("01:02:03,456");

        Subtitle first = subtitles.getSubtitles().get(0);

        checkDate(first.getStart(), 1, 2,5, 167);

        checkDate(first.getEnd(), 1, 2,7, 627);

        System.out.println(subtitles);
    }

    @Test
    public void loadFromFileAndSubtractTime() throws SubtitleReadingException {
        String filename = "./resources/test.srt";

        Subtitles subtitles = Subtitles.fromFile(filename);

        subtitles.subtract("01:02:03,456");

        Subtitle last = subtitles.getSubtitles().get(subtitles.getSubtitles().size()-1);

        checkDate(last.getStart(), 11, 19,32, 798);

        checkDate(last.getEnd(), 11, 19,33, 798);

        System.out.println(subtitles);
    }

    private void checkDate(Date time, int hours, int minutes, int seconds, int milliseconds){

        Calendar calendar = Util.getCalendar(time);

        int hh = calendar.get(Calendar.HOUR);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        int sss = calendar.get(Calendar.MILLISECOND);

        assertEquals(hours, hh);
        assertEquals(minutes, mm);
        assertEquals(seconds, ss);
        assertEquals(milliseconds, sss);
    }
}
