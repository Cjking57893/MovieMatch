package com.codyking.moviematch.service;

import com.codyking.moviematch.model.TvShowLog;
import com.codyking.moviematch.repository.TvShowLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TvShowLogService {

    private final TvShowLogRepository tvShowLogRepository;

    @Autowired
    public TvShowLogService(TvShowLogRepository tvShowLogRepository) {
        this.tvShowLogRepository = tvShowLogRepository;
    }

    public List<TvShowLog> getAllTvShowLogs() {
        return tvShowLogRepository.findAll();
    }

    public TvShowLog getTvShowLogById(Long id) {
        Optional<TvShowLog> tvShowLog = tvShowLogRepository.findById(id);
        return tvShowLog.orElse(null);
    }

    public TvShowLog createTvShowLog(TvShowLog tvShowLog) {
        return tvShowLogRepository.save(tvShowLog);
    }

    public TvShowLog updateTvShowLog(Long id, TvShowLog tvShowLog) {
        if (!tvShowLogRepository.existsById(id)) {
            throw new RuntimeException("Tv Show Log not found");
        }

        tvShowLog.setId(id);

        return tvShowLogRepository.save(tvShowLog);
    }

    public void deleteTvShowLog(Long id) {
        if (!tvShowLogRepository.existsById(id)) {
            throw new RuntimeException("Tv Show Log not found");
        }

        tvShowLogRepository.deleteById(id);
    }

}
