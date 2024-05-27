package com.dong.formatters;

import com.dong.pojo.Accounts;
import com.dong.pojo.Room;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AccountsFormatter implements Formatter<Accounts> {
    @Override
    public String print(Accounts cate, Locale locale) {
        return String.valueOf(cate.getId());
    }

    @Override
    public Accounts parse(String AccountsId, Locale locale) throws ParseException {
        int id = Integer.parseInt(AccountsId);
        return new Accounts(id);
    }
}
