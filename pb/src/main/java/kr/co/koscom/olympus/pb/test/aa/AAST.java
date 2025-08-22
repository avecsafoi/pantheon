package kr.co.koscom.olympus.pb.test.aa;

import kr.co.koscom.olympus.pb.include.PBST;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class AAST extends PBST<AAQ, AAR> {

}
