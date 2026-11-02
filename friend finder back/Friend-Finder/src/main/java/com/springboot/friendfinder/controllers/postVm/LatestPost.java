package com.springboot.friendfinder.controllers.postVm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class LatestPost {
      @Schema(example = "2026-09-02T10:15:30")
    private String createdAt;

}
