package com.codyking.moviematch.service;

import com.codyking.moviematch.model.MovieLog;
import com.codyking.moviematch.repository.MovieLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieLogServiceTests {

    @Mock
    private MovieLogRepository movieLogRepository;

    @InjectMocks
    private MovieLogService movieLogService;

    @Test
    void testGetAllMovieLogs() {
        MovieLog fakeMovieLog1 = new MovieLog();
        MovieLog fakeMovieLog2 = new MovieLog();
        MovieLog fakeMovieLog3 = new MovieLog();
        List<MovieLog> allFakeMovieLogs = List.of(fakeMovieLog1, fakeMovieLog2, fakeMovieLog3);

        when(movieLogRepository.findAll()).thenReturn(allFakeMovieLogs);

        List<MovieLog> result = movieLogService.getAllMovieLogs();

        assertEquals(allFakeMovieLogs, result);

        verify(movieLogRepository).findAll();
    }

    @Test
    void testGetMovieLogById() {
        MovieLog fakeMovieLog1 = new MovieLog();
        MovieLog fakeMovieLog2 = new MovieLog();
        fakeMovieLog1.setId(10L);
        fakeMovieLog2.setId(20L);

        when(movieLogRepository.findById(10L)).thenReturn(Optional.of(fakeMovieLog1)); // Returning as optional since that is findByID return type

        MovieLog result = movieLogService.getMovieLogById(10L);
        MovieLog resultIfNoneFound= movieLogService.getMovieLogById(12L);

        assertEquals(fakeMovieLog1, result);
        assertNull(resultIfNoneFound);

        verify(movieLogRepository).findById(10L);
        verify(movieLogRepository).findById(12L);
    }

    @Test
    void testCreateMovieLog() {
        MovieLog fakeMovieLog1 = new MovieLog();

        when(movieLogRepository.save(fakeMovieLog1)).thenReturn(fakeMovieLog1);

        MovieLog result = movieLogService.createMovieLog(fakeMovieLog1);

        assertEquals(result, fakeMovieLog1);

        verify(movieLogRepository).save(fakeMovieLog1);
    }

    @Test
    void testUpdateMovieLog() {
        MovieLog fakeMovieLog1 = new MovieLog();
        fakeMovieLog1.setId(1L);

        MovieLog updatedFakeMovieLog1 = new MovieLog();
        updatedFakeMovieLog1.setId(fakeMovieLog1.getId());
        updatedFakeMovieLog1.setStatus(MovieLog.Status.watched);

        when(movieLogRepository.existsById(fakeMovieLog1.getId())).thenReturn(true);
        when(movieLogRepository.save(updatedFakeMovieLog1)).thenReturn(updatedFakeMovieLog1);

        MovieLog result = movieLogService.updateMovieLog(fakeMovieLog1.getId(), updatedFakeMovieLog1);

        assertThrows(RuntimeException.class, () ->
            movieLogService.updateMovieLog(2L, updatedFakeMovieLog1)
        );
        assertEquals(result, updatedFakeMovieLog1);

        verify(movieLogRepository).save(updatedFakeMovieLog1);
    }

    @Test
    void testDeleteMovieLog() {
        MovieLog fakeMovieLog1 = new MovieLog();
        fakeMovieLog1.setId(1L);

        when(movieLogRepository.existsById(fakeMovieLog1.getId())).thenReturn(true);

        movieLogService.deleteMovieLog(fakeMovieLog1.getId());

        assertThrows(RuntimeException.class, () ->
            movieLogService.deleteMovieLog(2L)
        );

        verify(movieLogRepository).deleteById(1L);
    }
}
