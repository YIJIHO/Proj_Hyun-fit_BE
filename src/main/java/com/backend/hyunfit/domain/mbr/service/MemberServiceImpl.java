package com.backend.hyunfit.domain.mbr.service;

import com.backend.hyunfit.domain.exch.dto.DailyRecordDTO;
import com.backend.hyunfit.domain.exctg.dto.ExerciseTargetDTO;
import com.backend.hyunfit.domain.exctg.mapper.ExerciseTargetMapper;
import com.backend.hyunfit.domain.mbr.dto.MemberDTO;
import com.backend.hyunfit.domain.mbr.mapper.MemberMapper;
import com.backend.hyunfit.domain.pt.dto.PersonalTrainingDTO;
import com.backend.hyunfit.domain.exch.dto.ExerciseHistorySummaryDTO;
import com.backend.hyunfit.domain.exch.mapper.ExerciseHistoryMapper;
import com.backend.hyunfit.domain.trnf.dto.TrainerFeedbackDTO;
import com.backend.hyunfit.domain.trnf.mapper.TrainerFeedbackMapper;
import com.backend.hyunfit.global.dto.SearchDTO;
import com.backend.hyunfit.global.exception.BusinessException;
import com.backend.hyunfit.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final ExerciseHistoryMapper exerciseHistoryMapper;
    private final ExerciseTargetMapper exerciseTargetMapper;
    private final TrainerFeedbackMapper trainerFeedbackMapper;

    @Transactional
    @Override
    public void updateOneMemberById(MemberDTO memberDTO) {
        // 멤버 mbrId 조회 검증
        selectOneMemberById(memberDTO);

        int updateResult = memberMapper.updateOneMemberById(memberDTO);
        if (updateResult == 0) {
            throw BusinessException.of(ErrorCode.DB_QUERY_UPDATE_EXCEPTION);
        }
    }

    public MemberDTO selectOneMemberById(MemberDTO memberDTO) {
        return selectOneMemberById(memberDTO.getMbrId());
    }

    @Override
    public MemberDTO selectOneMemberById(String mbrId) {
        MemberDTO memberDTO = memberMapper.selectOneMemberById(mbrId)
                .orElseThrow(BusinessException.supplierOf(ErrorCode.USERID_NOT_FOUND));
        // 사용자 암호 null 처리
        memberDTO.setMbrPw(null);
        return memberDTO;
    }


    @Override
    public MemberDTO selectAllMemberPtBySeq(long mbrSeq, int offSet, int limit) {
        log.info("=============== selectAllMemberPtBySeq : Service " + mbrSeq);
        MemberDTO memberDTO = memberMapper.selectOneMemberBySeq(mbrSeq)
                .orElseThrow(() -> BusinessException.of(ErrorCode.USERID_NOT_FOUND));

        log.info("=============== selectAllMemberPtBySeq : Service " + memberDTO);
        List<PersonalTrainingDTO> personalTrainingDTO = memberMapper.selectAllMemberPtBySeq(mbrSeq, offSet, limit);
        memberDTO.setPersonalTrainingDTOList(personalTrainingDTO);
        return memberDTO;
    }
    @Override
    public MemberDTO selectOneMemberReportById(SearchDTO searchDTO) {

        MemberDTO memberDTO = memberMapper.selectOneMemberBySeq(searchDTO.getMbrSeq())
                .orElseThrow(BusinessException.supplierOf(ErrorCode.USERSEQ_NOT_FOUND));

        //mbrSeq 검증과정이 필요함.

        ExerciseHistorySummaryDTO exerciseHistorySummaryDTO =
                exerciseHistoryMapper.selectOneExchSummaryByMbrIdRanged(searchDTO)
                                     .orElse(new ExerciseHistorySummaryDTO());
        List<String> exercisedDays = exerciseHistoryMapper.selectAllExercisedDaysByMbrIdRanged(searchDTO);
        List<DailyRecordDTO> dailyRecords = exerciseHistoryMapper.selectAllDailyRecordsByMbrIdRanged(searchDTO);
        System.out.println(dailyRecords);
        List<ExerciseTargetDTO> targets = exerciseTargetMapper.selectAllExerciseTargetByMbrIdRanged(searchDTO);

        exerciseHistorySummaryDTO.setExercisedDays(exercisedDays);
        exerciseHistorySummaryDTO.setDailyRecords(dailyRecords);
        exerciseHistorySummaryDTO.setExerciseTargets(targets);

        memberDTO.setExerciseHistory(exerciseHistorySummaryDTO);

        // 사용자 암호 null 처리
        memberDTO.setMbrPw(null);
        return memberDTO;
    }

    @Override
    public MemberDTO selectAllMemberTrnfByMbrSeq(long mbrSeq) {
        MemberDTO memberDTO = memberMapper.selectOneMemberBySeq(mbrSeq)
                .orElseThrow(BusinessException.supplierOf(ErrorCode.USERSEQ_NOT_FOUND));
        memberDTO.setMbrPw(null);

        List<TrainerFeedbackDTO> trainerFeedbacks = trainerFeedbackMapper.selectAllTrainerFeedbackByMbrSeq(mbrSeq);

        memberDTO.setTrainerFeedbacks(trainerFeedbacks);
        return memberDTO;
    }

    @Override
    public String selectOneMemberTrnfByMbrSeq(long mbrSeq, String date) {
        TrainerFeedbackDTO trnfDTO = new TrainerFeedbackDTO();
        trnfDTO.setMbrSeq(mbrSeq);
        trnfDTO.setTargetMonth(date);
        return trainerFeedbackMapper.selectOneTrainerFeedbackByMbrSeq(trnfDTO);
    }
}
