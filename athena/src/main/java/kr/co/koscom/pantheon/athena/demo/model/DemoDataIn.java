package kr.co.koscom.pantheon.athena.demo.model;

import kr.co.koscom.pantheon.athena.base.io.TextKData;
import kr.co.koscom.pantheon.athena.base.io.data.annotations.KAText;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoDataIn implements TextKData {

    @KAText(name = "아이디", size = 10)
    private String id;

    @KAText(name = "이름", size = 20)
    private String name;

    @KAText(name = "예수금", size = 10)
    private String deposit;
}
