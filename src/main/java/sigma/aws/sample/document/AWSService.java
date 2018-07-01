package sigma.aws.sample.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigma.aws.sample.document.dto.MetaInfo;
import sigma.aws.sample.document.dto.UploadResponse;

import java.io.IOException;
import java.util.UUID;

@Service
public class AWSService {

    private MessageBrokerService messageBrokerService;
    private ObjectStorage objectStorage;
    private SearchEngine elasticSearchService;

    @Autowired
    public AWSService(MessageBrokerService messageBrokerService, ObjectStorage objectStorage, SearchEngine elasticSearchService) {
        this.messageBrokerService = messageBrokerService;
        this.objectStorage = objectStorage;
        this.elasticSearchService = elasticSearchService;
    }

    public UploadResponse generatePreassignedFileUploadURL() {
        String fileId = UUID.randomUUID().toString();
        String uploadURL = objectStorage.generatePreassignedFileUploadURL(fileId);
        messageBrokerService.sendUploadFileCmd(fileId);
        return UploadResponse.builder().fileId(fileId).url(uploadURL).build();
    }


    public void saveDocumentMetaInfo(MetaInfo metaInfo) throws IOException {
        elasticSearchService.saveMetaInfo(metaInfo);
    }
}
