//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.service.impl;

import com.dong.pojo.MerchandiseCabinet;
import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.repository.MerchandiseCabinetRepository;
import com.dong.service.MerchandiseCabinetService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseCabinetServiceImpl implements MerchandiseCabinetService {
    @Autowired
    private MerchandiseCabinetRepository merCaRep;

    public MerchandiseCabinetServiceImpl() {
    }

    public List<MerchandiseCabinetDetail> getMerchandiseCabinet(Map<String, String> params) {
        return this.merCaRep.getMerchandiseCabinet(params);
    }

    public MerchandiseCabinet getMerchandiseCabinetById(int id) {
        return null;
    }

    public MerchandiseCabinet addMerchandiseCabinet(MerchandiseCabinet r) {
        return null;
    }

    public List<MerchandiseCabinetDetail> getMerchandiseByCustomerId(int customerId) {
        return this.merCaRep.getMerchandiseByCustomerId(customerId);
    }
}
