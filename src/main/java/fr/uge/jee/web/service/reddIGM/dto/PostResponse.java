package fr.uge.jee.web.service.reddIGM.dto;

import fr.uge.jee.web.service.reddIGM.models.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String postName;
    private String userName;
    private String url;
    private String description;
    private Long subjectId;
    private Integer voteCount;
    private Integer commentCount;
    private LocalDateTime duration;
    private VoteType myVote;
}