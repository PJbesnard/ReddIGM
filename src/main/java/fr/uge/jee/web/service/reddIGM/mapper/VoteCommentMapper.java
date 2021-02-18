package fr.uge.jee.web.service.reddIGM.mapper;

import fr.uge.jee.web.service.reddIGM.dto.VoteCommentDto;
import fr.uge.jee.web.service.reddIGM.models.VoteComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteCommentMapper {
    VoteCommentMapper INSTANCE = Mappers.getMapper(VoteCommentMapper.class);

    @Mapping(target = "userName", expression = "java(voteComment.getUser().getUsername())")
    @Mapping(target = "commentId", expression = "java(voteComment.getComment().getId())")
    @Mapping(target = "vote", expression = "java(voteComment.getType())")
    VoteCommentDto toDto(VoteComment voteComment);
}
