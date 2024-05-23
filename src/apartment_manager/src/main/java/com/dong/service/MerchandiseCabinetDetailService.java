//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.service;

import com.dong.pojo.MerchandiseCabinetDetail;
import java.util.List;

public interface MerchandiseCabinetDetailService {
    List<MerchandiseCabinetDetail> getMerchandiseCabinetDetail();

    MerchandiseCabinetDetail getMerchandiseCabinetDetailById(int var1);

    boolean addOrUpdateMerchandiseCabinetDetail(MerchandiseCabinetDetail var1);

    boolean deleteMerchandiseCabinetDetail(int var1);
}
