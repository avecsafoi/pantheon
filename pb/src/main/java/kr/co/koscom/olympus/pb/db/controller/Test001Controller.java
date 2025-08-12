package kr.co.koscom.olympus.pb.db.controller;

import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import kr.co.koscom.olympus.pb.db.entity.Test001;
import kr.co.koscom.olympus.pb.db.service.Test001Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制层。
 *
 * @author KOSCOM
 * @since 2025-08-12
 */
@RestController
@RequestMapping("/test001")
public class Test001Controller {

    @Resource
    private Test001Service test001Service;

    /**
     * 保存。
     *
     * @param test001
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Test001 test001) {
        return test001Service.save(test001);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable long id) {
        return test001Service.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param test001
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Test001 test001) {
        return test001Service.updateById(test001);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Test001> list() {
        return test001Service.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Test001 getInfo(@PathVariable long id) {
        return test001Service.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Test001> page(Page<Test001> page) {
        return test001Service.page(page);
    }

}
