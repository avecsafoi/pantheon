package kr.co.koscom.olympus.athena.pb.on.trd;

import kr.co.koscom.olympus.athena.pb.include.base.st.PB_APS;
import kr.co.koscom.olympus.athena.pb.include.base.ut.TIMEVAL;

import java.time.Instant;

import static kr.co.koscom.olympus.athena.pb.include.PB_COMMON.SUCCESS;
import static kr.co.koscom.olympus.athena.pb.include.base.ut.PB_FN.gettimeofday;

@PB_CMeta(value = """
        프로그램: SONAT100_APS
        작 성 자: 정기우
        작 성 일: 2005/11/29
        처리기능: 현물주문처리(유가증권시장,코스닥시장,프리보드시장 위탁/저축/신용주문
        입력정보: SONAT100 전문입력
        출력정보: SONAT100 전문입력
        특이사항: 서비스소스생성 자동화툴에 의한 생성
        담 당 자: 정기우
        관 리 자:
        이력관리: 변경자/변경일/변경이유/변경사항
        """, history = """
        2007.08.07: NH 증권 강제 반대매매 주문,정정,취소시 비밀번호 'YYYY' 처리
        2007.08.29: ETK 내부주문처리시 지점대표비밀번호가능
        ...
        2020.12.23: 골드만삭스 zDummy에 GridId 반영(김덕기)
        """)
public class SONAT100_APS implements PB_APS<SONAT100_ST> {

    /**
     * 서비스처리 MAIN
     */
    @Override
    public int process(SONAT100_ST psMsg) {

        TIMEVAL timeval = new TIMEVAL();

        gettimeofday(timeval, null);
        Instant a1 = Instant.now();

        /* 1. 입력데이터확인, 온라인여부 확인 */
        // int nRet = SpotOrdInputDataChkRtn_LC();

        /* 2. 일자확인 */

        /* 3. 회사정보,통신매체 Check */

        /* 4. 계좌확인 */

        /* 2008.01.21 */
        // GetHdrTrxRestrcTp();

        /* 5. 지점정보 Check */

        /* 6. 종목정보 Check */

        /* 6.1 상품유형,상세유형별 매매가능종목 체크로직 추가 */

        /* 7. 장운영확인 */

        /* 8. 증거금률산정 */

        /* 9. 증거금징수,증거수량징수, 주문내역 Insert */

        /* 10. 주문 Queue Write */

        /* 11. 주문output생성 및 MJF데이터 생성 */

        psMsg.out = new SONAT100_Out_ST();

        return SUCCESS;
    }

    // private int SpotOrdInputDataChkRtn_LC(SONAT100_ST psMsg, SPOT_ORD_CM_INPUT_ST in) { return 0; }

    // private int FirmInfoChkRtn_LC(SONAT100_ST st, SELMDBFIRMMBSINFO_QPA qpa, SPOT_ORD_CM_INPUT_ST st2) { return 0; }


}

