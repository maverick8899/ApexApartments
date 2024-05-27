//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.service;

import com.dong.pojo.Merchandise;
import com.dong.pojo.MerchandiseCabinetDetail;

public interface MerchandiseService {
    Merchandise getMerchandiseById(int var1);

    boolean addOrUpdateMerchandise(Merchandise var1);
    boolean addOrUpdateMerchandiseCabinetDetail(MerchandiseCabinetDetail r);
}
