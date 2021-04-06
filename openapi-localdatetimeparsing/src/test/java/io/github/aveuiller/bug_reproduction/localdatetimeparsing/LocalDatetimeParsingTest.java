package io.github.aveuiller.bug_reproduction.localdatetimeparsing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.DefaultApi;
import org.openapitools.client.model.User;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocalDatetimeParsingTest {

    private MockWebServer mockWebServer;
    private LocalDateTime expectedDate;
    private String name;

    @Before
    public void before() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        name = "test";
        String date = "1970-01-01T10:00:00";
        expectedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        MockResponse response = new MockResponse();
        response.setBody(String.format("{\"id\": 123, \"name\": \"%s\", \"creation\": \"%s\"}", name, date));
        mockWebServer.enqueue(response);
    }

    @After
    public void after() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void issueWithOriginalLocalDateTimeParsing() throws IOException {
        ApiClient client = new ApiClient();
        client.getAdapterBuilder().baseUrl(mockWebServer.url("/test/"));
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        client.getAdapterBuilder().addConverterFactory(GsonConverterFactory.create(gson));
        DefaultApi userApi = client.createService(DefaultApi.class);

        // Failure
        executeCallAndCheckAssertions(userApi);
    }

    @Test
    public void WorkingWithInjectedLocalDateTimeParsing() throws IOException {
        io.github.aveuiller.bug_reproduction.localdatetimeparsing.injectedConverter.ApiClient client =
                new io.github.aveuiller.bug_reproduction.localdatetimeparsing.injectedConverter.ApiClient();
        client.getAdapterBuilder().baseUrl(mockWebServer.url("/test/"));
        DefaultApi userApi = client.createService(DefaultApi.class);

        // Success
        executeCallAndCheckAssertions(userApi);
    }

    private void executeCallAndCheckAssertions(DefaultApi userApi) throws IOException {
        Call<User> userCall = userApi.usersGet();
        Response<User> execute = userCall.execute();

        User body = execute.body();
        assertNotNull(body);
        assertEquals(name, body.getName());
        assertEquals(expectedDate, body.getCreation());
    }
}