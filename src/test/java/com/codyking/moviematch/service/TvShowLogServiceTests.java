package com.codyking.moviematch.service;

import com.codyking.moviematch.model.TvShowLog;
import com.codyking.moviematch.repository.TvShowLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TvShowLogServiceTests {
    @Mock
    private TvShowLogRepository tvShowLogRepository;

    @InjectMocks
    private TvShowLogService tvShowLogService;

    @Test
    void testGetAllTvShowLogs() {
        TvShowLog fakeTvShowLog1 = new TvShowLog();
        TvShowLog fakeTvShowLog2 = new TvShowLog();
        TvShowLog fakeTvShowLog3 = new TvShowLog();
        List<TvShowLog> allFakeTvShowLogs = List.of(fakeTvShowLog1, fakeTvShowLog2, fakeTvShowLog3);

        when(tvShowLogRepository.findAll()).thenReturn(allFakeTvShowLogs);

        List<TvShowLog> result = tvShowLogService.getAllTvShowLogs();

        assertEquals(allFakeTvShowLogs, result);

        verify(tvShowLogRepository).findAll();
    }

    @Test
    void testGetTvShowLogById() {
        TvShowLog fakeTvShowLog1 = new TvShowLog();
        TvShowLog fakeTvShowLog2 = new TvShowLog();
        fakeTvShowLog1.setId(10L);
        fakeTvShowLog2.setId(20L);

        when(tvShowLogRepository.findById(10L)).thenReturn(Optional.of(fakeTvShowLog1)); // Returning as optional since that is findByID return type

        TvShowLog result = tvShowLogService.getTvShowLogById(10L);
        TvShowLog resultIfNoneFound= tvShowLogService.getTvShowLogById(12L);

        assertEquals(fakeTvShowLog1, result);
        assertNull(resultIfNoneFound);

        verify(tvShowLogRepository).findById(10L);
        verify(tvShowLogRepository).findById(12L);
    }

    @Test
    void testCreateTvShowLog() {
        TvShowLog fakeTvShowLog1 = new TvShowLog();

        when(tvShowLogRepository.save(fakeTvShowLog1)).thenReturn(fakeTvShowLog1);

        TvShowLog result = tvShowLogService.createTvShowLog(fakeTvShowLog1);

        assertEquals(result, fakeTvShowLog1);

        verify(tvShowLogRepository).save(fakeTvShowLog1);
    }

    @Test
    void testUpdateTvShowLog() {
        TvShowLog fakeTvShowLog1 = new TvShowLog();
        fakeTvShowLog1.setId(1L);

        TvShowLog updatedFakeTvShowLog1 = new TvShowLog();
        updatedFakeTvShowLog1.setId(fakeTvShowLog1.getId());
        updatedFakeTvShowLog1.setStatus(TvShowLog.Status.watched);

        when(tvShowLogRepository.existsById(fakeTvShowLog1.getId())).thenReturn(true);
        when(tvShowLogRepository.save(updatedFakeTvShowLog1)).thenReturn(updatedFakeTvShowLog1);

        TvShowLog result = tvShowLogService.updateTvShowLog(fakeTvShowLog1.getId(), updatedFakeTvShowLog1);

        assertThrows(RuntimeException.class, () ->
                tvShowLogService.updateTvShowLog(2L, updatedFakeTvShowLog1)
        );
        assertEquals(result, updatedFakeTvShowLog1);

        verify(tvShowLogRepository).save(updatedFakeTvShowLog1);
    }

    @Test
    void testDeleteTvShowLog() {
        TvShowLog fakeTvShowLog1 = new TvShowLog();
        fakeTvShowLog1.setId(1L);

        when(tvShowLogRepository.existsById(1L)).thenReturn(true);

        tvShowLogService.deleteTvShowLog(fakeTvShowLog1.getId());

        assertThrows(RuntimeException.class, () ->
                tvShowLogService.deleteTvShowLog(2L)
        );

        verify(tvShowLogRepository).deleteById(fakeTvShowLog1.getId());
    }
}
