package com.marcovicente.subeditor.model;

import com.marcovicente.subeditor.util.Util;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.marcovicente.subeditor.util.Constants.*;

public class Subtitle {

    int id;
    Date start;
    Date end;
    String text;

    public void add(String dateAsString){
        add(dateAsString, true);
        add(dateAsString, false);
    }

    public void add(String dateAsString, boolean addToStart){
        add(Util.getDateFromString(dateAsString), addToStart);
    }

    public void add(Date time){
        add(time, true);
        add(time, false);
    }

    public void add(Date time, boolean addToStart){
        change(time, addToStart, true);
    }

    public void subtract(String dateAsString){
        subtract(dateAsString, true);
        subtract(dateAsString, false);
    }

    public void subtract(String dateAsString, boolean addToStart){
        subtract(Util.getDateFromString(dateAsString), addToStart);
    }

    public void subtract(Date time){
        subtract(time, true);
        subtract(time, false);
    }

    public void subtract(Date time, boolean addToStart){
        change(time, addToStart, false);
    }

    private void change(Date time, boolean addToStart, boolean add){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        Calendar toChange = (Calendar) calendar.clone();
        toChange.setTime(addToStart?start:end);

        int[] fieldsToChange = new int[]{Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND};

        int factor = add?1:-1;

        for(int field : fieldsToChange){
            toChange.add(field, calendar.get(field)*factor);
        }

        if(addToStart){
            start = toChange.getTime();
        } else {
            end = toChange.getTime();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {

        return MessageFormat.format("{0}\n{1}\n{2}", String.valueOf(id), Util.formatIntervalAsString(start, end), text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subtitle subtitle = (Subtitle) o;

        if (getId() != subtitle.getId()) return false;
        if (getStart() != null ? !getStart().equals(subtitle.getStart()) : subtitle.getStart() != null) return false;
        if (getEnd() != null ? !getEnd().equals(subtitle.getEnd()) : subtitle.getEnd() != null) return false;
        return getText() != null ? getText().equals(subtitle.getText()) : subtitle.getText() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getStart() != null ? getStart().hashCode() : 0);
        result = 31 * result + (getEnd() != null ? getEnd().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        return result;
    }
}
