//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.dong.service;

import com.dong.pojo.RelativeParkCard;
import java.util.List;
import java.util.Map;

public interface RelativeParkCardService {

    List<RelativeParkCard> getRelativeParkCard(Map<String, String> var1);

    RelativeParkCard getRelativeParkCardById(int var1);

    boolean deleteRelativeParkCard(int var1);

    RelativeParkCard addRelativeParkCard(RelativeParkCard var1);

    boolean updateRelativeParkCard(Map<String, String> params) ;
}
