package kr.co.koscom.olympus.athena.pb.common.bcm;

import kr.co.koscom.olympus.athena.base.io.data.annotations.XAText;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Description;

import static kr.co.koscom.olympus.athena.pb.include.PB_COMMON.SUCCESS;

@Description("AML 고액자산가여부 조회")
public class AmtAssetYn_CBSMQ {

    public int AmtAssetYn_CBSMQ_Main(AML_ASSET_YN_ST psAmlAsset) {


        return SUCCESS;
    }

    @Description("기본정보취득 및 점검")
    public int VerifyInput_LC(AML_ASSET_YN_ST psAmlAsset, CONT_KEY_ST stContKey) {



        return SUCCESS;
    }

    public int AssetProcess031_LC(AML_ASSET_YN_ST psAmlAsset, CONT_KEY_ST stContKey) {

        return SUCCESS;
    }

    public int AssetProcess072_LC(AML_ASSET_YN_ST psAmlAsset, CONT_KEY_ST stContKey) {
        return SUCCESS;
    }

    @Data
    @Accessors(fluent = true)
    public static class CONT_KEY_ST {

        @XAText(name = "시작일자", size = 8)
        public String zSrtDt;

        @XAText(name = "끝일자", size = 8)
        public String zEndDt;

        @XAText(name = "구분", size = 1)
        public String zTp;

    }

    @Data
    @Accessors(fluent = true)
    public static class AML_ASSET_YN_ST {

        @XAText(name = "회사번호", size = 3)
        String zFirmNo;

        @XAText(name = "실명확인번호", size = 13)
        public String zRmnno;

        @XAText(name = "거래일자", size = 8)
        public String zTrxDt;

        @XAText(name = "고액자산가여부", size = 1)
        public String zLgmnyAssetYn;

        @XAText(name = "에러코드")
        public int nErrCode;

    }

    @Data
    @Accessors(fluent = true)
    public static class SELASSET072_QPA {

        @XAText(name = "입력")
        public QPI i;

        @XAText(name = "출력")
        public QPO o;

        @Data
        @Accessors(fluent = true)
        public static class QPI {

            @XAText(name = "실명확인번호", size = 13)
            public String zRmnno;

            @XAText(name = "시작일", size = 8)
            public String zSrtDt;

            @XAText(name = "종료일", size = 8)
            public String zEndDt;

        }

        @Data
        @Accessors(fluent = true)
        public static class QPO {

            @XAText(name = "고액자산가여부", size = 1)
            public String zLgmyAssetYn;

        }
    }
}
