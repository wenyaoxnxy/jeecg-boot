package org.jeecg.modules.rules.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.rules.entity.PublishReviewChecklistRules;
import org.jeecg.modules.rules.service.IPublishReviewChecklistRulesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 上线检查规则列表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="上线检查规则列表")
@RestController
@RequestMapping("/rules/publishReviewChecklistRules")
public class PublishReviewChecklistRulesController extends JeecgController<PublishReviewChecklistRules, IPublishReviewChecklistRulesService> {
	@Autowired
	private IPublishReviewChecklistRulesService publishReviewChecklistRulesService;
	
	/**
	 * 分页列表查询
	 *
	 * @param publishReviewChecklistRules
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-分页列表查询")
	@ApiOperation(value="上线检查规则列表-分页列表查询", notes="上线检查规则列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PublishReviewChecklistRules publishReviewChecklistRules,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		Map<String, String[]> newParameterMap = new HashMap(req.getParameterMap());
		newParameterMap.remove("order");

		QueryWrapper<PublishReviewChecklistRules> queryWrapper = QueryGenerator.initQueryWrapper(publishReviewChecklistRules, newParameterMap);
		Page<PublishReviewChecklistRules> page = new Page<PublishReviewChecklistRules>(pageNo, pageSize);
		queryWrapper.orderByAsc(Arrays.asList("check_group","seq_no","create_time"));
		IPage<PublishReviewChecklistRules> pageList = publishReviewChecklistRulesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param publishReviewChecklistRules
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-添加")
	@ApiOperation(value="上线检查规则列表-添加", notes="上线检查规则列表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PublishReviewChecklistRules publishReviewChecklistRules) {
		publishReviewChecklistRulesService.save(publishReviewChecklistRules);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param publishReviewChecklistRules
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-编辑")
	@ApiOperation(value="上线检查规则列表-编辑", notes="上线检查规则列表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody PublishReviewChecklistRules publishReviewChecklistRules) {
		publishReviewChecklistRulesService.updateById(publishReviewChecklistRules);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-通过id删除")
	@ApiOperation(value="上线检查规则列表-通过id删除", notes="上线检查规则列表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		publishReviewChecklistRulesService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-批量删除")
	@ApiOperation(value="上线检查规则列表-批量删除", notes="上线检查规则列表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.publishReviewChecklistRulesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线检查规则列表-通过id查询")
	@ApiOperation(value="上线检查规则列表-通过id查询", notes="上线检查规则列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PublishReviewChecklistRules publishReviewChecklistRules = publishReviewChecklistRulesService.getById(id);
		return Result.OK(publishReviewChecklistRules);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param publishReviewChecklistRules
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PublishReviewChecklistRules publishReviewChecklistRules) {
      return super.exportXls(request, publishReviewChecklistRules, PublishReviewChecklistRules.class, "上线检查规则列表");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, PublishReviewChecklistRules.class);
  }

}
