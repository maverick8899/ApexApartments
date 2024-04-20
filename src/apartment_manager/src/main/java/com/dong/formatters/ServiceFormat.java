package com.dong.formatters;

import com.dong.pojo.Room;
import com.dong.pojo.Service;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ServiceFormat implements Formatter<Service> {
    @Override
    public String print(Service cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    @Override
    public Service parse(String roomId, Locale locale) throws ParseException {
        int id = Integer.parseInt(roomId);
        return new Service(id);
    }
}
