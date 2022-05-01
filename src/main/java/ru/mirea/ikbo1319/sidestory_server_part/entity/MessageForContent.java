package ru.mirea.ikbo1319.sidestory_server_part.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageForContent {
    private Long novelId;
    private String source;
    private String curCh;
    private String novelUrl;
}
