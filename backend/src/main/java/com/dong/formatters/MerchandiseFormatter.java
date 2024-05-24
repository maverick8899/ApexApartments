//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.formatters;

import com.dong.pojo.Merchandise;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class MerchandiseFormatter implements Formatter<Merchandise> {
    public MerchandiseFormatter() {
    }

    public String print(Merchandise cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    public Merchandise parse(String roomId, Locale locale) throws ParseException {
        int id = Integer.parseInt(roomId);
        return new Merchandise(id);
    }
}
