package com.marcovicente.subeditor.io;

import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.model.Subtitle;
import com.marcovicente.subeditor.model.Subtitles;

import java.io.*;

public class SubtitlesWriter {

    String filename;
    Subtitles subtitles;

    public SubtitlesWriter(Subtitles subtitles, String filename) {
        this.filename = filename;
        this.subtitles = subtitles;
    }

    public void write() throws SubtitleWritingException {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new SubtitleWritingException("Could not find file: "+filename);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            for(Subtitle subtitle : subtitles.getSubtitles()){
                writeSubtitle(writer, subtitle);
                try {
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new SubtitleWritingException("Error writing subtitles");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSubtitle(BufferedWriter writer, Subtitle subtitle) throws SubtitleWritingException {

        if(writer == null || subtitle == null){
            return;
        }

        try {
            writer.write(subtitle.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new SubtitleWritingException("Error writing subtitle: "+subtitle.getId());
        }


    }
}
