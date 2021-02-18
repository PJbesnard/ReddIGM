package fr.uge.jee.web.service.reddIGM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
//    private Long postId;
    private Long subjectId;
    private Long userId;
    private String postName;
    private String url;
    private String description;
}
