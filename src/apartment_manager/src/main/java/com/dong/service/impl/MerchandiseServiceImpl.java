//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.service.impl;

import com.dong.pojo.Merchandise;
import com.dong.repository.MerchandiseRepository;
import com.dong.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {
    @Autowired
    private MerchandiseRepository MerRep;

    public MerchandiseServiceImpl() {
    }

    public Merchandise getMerchandiseById(int id) {
        return this.MerRep.getMerchandiseById(id);
    }

    public boolean addOrUpdateMerchandise(Merchandise r) {
        return this.MerRep.addOrUpdateMerchandise(r);
    }
}
