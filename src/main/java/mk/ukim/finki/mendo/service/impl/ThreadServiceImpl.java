package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Thread;
import mk.ukim.finki.mendo.repository.ThreadRepository;
import mk.ukim.finki.mendo.service.ThreadService;
import org.springframework.stereotype.Service;


@Service
public class ThreadServiceImpl implements ThreadService {

    ThreadRepository threadRepository;

    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public Thread findById(Long threadId) {
        return threadRepository.findById(threadId).orElse(null);
    }

    @Override
    public Thread save(Thread thread) {
        return threadRepository.save(thread);
    }
}
