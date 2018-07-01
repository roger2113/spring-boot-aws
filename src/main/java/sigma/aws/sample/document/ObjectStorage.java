package sigma.aws.sample.document;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ObjectStorage {

    @Value("aws.bucketName")
    private String bucket;

    private AmazonS3 s3Client;

    @Autowired
    public ObjectStorage(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String generatePreassignedFileUploadURL(String fileId) {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, fileId)
                .withMethod(HttpMethod.PUT)
                .withExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60));
        return s3Client.generatePresignedUrl(request).toString();
    }

}
