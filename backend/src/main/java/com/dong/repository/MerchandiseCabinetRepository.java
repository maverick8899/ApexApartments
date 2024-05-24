package com.dong.repository;

import com.dong.pojo.MerchandiseCabinet;
import com.dong.pojo.MerchandiseCabinetDetail;

import java.util.List;
import java.util.Map;

public interface MerchandiseCabinetRepository {
    List<MerchandiseCabinetDetail> getMerchandiseCabinet(Map<String, String> var1);

    MerchandiseCabinet getMerchandiseCabinetById(int var1);

    MerchandiseCabinet addMerchandiseCabinet(MerchandiseCabinet var1);

    List<MerchandiseCabinetDetail> getMerchandiseByCustomerId(int customerId);
}
