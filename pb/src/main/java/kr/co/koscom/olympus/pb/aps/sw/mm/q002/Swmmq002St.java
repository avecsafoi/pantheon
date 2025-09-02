package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import kr.co.koscom.olympus.pb.apa.PBST2Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Swmmq002St extends PBST2Object {

	public Swmmq002In in;

	public Swmmq002Out out;
}
