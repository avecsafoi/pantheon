package kr.co.koscom.olympus.athena.flex;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import kr.co.koscom.olympus.athena.flex.mapper.ProductMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductService extends ServiceImpl<ProductMapper, Product> {

}
