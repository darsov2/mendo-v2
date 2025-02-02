package mk.ukim.finki.mendo.web.request;

import lombok.Data;
import mk.ukim.finki.mendo.model.MendoUser;
import mk.ukim.finki.mendo.model.Post;
import mk.ukim.finki.mendo.model.Thread;
@Data
public class PostRequest {

    String description;
    Post parent;
    Long threadId;

}
