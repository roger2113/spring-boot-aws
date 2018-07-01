package sigma.aws.sample.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sigma.aws.sample.document.dto.MetaInfo;
import sigma.aws.sample.document.dto.UploadResponse;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/aws")
public class ClientResource {

    private AWSService service;

    @Autowired
    public ClientResource(AWSService service) {
        this.service = service;
    }

    @PostMapping("/generateUrl")
    public ResponseEntity<UploadResponse> getPreAssignedUrl() {
        return ResponseEntity.ok(service.generatePreassignedFileUploadURL());
    }

    @PostMapping("/uploadMetaInfo")
    public ResponseEntity<UploadResponse> uploadMetaInfo(@Valid MetaInfo metaInfo) throws IOException {
        service.saveDocumentMetaInfo(metaInfo);
        return ResponseEntity.ok().build();
    }
}
