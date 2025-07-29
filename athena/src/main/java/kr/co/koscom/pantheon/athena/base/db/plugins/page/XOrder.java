package kr.co.koscom.pantheon.athena.base.db.plugins.page;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class XOrder {
    private String column;
    private boolean asc = true;
    private Object value;
}
