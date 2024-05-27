//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.repository;

import com.dong.pojo.RelativeParkCard;
import java.util.List;
import java.util.Map;

public interface RelativeParkCardRepository {
    List<RelativeParkCard> getRelativeParkCard(Map<String, String> var1);

    RelativeParkCard getRelativeParkCardById(int var1);

    boolean deleteRelativeParkCard(int var1);

    RelativeParkCard addRelativeParkCard(RelativeParkCard var1);
}
