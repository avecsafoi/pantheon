package kr.co.koscom.pantheon.athena;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KOrder {
    private String column;
    private boolean asc = true;
    private Object value;
}
