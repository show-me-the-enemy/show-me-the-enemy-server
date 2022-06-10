package com.mse.showmetheenemyserver.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildUpRequestDto {

    private Long id;

    private String sender;

    private String type;

    private String name;

    private Integer count;
}
