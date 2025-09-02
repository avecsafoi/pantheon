package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import java.util.List;

import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.apa.PBST2Object;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class Swmmx002St extends PBST2Object {

	@PBA(name = "입력값")
	public Swmmx002In in;

	@PBA(name = "페이지")
	public PBCPage page;

	@PBA(name = "출력값")
	public List<Swmmq002Out> out;
}
