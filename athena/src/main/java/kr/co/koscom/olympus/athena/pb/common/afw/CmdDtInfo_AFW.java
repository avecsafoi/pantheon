package kr.co.koscom.olympus.athena.pb.common.afw;

import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import static kr.co.koscom.olympus.athena.pb.include.PB_COMMON.SUCCESS;

public class CmdDtInfo_AFW {

    /**
     * @param pzInptDate 입력일자
     * @param pzResltDt  결과일자
     * @return int       성공/실패
     */
    public static int ObtPrevDt_AFW(CharBuffer pzInptDate, CharBuffer pzResltDt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        pzResltDt.append(sdf.format(new Date()));
        return SUCCESS;
    }

    /**
     * @param pzInptDate 입력일자
     * @param pzResltDt  결과일자
     * @return int       성공/실패
     */
    public static int ObtPrevBizDt_AFW(CharBuffer pzInptDate, CharBuffer pzResltDt) {
        return ObtPrevDt_AFW(null, pzResltDt);
    }
}
