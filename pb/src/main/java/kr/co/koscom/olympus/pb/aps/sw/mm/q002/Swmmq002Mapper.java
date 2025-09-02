package kr.co.koscom.olympus.pb.aps.sw.mm.q002;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.koscom.olympus.pb.ab.db.page.PBCPage;

@Mapper
public interface Swmmq002Mapper {

	List<Swmmq002QryOut> q002List(@Param("in") Swmmq002QryIn in);

	List<Swmmq002QryOut> x002List(@Param("in") Swmmx002QryIn in, @Param("pg") PBCPage page);

}
