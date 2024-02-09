package io.github.imtotem.shortly.mapper;

import io.github.imtotem.shortly.dto.Error;
import io.github.imtotem.shortly.exception.CustomException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ErrorMapper {
    Error toError(CustomException e);
}
