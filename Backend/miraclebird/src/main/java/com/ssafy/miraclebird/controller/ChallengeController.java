package com.ssafy.miraclebird.controller;

import com.ssafy.miraclebird.dto.ChallengeDto;
import com.ssafy.miraclebird.service.ChallengeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@Api("챌린지 미션 관련 REST V1")
public class ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @ApiOperation(value = "모든 challenge의 정보를 반환한다.", response = ChallengeDto.class)
    @GetMapping("/")
    public ResponseEntity<List<ChallengeDto>> getChallengeALL() {
        List<ChallengeDto> result = challengeService.getChallengeALL();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "특정 challenge의 정보를 반환한다.", response = ChallengeDto.class)
    @GetMapping("/{challenge_idx}")
    public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable("challenge_idx") Long challengeIdx) {
        ChallengeDto result = challengeService.getChallengeById(challengeIdx);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}