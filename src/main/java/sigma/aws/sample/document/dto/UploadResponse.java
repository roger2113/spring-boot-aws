package sigma.aws.sample.document.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
    private String fileId;
    private String url;
}
