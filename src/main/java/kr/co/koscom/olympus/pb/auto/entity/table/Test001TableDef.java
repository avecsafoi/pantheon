package kr.co.koscom.olympus.pb.auto.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
public class Test001TableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final Test001TableDef TEST001 = new Test001TableDef();

    
    public final QueryColumn ID1 = new QueryColumn(this, "id1");

    
    public final QueryColumn ID2 = new QueryColumn(this, "id2");

    
    public final QueryColumn ID3 = new QueryColumn(this, "id3");

    
    public final QueryColumn NO1 = new QueryColumn(this, "no1");

    
    public final QueryColumn NO2 = new QueryColumn(this, "no2");

    
    public final QueryColumn NO3 = new QueryColumn(this, "no3");

    
    public final QueryColumn NAME1 = new QueryColumn(this, "name1");

    
    public final QueryColumn NAME2 = new QueryColumn(this, "name2");

    
    public final QueryColumn NAME3 = new QueryColumn(this, "name3");

    
    public final QueryColumn CREATED = new QueryColumn(this, "created");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{NO1, NO2, NO3, ID1, ID2, ID3, NAME1, NAME2, NAME3, CREATED};

    public Test001TableDef() {
        super("", "test_001");
    }

    private Test001TableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public Test001TableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new Test001TableDef("", "test_001", alias));
    }

}
