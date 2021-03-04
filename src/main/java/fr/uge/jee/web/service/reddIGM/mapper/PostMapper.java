package fr.uge.jee.web.service.reddIGM.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import fr.uge.jee.web.service.reddIGM.dto.PostRequest;
import fr.uge.jee.web.service.reddIGM.dto.PostResponse;
import fr.uge.jee.web.service.reddIGM.models.*;
import fr.uge.jee.web.service.reddIGM.repositories.CommentRepository;
import fr.uge.jee.web.service.reddIGM.repositories.VoteRepository;
import fr.uge.jee.web.service.reddIGM.services.AuthenticationService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.time.Instant;
import java.util.Optional;

import static fr.uge.jee.web.service.reddIGM.models.Vote.Type.DOWNVOTE;
import static fr.uge.jee.web.service.reddIGM.models.Vote.Type.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthenticationService authService;



    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subject subject, User user);

    @Mapping(target = "id", expression = "java(post.getPostId())")
    @Mapping(target = "subjectId", expression = "java(post.getSubject().getId())")
    @Mapping(target = "userId", expression = "java(post.getUser().getId())")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "myVote", expression = "java(voteType)")
    @Mapping(target = "voteCount", expression = "java(nbVote)")
    public abstract PostResponse mapToDto(Post post, int nbVote, VoteType voteType);

    LocalDateTime getDuration(Post post) {
        return post.getCreatedDate();
    }



}