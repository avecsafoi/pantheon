package kr.co.koscom.olympus.athena.base;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class XOrder {
    private String column;
    private boolean asc;
    private Object value;
}
