//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.service;

import com.dong.pojo.MerchandiseCabinet;
import com.dong.pojo.MerchandiseCabinetDetail;
import java.util.List;
import java.util.Map;

public interface MerchandiseCabinetService {
    List<MerchandiseCabinetDetail> getMerchandiseCabinet(Map<String, String> var1);

    MerchandiseCabinet getMerchandiseCabinetById(int var1);

    MerchandiseCabinet addMerchandiseCabinet(MerchandiseCabinet var1);

    List<MerchandiseCabinetDetail> getMerchandiseByCustomerId(int var1);
}
