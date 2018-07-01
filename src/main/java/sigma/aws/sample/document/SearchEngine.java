package sigma.aws.sample.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigma.aws.sample.document.dto.MetaInfo;

import java.io.IOException;

@Service
public class SearchEngine {

    private static final String INDEX = "/documents";
    private RestHighLevelClient client;
    private final ObjectMapper jsonMapper;

    @Autowired
    public SearchEngine(RestHighLevelClient client) {
        jsonMapper = new ObjectMapper();
        this.client = client;
    }

    public void saveMetaInfo(MetaInfo metaInfo) throws IOException {
        IndexRequest request = new IndexRequest(INDEX);
        request.source(buildEntity(metaInfo), XContentType.JSON);
        client.index(request);
    }

    private String buildEntity(MetaInfo metaInfo) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(metaInfo);
    }
}
