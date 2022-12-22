package se.magnus.api.core.review;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Review {
    private int productId;
    private int reviewId;
    private String author;
    private String subject;
    private String content;
    private String serviceAddress;

    public Review() {
        productId = 0;
        reviewId = 0;
        author = null;
        subject = null;
        content = null;
        serviceAddress = null;
    }

}
