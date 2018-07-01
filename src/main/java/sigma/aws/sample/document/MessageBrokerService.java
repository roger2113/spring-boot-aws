package sigma.aws.sample.document;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageBrokerService {

    public final static int COMPENSATION_DELAY = 60 * 15;

    @Value("${aws.queue.publish}")
    private String publishQueueURL;

    private AmazonSQS amazonSQS;

    @Autowired
    public MessageBrokerService(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void sendUploadFileCmd(String fileId) {
        SendMessageRequest message = new SendMessageRequest()
                .withQueueUrl(publishQueueURL)
                .withMessageBody(fileId)
                .withDelaySeconds(COMPENSATION_DELAY);
        amazonSQS.sendMessage(message);
    }
}
