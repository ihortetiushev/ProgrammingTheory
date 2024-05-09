package team.codium.refactored.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;

public class RequestExecutor<T> {
    private final ResultConverter<T> resultConverter;

    public RequestExecutor(ResultConverter<T> resultConverter) {
        this.resultConverter = resultConverter;
    }

    T executeGetRequest(String url) {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        String rawResponse;
        try {
            HttpRequest request = requestFactory.buildGetRequest(
                    new GenericUrl(url));
            rawResponse = request.execute().parseAsString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultConverter.convert(rawResponse);
    }
}
