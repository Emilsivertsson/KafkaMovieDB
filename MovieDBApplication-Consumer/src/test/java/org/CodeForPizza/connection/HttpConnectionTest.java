package org.CodeForPizza.connection;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpPost;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpPut;
import org.CodeForPizza.dto.MovieDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.List;
import org.apache.http.client.methods.HttpGet;

public class HttpConnectionTest {

    private HttpConnection httpConnection;
    private CloseableHttpClient mockHttpClient;

    CloseableHttpResponse mockHttpResponse;

    @BeforeEach
    public void setUp() {
        httpConnection = new HttpConnection();
        mockHttpClient = Mockito.mock(CloseableHttpClient.class);
        mockHttpResponse = Mockito.mock(CloseableHttpResponse.class);
    }

    @Test //TODO FIX all asserts
    public void testSaveMovieToAPI() throws IOException {
        MovieDTO movie = new MovieDTO();

        movie.setTitle("Test Movie");
        Mockito.when(mockHttpClient.execute(Mockito.any())).thenReturn(mockHttpResponse);
        httpConnection.executePOST(mockHttpClient, Mockito.any(org.apache.http.client.methods.HttpPost.class));

        String result = httpConnection.saveMovieToAPI(movie);

        // Assert the result

    }

    @Test
    public void testGetAllMovies() throws IOException {
        Mockito.when(mockHttpClient.execute(Mockito.any())).thenReturn(mockHttpResponse);
        httpConnection.executeGETAll(mockHttpClient, Mockito.any(HttpGet.class));

        List<MovieDTO> movieList = httpConnection.getAllMovies();

        // Assert the result
        // You can use assertions to check if the list is not empty or other criteria
    }

    @Test
    public void testDeleteMovie() throws IOException {
        int movieId = 1;
        Mockito.when(mockHttpClient.execute(Mockito.any())).thenReturn(mockHttpResponse);
        httpConnection.executeDELETE(mockHttpClient, Mockito.any(HttpDelete.class));
        String result = httpConnection.deleteMovie(movieId);

        // Assert the result
        // You can use assertions to check if the result is as expected
    }

    @Test
    public void testUpdateMovie() throws IOException {
        int movieId = 1;
        MovieDTO movie = new MovieDTO();
        movie.setTitle("Updated Movie");
        Mockito.when(mockHttpClient.execute(Mockito.any())).thenReturn(mockHttpResponse);
        httpConnection.executePut(mockHttpClient, Mockito.any(org.apache.http.client.methods.HttpPut.class));

        String result = httpConnection.updateMovie(movieId, movie);

        // Assert the result
        // You can use assertions to check if the result is as expected
    }
}
