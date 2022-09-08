package org.jeecg.modules.code.controller;

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
import org.jeecg.modules.code.entity.ReviewCodeDetail;
import org.jeecg.modules.code.entity.ReviewCodeChecklistResult;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.code.vo.ReviewCodePage;
import org.jeecg.modules.code.service.IReviewCodeService;
import org.jeecg.modules.code.service.IReviewCodeDetailService;
import org.jeecg.modules.code.service.IReviewCodeChecklistResultService;
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
 * @Description: 代码评审记录
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Api(tags="代码评审记录")
@RestController
@RequestMapping("/code/reviewCode")
@Slf4j
public class ReviewCodeController {
	@Autowired
	private IReviewCodeService reviewCodeService;
	@Autowired
	private IReviewCodeDetailService reviewCodeDetailService;
	@Autowired
	private IReviewCodeChecklistResultService reviewCodeChecklistResultService;
	
	/**
	 * 分页列表查询
	 *
	 * @param reviewCode
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "代码评审记录-分页列表查询")
	@ApiOperation(value="代码评审记录-分页列表查询", notes="代码评审记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ReviewCode reviewCode,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ReviewCode> queryWrapper = QueryGenerator.initQueryWrapper(reviewCode, req.getParameterMap());
		Page<ReviewCode> page = new Page<ReviewCode>(pageNo, pageSize);
		queryWrapper.orderByDesc("id");
		IPage<ReviewCode> pageList = reviewCodeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param reviewCodePage
	 * @return
	 */
	@AutoLog(value = "代码评审记录-添加")
	@ApiOperation(value="代码评审记录-添加", notes="代码评审记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ReviewCodePage reviewCodePage) {
		ReviewCode reviewCode = new ReviewCode();
		BeanUtils.copyProperties(reviewCodePage, reviewCode);
		reviewCodeService.saveMain(reviewCode, reviewCodePage.getReviewCodeDetailList(),reviewCodePage.getReviewCodeChecklistResultList());
		return Result.OK("添加成功!");
	}
	
	/**
	 * 编辑
	 *
	 * @param reviewCodePage
	 * @return
	 */
	@AutoLog(value = "代码评审记录-编辑")
	@ApiOperation(value="代码评审记录-编辑", notes="代码评审记录-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ReviewCodePage reviewCodePage) {
		ReviewCode reviewCode = new ReviewCode();
		BeanUtils.copyProperties(reviewCodePage, reviewCode);
		reviewCodeService.updateMain(reviewCode, reviewCodePage.getReviewCodeDetailList(),reviewCodePage.getReviewCodeChecklistResultList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码评审记录-通过id删除")
	@ApiOperation(value="代码评审记录-通过id删除", notes="代码评审记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
	    reviewCodeService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "代码评审记录-批量删除")
	@ApiOperation(value="代码评审记录-批量删除", notes="代码评审记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.reviewCodeService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码评审记录-通过id查询")
	@ApiOperation(value="代码评审记录-通过id查询", notes="代码评审记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ReviewCode reviewCode = reviewCodeService.getById(id);
		return Result.OK(reviewCode);
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码评审明细记录-通过主表ID查询")
	@ApiOperation(value="代码评审明细记录-通过主表ID查询", notes="代码评审记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewCodeDetailByMainId")
	public Result<?> queryReviewCodeDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewCodeDetail> reviewCodeDetailList = reviewCodeDetailService.selectByMainId(id);
		return Result.OK(reviewCodeDetailList);
	}
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "代码评审检查清单检查结果-通过主表ID查询")
	@ApiOperation(value="代码评审检查清单检查结果-通过主表ID查询", notes="代码评审记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewCodeChecklistResultByMainId")
	public Result<?> queryReviewCodeChecklistResultListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewCodeChecklistResult> reviewCodeChecklistResultList = reviewCodeChecklistResultService.selectByMainId(id);
		return Result.OK(reviewCodeChecklistResultList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param reviewCode
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ReviewCode reviewCode) {
      // Step.1 组装查询条件
      QueryWrapper<ReviewCode> queryWrapper = QueryGenerator.initQueryWrapper(reviewCode, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ReviewCodePage> pageList = new ArrayList<ReviewCodePage>();
      List<ReviewCode> reviewCodeList = reviewCodeService.list(queryWrapper);
      for (ReviewCode temp : reviewCodeList) {
          ReviewCodePage vo = new ReviewCodePage();
          BeanUtils.copyProperties(temp, vo);
          List<ReviewCodeDetail> reviewCodeDetailList = reviewCodeDetailService.selectByMainId(temp.getId());
          vo.setReviewCodeDetailList(reviewCodeDetailList);
          List<ReviewCodeChecklistResult> reviewCodeChecklistResultList = reviewCodeChecklistResultService.selectByMainId(temp.getId());
          vo.setReviewCodeChecklistResultList(reviewCodeChecklistResultList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "代码评审记录");
      mv.addObject(NormalExcelConstants.CLASS, ReviewCodePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("代码评审记录数据", "导出人:"+sysUser.getRealname(), "代码评审记录"));
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
              List<ReviewCodePage> list = ExcelImportUtil.importExcel(file.getInputStream(), ReviewCodePage.class, params);
              for (ReviewCodePage page : list) {
                  ReviewCode po = new ReviewCode();
                  BeanUtils.copyProperties(page, po);
                  reviewCodeService.saveMain(po, page.getReviewCodeDetailList(),page.getReviewCodeChecklistResultList());
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
