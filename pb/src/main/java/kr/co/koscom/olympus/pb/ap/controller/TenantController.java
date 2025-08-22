package kr.co.koscom.olympus.pb.ap.controller;

import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.ap.entity.Tenant;
import kr.co.koscom.olympus.pb.ap.service.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;

    /**
     * 保存。
     *
     * @param tenant DB 구분용 회원사코드 값
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Tenant tenant) {
        return tenantService.save(tenant);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return tenantService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param tenant DB 구분용 회원사코드 값
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Tenant tenant) {
        return tenantService.updateById(tenant);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Tenant> list() {
        return tenantService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Tenant getInfo(@PathVariable Long id) {
        return tenantService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Tenant> page(Page<Tenant> page) {
        return tenantService.page(page);
    }

}
