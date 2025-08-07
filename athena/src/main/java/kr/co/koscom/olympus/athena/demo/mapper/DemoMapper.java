package kr.co.koscom.olympus.athena.demo.mapper;

import com.mybatisflex.core.BaseMapper;
import kr.co.koscom.olympus.athena.base.XPage;
import kr.co.koscom.olympus.athena.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DemoMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user where age >= #{su.age} limit #{pg.limit} offset #{pg.offset}")
    List<SysUser> selectPage(@Param("su") SysUser su, @Param("pg") XPage pg);

    @Select("select id, name, age, email from sys_user where age > #{su.age} - 100")
    List<SysUser> selectContPage(SysUser su, XPage pg);
}
