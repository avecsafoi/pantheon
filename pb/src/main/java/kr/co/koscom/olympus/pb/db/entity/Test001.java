package kr.co.koscom.olympus.pb.db.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 实体类。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("test_001")
public class Test001 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private BigInteger no1;

    @Id
    private BigInteger no2;

    @Id
    private BigInteger no3;

    @Id
    private String id1;

    @Id
    private String id2;

    @Id
    private String id3;

    private String name1;

    private String name2;

    private String name3;

    private Timestamp created;

}
