package io.github.imtotem.shortly.mapper;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserInfoRequest request);

    UserInfoResponse toResponse(User user);
}
