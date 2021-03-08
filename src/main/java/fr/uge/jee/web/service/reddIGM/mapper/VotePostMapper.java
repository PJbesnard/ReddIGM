package fr.uge.jee.web.service.reddIGM.mapper;

import fr.uge.jee.web.service.reddIGM.dto.VotePostDto;
import fr.uge.jee.web.service.reddIGM.models.VotePost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VotePostMapper {
    VotePostMapper INSTANCE = Mappers.getMapper(VotePostMapper.class);

    @Mapping(target = "postId", expression = "java(vote.getPost().getPostId())")
    @Mapping(target = "vote", expression = "java(vote.getType())")
    @Mapping(target = "creationDate", expression = "java(vote.getCreationDate())")
    VotePostDto toDto(VotePost vote);
}
