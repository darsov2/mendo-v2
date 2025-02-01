package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Thread;


public interface ThreadService {

    public Thread findById(Long threadId);
    public Thread save(Thread thread);

}
