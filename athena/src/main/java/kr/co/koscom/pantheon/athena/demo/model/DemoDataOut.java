package kr.co.koscom.pantheon.athena.demo.model;

import kr.co.koscom.pantheon.athena.base.io.TextXData;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoDataOut implements TextXData {

    @XAText(name = "아이디", size = 10)
    private String id;

    @XAText(name = "이름", size = 20)
    private String name;

    @XAText(name = "예수금", size = 10)
    private long deposit;
}
