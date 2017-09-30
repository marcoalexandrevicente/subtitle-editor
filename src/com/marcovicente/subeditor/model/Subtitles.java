package com.marcovicente.subeditor.model;

import com.marcovicente.subeditor.exception.SubtitleWritingException;
import com.marcovicente.subeditor.io.SubtitlesReader;
import com.marcovicente.subeditor.io.SubtitlesWriter;
import com.marcovicente.subeditor.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subtitles {

    List<Subtitle> subtitles = new ArrayList<>();

    public Subtitles(){

    }

    public Subtitles(List<Subtitle> subtitles){
        this.subtitles = subtitles;
    }

    public static Subtitles fromFile(String filename){

        SubtitlesReader reader = new SubtitlesReader(filename);
        return reader.read();
    }

    public void toFile(String filename) throws SubtitleWritingException {
        SubtitlesWriter writer = new SubtitlesWriter(this, filename);
        writer.write();
    }

    public void add(String dateAsString){
        for(Subtitle subtitle : subtitles){
            subtitle.add(dateAsString);
        }
    }

    public void add(Date time){
        for(Subtitle subtitle : subtitles){
            subtitle.add(time);
        }
    }

    public void subtract(String dateAsString){
        for(Subtitle subtitle : subtitles){
            subtitle.subtract(dateAsString);
        }
    }

    public void subtract(Date time){
        for(Subtitle subtitle : subtitles){
            subtitle.subtract(time);
        }
    }

    public List<Subtitle> getSubtitles() {
        return subtitles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Subtitle subtitle : subtitles){
            sb.append(subtitle);
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subtitles subtitles1 = (Subtitles) o;

        if(getSubtitles() == null && subtitles1.getSubtitles() == null){
            return true;
        }

        if(getSubtitles() == null || subtitles1.getSubtitles() == null){
            return false;
        }

        if (getSubtitles().size() != subtitles1.getSubtitles().size()){
            return false;
        }

        for(short i = 0; i < getSubtitles().size(); i++){
            Subtitle subtitle = getSubtitles().get(i);
            Subtitle subtitle1 = subtitles1.getSubtitles().get(i);

            if(subtitle == null && subtitle1 == null){
                continue;//equals
            } else if(subtitle == null || subtitle1 == null || !subtitle.equals(subtitle1)){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;

        for(Subtitle subtitle : subtitles){
            result = 31 * result + (subtitle != null ? subtitle.hashCode() : 0);
        }

        return result;
    }
}
