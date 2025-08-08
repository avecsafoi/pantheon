package kr.co.koscom.olympus.athena.flex.mapper;

import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;
import kr.co.koscom.olympus.athena.flex.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@UseDataSource("flex")
public interface ProductMapper extends BaseMapper<Product> {

}
