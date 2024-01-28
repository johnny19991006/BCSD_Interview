package io.github.imtotem.shortly.service.url;

import io.github.imtotem.shortly.domain.Url;
import io.github.imtotem.shortly.domain.UserUrl;
import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.exception.UserException;
import io.github.imtotem.shortly.repository.ShortUrlRepository;
import io.github.imtotem.shortly.repository.UserUrlRepository;
import io.github.imtotem.shortly.util.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UrlServiceImpl implements UrlService {
    private final UserUrlRepository userUrlRepository;
    private final ShortUrlRepository shortUrlRepository;
    private final Base62 base62;

    @Override
    public List<UserUrl> findAll(String email) {
        return userUrlRepository.findAllByUser_Email(email);
    }

    @Override
    public UserUrl createUrl(UserUrl request) throws RuntimeException {
        return userUrlRepository.findByUser_EmailAndUrl_OriginUrl(
                request.getUser().getEmail(), request.getUrl().getOriginUrl())
                .orElseGet(() -> {
                    Url url = shortUrlRepository.findByOriginUrl(request.getUrl().getOriginUrl())
                            .orElseGet(() -> {
                                Url _url = shortUrlRepository.save(request.getUrl());
                                _url.setShortUrl(base62.encode(_url.getId()));
                                return _url;
                            });
                    url.increase();
                    request.updateUrl(url);
                    return userUrlRepository.save(request);
                });
    }

    @Override
    public UserUrl updateUrl(UserUrl request) throws RuntimeException {
        UserUrl userUrl = userUrlRepository.findByUser_EmailAndUrl_OriginUrl(
                request.getUser().getEmail(), request.getUrl().getOriginUrl())
                .orElseThrow(() -> new UserException(ErrorCode.URL_NOT_FOUND));

        userUrl.updateDescription(request.getDescription());
        userUrl.updateDeletable(request.isDeletable());

        return userUrl;
    }

    @Override
    public boolean deleteUrl(UserUrl request) throws RuntimeException {
        UserUrl userUrl = userUrlRepository.findByUser_EmailAndUrl_OriginUrl(
                request.getUser().getEmail(), request.getUrl().getOriginUrl())
                .orElseThrow(() -> new UserException(ErrorCode.URL_NOT_FOUND));

        userUrlRepository.delete(userUrl);
        userUrl.getUrl().decrease();
        if (userUrl.getUrl().getCnt() <= 0) {
            shortUrlRepository.delete(userUrl.getUrl());
        }

        return true;
    }

    @Override
    public String restoreUrl(String shortUrl) {
        return shortUrlRepository.findById(base62.decode(shortUrl))
                .orElseThrow(() -> new UserException(ErrorCode.URL_NOT_FOUND))
                .getOriginUrl();
    }
}
