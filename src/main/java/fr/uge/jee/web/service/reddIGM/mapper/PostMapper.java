package fr.uge.jee.web.service.reddIGM.mapper;

import fr.uge.jee.web.service.reddIGM.dto.PostRequest;
import fr.uge.jee.web.service.reddIGM.dto.PostResponse;
import fr.uge.jee.web.service.reddIGM.dto.SubjectDto;
import fr.uge.jee.web.service.reddIGM.dto.UserDto;
import fr.uge.jee.web.service.reddIGM.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subject subject, User user);

    @Mapping(target = "id", expression = "java(post.getPostId())")
    @Mapping(target = "sub", expression = "java(sub)")
    @Mapping(target = "user", expression = "java(user)")
    @Mapping(target = "duration", expression = "java(post.getCreatedDate())")
    @Mapping(target = "myVote", expression = "java(voteType)")
    @Mapping(target = "voteCount", expression = "java(nbVote)")
    @Mapping(target = "description", expression = "java(post.getDescription())")
    @Mapping(target = "nbComments", expression = "java(nbComments)")
    PostResponse mapToDto(Post post, int nbVote, VoteType voteType, SubjectDto sub, UserDto user, Integer nbComments);

}