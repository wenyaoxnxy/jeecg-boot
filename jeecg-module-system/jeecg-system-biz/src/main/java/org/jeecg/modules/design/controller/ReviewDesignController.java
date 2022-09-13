package org.jeecg.modules.design.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.design.entity.ReviewDesignDetail;
import org.jeecg.modules.design.entity.ReviewDesign;
import org.jeecg.modules.design.vo.ReviewDesignPage;
import org.jeecg.modules.design.service.IReviewDesignService;
import org.jeecg.modules.design.service.IReviewDesignDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 概设评审记录
 * @Author: jeecg-boot
 * @Date:   2022-09-13
 * @Version: V1.0
 */
@Api(tags="概设评审记录")
@RestController
@RequestMapping("/design/reviewDesign")
@Slf4j
public class ReviewDesignController {
	@Autowired
	private IReviewDesignService reviewDesignService;
	@Autowired
	private IReviewDesignDetailService reviewDesignDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param reviewDesign
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "概设评审记录-分页列表查询")
	@ApiOperation(value="概设评审记录-分页列表查询", notes="概设评审记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ReviewDesign reviewDesign,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ReviewDesign> queryWrapper = QueryGenerator.initQueryWrapper(reviewDesign, req.getParameterMap());
		Page<ReviewDesign> page = new Page<ReviewDesign>(pageNo, pageSize);
		IPage<ReviewDesign> pageList = reviewDesignService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param reviewDesignPage
	 * @return
	 */
	@AutoLog(value = "概设评审记录-添加")
	@ApiOperation(value="概设评审记录-添加", notes="概设评审记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ReviewDesignPage reviewDesignPage) {
		ReviewDesign reviewDesign = new ReviewDesign();
		BeanUtils.copyProperties(reviewDesignPage, reviewDesign);
		reviewDesignService.saveMain(reviewDesign, reviewDesignPage.getReviewDesignDetailList());
		return Result.OK("添加成功!");
	}
	
	/**
	 * 编辑
	 *
	 * @param reviewDesignPage
	 * @return
	 */
	@AutoLog(value = "概设评审记录-编辑")
	@ApiOperation(value="概设评审记录-编辑", notes="概设评审记录-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ReviewDesignPage reviewDesignPage) {
		ReviewDesign reviewDesign = new ReviewDesign();
		BeanUtils.copyProperties(reviewDesignPage, reviewDesign);
		reviewDesignService.updateMain(reviewDesign, reviewDesignPage.getReviewDesignDetailList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "概设评审记录-通过id删除")
	@ApiOperation(value="概设评审记录-通过id删除", notes="概设评审记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
	    reviewDesignService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "概设评审记录-批量删除")
	@ApiOperation(value="概设评审记录-批量删除", notes="概设评审记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.reviewDesignService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "概设评审记录-通过id查询")
	@ApiOperation(value="概设评审记录-通过id查询", notes="概设评审记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ReviewDesign reviewDesign = reviewDesignService.getById(id);
		return Result.OK(reviewDesign);
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "概设评审明细记录-通过主表ID查询")
	@ApiOperation(value="概设评审明细记录-通过主表ID查询", notes="概设评审记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewDesignDetailByMainId")
	public Result<?> queryReviewDesignDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewDesignDetail> reviewDesignDetailList = reviewDesignDetailService.selectByMainId(id);
		return Result.OK(reviewDesignDetailList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param reviewDesign
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ReviewDesign reviewDesign) {
      // Step.1 组装查询条件
      QueryWrapper<ReviewDesign> queryWrapper = QueryGenerator.initQueryWrapper(reviewDesign, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ReviewDesignPage> pageList = new ArrayList<ReviewDesignPage>();
      List<ReviewDesign> reviewDesignList = reviewDesignService.list(queryWrapper);
      for (ReviewDesign temp : reviewDesignList) {
          ReviewDesignPage vo = new ReviewDesignPage();
          BeanUtils.copyProperties(temp, vo);
          List<ReviewDesignDetail> reviewDesignDetailList = reviewDesignDetailService.selectByMainId(temp.getId());
          vo.setReviewDesignDetailList(reviewDesignDetailList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "概设评审记录");
      mv.addObject(NormalExcelConstants.CLASS, ReviewDesignPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("概设评审记录数据", "导出人:"+sysUser.getRealname(), "概设评审记录"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ReviewDesignPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ReviewDesignPage.class, params);
              for (ReviewDesignPage page : list) {
                  ReviewDesign po = new ReviewDesign();
                  BeanUtils.copyProperties(page, po);
                  reviewDesignService.saveMain(po, page.getReviewDesignDetailList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
  }

}
