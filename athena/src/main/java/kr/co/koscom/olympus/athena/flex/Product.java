package kr.co.koscom.olympus.athena.flex;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(fluent = true)
@Table("product")
public class Product {

    @Id(keyType = KeyType.Auto)
    public BigInteger prodNo;

    public String prodName;

    public String prodModel;

    public int prodQty;

    public String prodDate;

}
