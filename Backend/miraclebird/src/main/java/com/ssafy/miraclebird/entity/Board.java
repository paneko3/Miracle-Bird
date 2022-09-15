package com.ssafy.miraclebird.entity;

import com.ssafy.miraclebird.dto.BoardDto;
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
@Table(name = "Board")
public class Board {
    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardIdx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int hit;

    /* 연관관계 매핑 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private User user;

    @OneToMany(mappedBy = "board")
    List<Comment> comment = new ArrayList<>();

    public static Board of(BoardDto boardDto) {
        Board boardEntity = ModelMapperUtils.getModelMapper().map(boardDto, Board.class);

        return boardEntity;
    }
}
