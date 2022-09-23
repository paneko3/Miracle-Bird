package com.ssafy.miraclebird.service;

import com.ssafy.miraclebird.dao.*;
import com.ssafy.miraclebird.dto.VerificationDto;
import com.ssafy.miraclebird.entity.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final VerificationDao verificationDao;
    private final UserDao userDao;
    private final ChallengeDao challengeDao;

    @Autowired
    public VerificationServiceImpl(VerificationDao verificationDao, UserDao userDao, ChallengeDao challengeDao) {
        this.verificationDao = verificationDao;
        this.userDao = userDao;
        this.challengeDao = challengeDao;
    }

    @Override
    @Transactional
    public List<VerificationDto> getVerificationALL() {
        List<Verification> verificationEntity = verificationDao.getVerificationALL();
        List<VerificationDto> verificationDtos = new ArrayList<>();
        for (int i = 0; i < verificationEntity.size(); i++) {
            verificationDtos.add(VerificationDto.of(verificationEntity.get(i)));
        }
        return verificationDtos;
    }

    @Override
    @Transactional
    public VerificationDto getVerificationById(long verificationId) {
        Verification verificationEntity = verificationDao.getVerificationById(verificationId);
        VerificationDto verificationDto = VerificationDto.of(verificationEntity);
        return verificationDto;
    }

    @Override
    @Transactional
    public void uploadVerification(VerificationDto verificationDto) throws Exception {
        try {
            Verification verificationEntity = new Verification();
            verificationEntity.setChallenge(challengeDao.getChallengeById(verificationDto.getChallengeIdx()));
            verificationEntity.setRegtime(LocalDateTime.now());
            verificationEntity.setSelfie(verificationDto.getSelfie());
            verificationEntity.setUser(userDao.getUserById(verificationDto.getUserIdx()));
            verificationDao.saveVerification(verificationEntity);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public VerificationDto approveVerification(long verificationId, long updateApproval) throws Exception {
        Verification verificationEntity = verificationDao.approveVerification(verificationId, updateApproval);
        VerificationDto verificationDto = VerificationDto.of(verificationEntity);
        return verificationDto;
    }

    @Override
    @Transactional
    public void deleteVerificationInfo(long verificationId, long userId) throws Exception {
        verificationDao.deleteVerificationInfo(verificationId, userId);
//        VerificationDto verificationDto = VerificationDto.of(verificationEntity);
//        return verificationDto;
        return;
    }

    @Override
    @Transactional
    public List<VerificationDto> getVerificationByPeriod(Long userIdx, LocalDateTime startDate, LocalDateTime endDate) {
        List<Verification> verificationEntity = verificationDao.getVerificationByPeriod(userIdx, startDate, endDate);
        List<VerificationDto> verificationDtos = new ArrayList<>();
        for (int i = 0; i < verificationEntity.size(); i++) {
            verificationDtos.add(VerificationDto.of(verificationEntity.get(i)));
        }
        return verificationDtos;
    }

    @Override
    @Transactional
    public List<String> getRankByCount() {
        List<String> stringEntity = verificationDao.getRankByCount();
        return stringEntity;
    }

    @Override
    @Transactional
    public List<String> getRankByStreak() {
        List<String> stringEntity = verificationDao.getRankByStreak();
        return stringEntity;
    }
}
