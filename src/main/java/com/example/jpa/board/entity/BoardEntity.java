package com.example.jpa.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tbl_board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardEntity {

    @Id
    private int id;
    private String writer;
    private String title;
    private String content;
    private String category;


}
