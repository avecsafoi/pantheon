package kr.co.koscom.olympus.pb.ap.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 表定义层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
public class OnTblSpotOrdTableDef extends TableDef {

    /**
     *
     */
    public static final OnTblSpotOrdTableDef ON_TBL_SPOT_ORD = new OnTblSpotOrdTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    public final QueryColumn BNS_TP = new QueryColumn(this, "BNS_TP");


    public final QueryColumn ISU_NO = new QueryColumn(this, "ISU_NO");


    public final QueryColumn ORD_DT = new QueryColumn(this, "ORD_DT");


    public final QueryColumn ORD_NO = new QueryColumn(this, "ORD_NO");


    public final QueryColumn ACNT_NO = new QueryColumn(this, "ACNT_NO");


    public final QueryColumn ORD_PRC = new QueryColumn(this, "ORD_PRC");


    public final QueryColumn ORD_QTY = new QueryColumn(this, "ORD_QTY");


    public final QueryColumn EXEC_AMT = new QueryColumn(this, "EXEC_AMT");


    public final QueryColumn EXEC_QTY = new QueryColumn(this, "EXEC_QTY");


    public final QueryColumn ORD_TIME = new QueryColumn(this, "ORD_TIME");


    public final QueryColumn ORD_MKT_CODE = new QueryColumn(this, "ORD_MKT_CODE");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ORD_DT, ORD_NO, ORD_MKT_CODE, ACNT_NO, ISU_NO, ORD_QTY, ORD_PRC, ORD_TIME, BNS_TP, EXEC_QTY, EXEC_AMT};

    public OnTblSpotOrdTableDef() {
        super("", "on_tbl_spot_ord");
    }

    private OnTblSpotOrdTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public OnTblSpotOrdTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, _ -> new OnTblSpotOrdTableDef("", "on_tbl_spot_ord", alias));
    }

}
