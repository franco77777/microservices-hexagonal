package com.pragma.square.application.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlateQuantityResponseDto {
    private Long plateId;
    private Integer quantity;
}
