package kr.co.koscom.pantheon.athena;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class KPage {
    private int offset = 0;
    private int limit = 10;
    private boolean first = true;
    private boolean last = false;
    private List<KOrder> orders = new LinkedList<>();
}
