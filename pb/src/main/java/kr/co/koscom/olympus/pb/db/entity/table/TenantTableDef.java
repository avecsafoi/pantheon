package kr.co.koscom.olympus.pb.db.entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 * 表定义层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
public class TenantTableDef extends TableDef {

    /**
     *
     */
    public static final TenantTableDef TENANT = new TenantTableDef();
    @Serial
    private static final long serialVersionUID = 1L;
    public final QueryColumn NO = new QueryColumn(this, "no");


    public final QueryColumn CREATED = new QueryColumn(this, "created");


    public final QueryColumn UPDATED = new QueryColumn(this, "updated");


    public final QueryColumn TENANT_ID = new QueryColumn(this, "tenant_id");


    public final QueryColumn TENANT_NAME = new QueryColumn(this, "tenant_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{NO, TENANT_ID, TENANT_NAME, CREATED, UPDATED};

    public TenantTableDef() {
        super("", "tenant");
    }

    private TenantTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TenantTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TenantTableDef("", "tenant", alias));
    }

}
