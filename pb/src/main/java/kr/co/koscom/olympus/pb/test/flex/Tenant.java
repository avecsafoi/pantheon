package kr.co.koscom.olympus.pb.test.flex;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@Table("TENANT")
public class Tenant extends Model<Tenant> {

    @Column(tenantId = true)
    private String tenantId;

    private String TenantName;

    private String dbName;
}
