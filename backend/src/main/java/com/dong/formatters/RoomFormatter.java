package com.dong.formatters;
import com.dong.pojo.Room;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RoomFormatter implements Formatter<Room> {
    @Override
    public String print(Room cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    @Override
    public Room parse(String roomId, Locale locale) throws ParseException {
        int id = Integer.parseInt(roomId);
        return new Room(id);
    }
}
