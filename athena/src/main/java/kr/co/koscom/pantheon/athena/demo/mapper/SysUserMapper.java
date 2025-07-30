package kr.co.koscom.pantheon.athena.demo.mapper;

import kr.co.koscom.pantheon.athena.base.db.plugins.page.XPage;
import kr.co.koscom.pantheon.athena.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user where age >= #{su.age} limit #{pg.limit} offset #{pg.offset}")
    List<SysUser> selectPage(@Param("su") SysUser su, @Param("pg") XPage pg);

    @Select("select id, name, age, email from sys_user where age > #{su.age} - 100")
    List<SysUser> selectContPage(@Param("su") SysUser su, @Param("pg") XPage pg);
}
