package kr.co.koscom.olympus.pb.test.pb.io.st10001001;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.data.annotation.PBA;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBPageData;
import kr.co.koscom.olympus.pb.ap.entity.OnTblSpotOrd;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ST10001001Out extends PBObject implements PBPageData<PBCPage> {

    @PBA(name = "리스트")
    private List<OnTblSpotOrd> list;

    @PBA(name = "페이지")
    private PBCPage page;
}
