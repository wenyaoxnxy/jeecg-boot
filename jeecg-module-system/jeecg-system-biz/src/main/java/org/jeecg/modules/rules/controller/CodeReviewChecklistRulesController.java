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
import org.jeecg.modules.rules.entity.CodeReviewChecklistRules;
import org.jeecg.modules.rules.service.ICodeReviewChecklistRulesService;
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
 * @Description: 代码检查规则列表
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="代码检查规则列表")
@RestController
@RequestMapping("/rules/codeReviewChecklistRules")
public class CodeReviewChecklistRulesController extends JeecgController<CodeReviewChecklistRules, ICodeReviewChecklistRulesService> {
	@Autowired
	private ICodeReviewChecklistRulesService codeReviewChecklistRulesService;
	
	/**
	 * 分页列表查询
	 *
	 * @param codeReviewChecklistRules
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-分页列表查询")
	@ApiOperation(value="代码检查规则列表-分页列表查询", notes="代码检查规则列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CodeReviewChecklistRules codeReviewChecklistRules,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Map<String, String[]> newParameterMap = new HashMap(req.getParameterMap());
		newParameterMap.remove("order");
		QueryWrapper<CodeReviewChecklistRules> queryWrapper = QueryGenerator.initQueryWrapper(codeReviewChecklistRules, newParameterMap);
		Page<CodeReviewChecklistRules> page = new Page<CodeReviewChecklistRules>(pageNo, pageSize);
		queryWrapper.orderByAsc("check_group","seq_no");
		IPage<CodeReviewChecklistRules> pageList = codeReviewChecklistRulesService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param codeReviewChecklistRules
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-添加")
	@ApiOperation(value="代码检查规则列表-添加", notes="代码检查规则列表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CodeReviewChecklistRules codeReviewChecklistRules) {
		codeReviewChecklistRulesService.save(codeReviewChecklistRules);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param codeReviewChecklistRules
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-编辑")
	@ApiOperation(value="代码检查规则列表-编辑", notes="代码检查规则列表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody CodeReviewChecklistRules codeReviewChecklistRules) {
		codeReviewChecklistRulesService.updateById(codeReviewChecklistRules);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-通过id删除")
	@ApiOperation(value="代码检查规则列表-通过id删除", notes="代码检查规则列表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		codeReviewChecklistRulesService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-批量删除")
	@ApiOperation(value="代码检查规则列表-批量删除", notes="代码检查规则列表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.codeReviewChecklistRulesService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码检查规则列表-通过id查询")
	@ApiOperation(value="代码检查规则列表-通过id查询", notes="代码检查规则列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CodeReviewChecklistRules codeReviewChecklistRules = codeReviewChecklistRulesService.getById(id);
		return Result.OK(codeReviewChecklistRules);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param codeReviewChecklistRules
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CodeReviewChecklistRules codeReviewChecklistRules) {
      return super.exportXls(request, codeReviewChecklistRules, CodeReviewChecklistRules.class, "代码检查规则列表");
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
      return super.importExcel(request, response, CodeReviewChecklistRules.class);
  }

}
