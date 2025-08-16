package kr.co.koscom.olympus.pb.test.page.io;

import kr.co.koscom.olympus.pb.ab.data.PBObject;
import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;
import kr.co.koscom.olympus.pb.ab.db.page.PBPageData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ST_10001001_Out extends PBObject implements PBPageData<PBCPage> {

    List<OrdDo> list;

    PBCPage page;
}
