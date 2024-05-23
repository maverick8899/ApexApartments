//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.repository;

import com.dong.pojo.Merchandise;

public interface MerchandiseRepository {
    Merchandise getMerchandiseById(int var1);

    boolean addOrUpdateMerchandise(Merchandise var1);
}
