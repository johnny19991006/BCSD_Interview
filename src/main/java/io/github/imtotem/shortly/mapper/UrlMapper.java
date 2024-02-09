package io.github.imtotem.shortly.mapper;

import io.github.imtotem.shortly.domain.Url;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.domain.UserUrl;
import io.github.imtotem.shortly.dto.admin.AdminRequest;
import io.github.imtotem.shortly.dto.url.UrlRequest;
import io.github.imtotem.shortly.dto.url.UrlResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "url.originUrl", source = "request.originUrl")
    UserUrl toUserUrl(User user, UrlRequest request);

    @Mapping(target = "originUrl", source = "userUrl.url.originUrl")
    @Mapping(target = "shortUrl", source = "userUrl.url.shortUrl")
    UrlResponse toResponse(UserUrl userUrl);

    Url toUrl(AdminRequest request);
}
