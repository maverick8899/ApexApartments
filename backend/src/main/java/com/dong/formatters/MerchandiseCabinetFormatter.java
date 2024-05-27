package com.dong.formatters;

import com.dong.pojo.Accounts;
import com.dong.pojo.MerchandiseCabinet;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class MerchandiseCabinetFormatter implements Formatter<MerchandiseCabinet> {
    @Override
    public String print(MerchandiseCabinet cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    @Override
    public MerchandiseCabinet parse(String MerchandiseCabinetId, Locale locale) throws ParseException {
        int id = Integer.parseInt(MerchandiseCabinetId);
        return new MerchandiseCabinet(id);
    }
}
