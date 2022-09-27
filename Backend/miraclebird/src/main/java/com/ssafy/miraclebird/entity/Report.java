package com.ssafy.miraclebird.entity;

import com.ssafy.miraclebird.dto.ReportDto;
import com.ssafy.miraclebird.securityOauth.domain.entity.user.User;
import com.ssafy.miraclebird.util.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Report")
public class Report {

    @Id
    @Column(name = "report_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportIdx;

    @Column(nullable = false)
    private String description;

    /* 연관관계 매핑 */
    @Column(nullable = false)
    private long reporter;

    @Column(nullable = false)
    private long suspect;

    public static Report of(ReportDto reportDto) {
        Report reportEntity = ModelMapperUtils.getModelMapper().map(reportDto, Report.class);

        return reportEntity;
    }
}
