package com.ssafy.miraclebird.dao;


import com.ssafy.miraclebird.entity.Post;
import com.ssafy.miraclebird.entity.Verification;
import com.ssafy.miraclebird.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VerificationDaoImpl implements VerificationDao {
    private final VerificationRepository verificationRepository;

    @Autowired
    public VerificationDaoImpl(VerificationRepository verificationRepository){
        this.verificationRepository = verificationRepository;
    }

    @Override
    public List<Verification> getVerificationALL() {
        List<Verification> verificationEntity = verificationRepository.findAll();
        return verificationEntity;
    }

    @Override
    public Verification getVerificationById(long verificationIdx) {
        Verification verificationEntity = verificationRepository.getById(verificationIdx);
        return verificationEntity;
    }

    @Override
    public void saveVerification(Verification verification) throws Exception {
        try {
            verificationRepository.save(verification);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public Verification approveVerification(long verificationIdx, long updateApproval) throws Exception {
        Verification verificationEntity = verificationRepository.getById(verificationIdx);

        if(verificationEntity != null) {
            verificationEntity.setApproval(updateApproval);
            verificationRepository.save(verificationEntity);
            return verificationEntity;
        }
        else {
            throw new Exception();
        }
    }

    public void deleteVerificationInfo(long verificationIdx, long userIdx) throws Exception {
        //userIdx 비교 필요!!!!!!
        try {
            verificationRepository.deleteById(verificationIdx);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public List<Verification> getVerificationByPeriod() {
        List<Verification> verificationEntity = verificationRepository.findAll();
        return verificationEntity;
    }
}