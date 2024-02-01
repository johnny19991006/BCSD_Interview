package io.github.imtotem.shortly.service.notice;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository repository;

    @Override
    public Notice createNotice(Notice request) throws RuntimeException {
        return repository.save(request);
    }

    @Override
    public List<Notice> findAll() throws RuntimeException {
        return repository.findAll();
    }

    @Override
    public List<Notice> findAllByUser(User user) throws RuntimeException {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public boolean deleteNotice(long id) throws RuntimeException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            return false;
        }
        return true;
    }
}
