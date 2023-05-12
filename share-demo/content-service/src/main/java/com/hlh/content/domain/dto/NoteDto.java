package com.hlh.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private String title;
    private String summary;
    private String downloadUrl;
    private String author;
    private Integer isOriginal;
    private String cover;
}
