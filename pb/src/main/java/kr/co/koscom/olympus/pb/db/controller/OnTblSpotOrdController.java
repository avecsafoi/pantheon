package kr.co.koscom.olympus.pb.db.controller;

import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.OnTblSpotOrd;
import kr.co.koscom.olympus.pb.db.service.OnTblSpotOrdService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@RestController
@RequestMapping("/onTblSpotOrd")
public class OnTblSpotOrdController {

    @Resource
    private OnTblSpotOrdService onTblSpotOrdService;

    /**
     * 保存。
     *
     * @param onTblSpotOrd 주문데이블 로우 데이터
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody OnTblSpotOrd onTblSpotOrd) {
        return onTblSpotOrdService.save(onTblSpotOrd);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return onTblSpotOrdService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param onTblSpotOrd 주문데이블 로우 데이터
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody OnTblSpotOrd onTblSpotOrd) {
        return onTblSpotOrdService.updateById(onTblSpotOrd);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<OnTblSpotOrd> list() {
        return onTblSpotOrdService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public OnTblSpotOrd getInfo(@PathVariable String id) {
        return onTblSpotOrdService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<OnTblSpotOrd> page(Page<OnTblSpotOrd> page) {
        return onTblSpotOrdService.page(page);
    }

}
