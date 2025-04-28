package com.codyking.moviematch.service;

import com.codyking.moviematch.model.MovieLog;
import com.codyking.moviematch.repository.MovieLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieLogServiceTest {

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
}
