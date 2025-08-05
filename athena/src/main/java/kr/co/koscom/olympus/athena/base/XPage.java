package kr.co.koscom.olympus.athena.base;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class XPage {
    private int offset = 0;
    private int limit = 10;
    private boolean first = true;
    private boolean last = false;
    private List<XOrder> orders = new LinkedList<>();
}
