package io.github.imtotem.shortly.service.admin;

import io.github.imtotem.shortly.domain.Url;
import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.exception.CustomException;
import io.github.imtotem.shortly.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    private final ShortUrlRepository repository;

    @Override
    public List<Url> findAll() throws RuntimeException {
        return repository.findAll();
    }

    @Override
    public boolean deleteUrl(Url url) throws RuntimeException {
        if (url.getId() != null) {
            repository.deleteById(url.getId());
        }
        else if (url.getShortUrl() != null) {
            repository.deleteByShortUrl(url.getShortUrl());
        }
        else if (url.getOriginUrl() != null) {
            repository.deleteByOriginUrl(url.getOriginUrl());
        }
        else {
            throw new CustomException(ErrorCode.URL_NOT_FOUND);
        }

        return true;
    }

}
