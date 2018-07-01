package sigma.aws.sample.document.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class MetaInfo {
    @NotNull
    private String guid;
    @NotNull
    private String publisher;
    @NotNull
    private String fileName;
    @NotNull
    private String s3URL;
}
