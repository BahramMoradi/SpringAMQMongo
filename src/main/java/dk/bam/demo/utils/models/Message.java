package dk.bam.demo.utils.models;

import lombok.Data;
import java.io.Serializable;

@Data
public class Message  implements Serializable {
    private static final long serialVersionUID = 3076791095088447885L;
    private String id;
    private String key;
    private String value;
}
