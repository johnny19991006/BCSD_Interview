package io.github.imtotem.shortly.mapper;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.notice.NoticeRequest;
import io.github.imtotem.shortly.dto.notice.NoticeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    @Mapping(source = "request.id", target = "id")
    Notice toNotice(User user, NoticeRequest request);

    @Mapping(source = "notice.user.email", target = "email")
    NoticeResponse toResponse(Notice notice);
}
