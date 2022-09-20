package org.jeecg.modules.publish.controller;

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
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.publish.entity.ReviewPublishDetail;
import org.jeecg.modules.publish.entity.ReviewPublishChecklistResult;
import org.jeecg.modules.publish.entity.ReviewPublishMain;
import org.jeecg.modules.publish.vo.ReviewPublishMainPage;
import org.jeecg.modules.publish.service.IReviewPublishMainService;
import org.jeecg.modules.publish.service.IReviewPublishService;
import org.jeecg.modules.publish.service.IReviewPublishDetailService;
import org.jeecg.modules.publish.service.IReviewPublishChecklistResultService;
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
 * @Description: 上线评审系统记录
 * @Author: jeecg-boot
 * @Date:   2022-09-20
 * @Version: V1.0
 */
@Api(tags="上线评审系统记录")
@RestController
@RequestMapping("/publish/reviewPublishMain")
@Slf4j
public class ReviewPublishMainController {
	@Autowired
	private IReviewPublishMainService reviewPublishMainService;
	@Autowired
	private IReviewPublishService reviewPublishService;
	@Autowired
	private IReviewPublishDetailService reviewPublishDetailService;
	@Autowired
	private IReviewPublishChecklistResultService reviewPublishChecklistResultService;
	
	/**
	 * 分页列表查询
	 *
	 * @param reviewPublishMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-分页列表查询")
	@ApiOperation(value="上线评审系统记录-分页列表查询", notes="上线评审系统记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ReviewPublishMain reviewPublishMain,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ReviewPublishMain> queryWrapper = QueryGenerator.initQueryWrapper(reviewPublishMain, req.getParameterMap());
		Page<ReviewPublishMain> page = new Page<ReviewPublishMain>(pageNo, pageSize);
		IPage<ReviewPublishMain> pageList = reviewPublishMainService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param reviewPublishMainPage
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-添加")
	@ApiOperation(value="上线评审系统记录-添加", notes="上线评审系统记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ReviewPublishMainPage reviewPublishMainPage) {
		ReviewPublishMain reviewPublishMain = new ReviewPublishMain();
		BeanUtils.copyProperties(reviewPublishMainPage, reviewPublishMain);
		reviewPublishMainService.saveMain(reviewPublishMain, reviewPublishMainPage.getReviewPublishList(),reviewPublishMainPage.getReviewPublishDetailList(),reviewPublishMainPage.getReviewPublishChecklistResultList());
		return Result.OK("添加成功!");
	}
	
	/**
	 * 编辑
	 *
	 * @param reviewPublishMainPage
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-编辑")
	@ApiOperation(value="上线评审系统记录-编辑", notes="上线评审系统记录-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ReviewPublishMainPage reviewPublishMainPage) {
		ReviewPublishMain reviewPublishMain = new ReviewPublishMain();
		BeanUtils.copyProperties(reviewPublishMainPage, reviewPublishMain);
		reviewPublishMainService.updateMain(reviewPublishMain, reviewPublishMainPage.getReviewPublishList(),reviewPublishMainPage.getReviewPublishDetailList(),reviewPublishMainPage.getReviewPublishChecklistResultList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-通过id删除")
	@ApiOperation(value="上线评审系统记录-通过id删除", notes="上线评审系统记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
	    reviewPublishMainService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-批量删除")
	@ApiOperation(value="上线评审系统记录-批量删除", notes="上线评审系统记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.reviewPublishMainService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线评审系统记录-通过id查询")
	@ApiOperation(value="上线评审系统记录-通过id查询", notes="上线评审系统记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ReviewPublishMain reviewPublishMain = reviewPublishMainService.getById(id);
		return Result.OK(reviewPublishMain);
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线评审需求明细记录-通过主表ID查询")
	@ApiOperation(value="上线评审需求明细记录-通过主表ID查询", notes="上线评审系统记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewPublishByMainId")
	public Result<?> queryReviewPublishListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewPublish> reviewPublishList = reviewPublishService.selectByMainId(id);
		return Result.OK(reviewPublishList);
	}
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线评审问题记录-通过主表ID查询")
	@ApiOperation(value="上线评审问题记录-通过主表ID查询", notes="上线评审系统记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewPublishDetailByMainId")
	public Result<?> queryReviewPublishDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewPublishDetail> reviewPublishDetailList = reviewPublishDetailService.selectByMainId(id);
		return Result.OK(reviewPublishDetailList);
	}
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "上线评审检查清单记录列表-通过主表ID查询")
	@ApiOperation(value="上线评审检查清单记录列表-通过主表ID查询", notes="上线评审系统记录-通过主表ID查询")
	@GetMapping(value = "/queryReviewPublishChecklistResultByMainId")
	public Result<?> queryReviewPublishChecklistResultListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ReviewPublishChecklistResult> reviewPublishChecklistResultList = reviewPublishChecklistResultService.selectByMainId(id);
		return Result.OK(reviewPublishChecklistResultList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param reviewPublishMain
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ReviewPublishMain reviewPublishMain) {
      // Step.1 组装查询条件
      QueryWrapper<ReviewPublishMain> queryWrapper = QueryGenerator.initQueryWrapper(reviewPublishMain, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ReviewPublishMainPage> pageList = new ArrayList<ReviewPublishMainPage>();
      List<ReviewPublishMain> reviewPublishMainList = reviewPublishMainService.list(queryWrapper);
      for (ReviewPublishMain temp : reviewPublishMainList) {
          ReviewPublishMainPage vo = new ReviewPublishMainPage();
          BeanUtils.copyProperties(temp, vo);
          List<ReviewPublish> reviewPublishList = reviewPublishService.selectByMainId(temp.getId());
          vo.setReviewPublishList(reviewPublishList);
          List<ReviewPublishDetail> reviewPublishDetailList = reviewPublishDetailService.selectByMainId(temp.getId());
          vo.setReviewPublishDetailList(reviewPublishDetailList);
          List<ReviewPublishChecklistResult> reviewPublishChecklistResultList = reviewPublishChecklistResultService.selectByMainId(temp.getId());
          vo.setReviewPublishChecklistResultList(reviewPublishChecklistResultList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "上线评审系统记录");
      mv.addObject(NormalExcelConstants.CLASS, ReviewPublishMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("上线评审系统记录数据", "导出人:"+sysUser.getRealname(), "上线评审系统记录"));
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
              List<ReviewPublishMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ReviewPublishMainPage.class, params);
              for (ReviewPublishMainPage page : list) {
                  ReviewPublishMain po = new ReviewPublishMain();
                  BeanUtils.copyProperties(page, po);
                  reviewPublishMainService.saveMain(po, page.getReviewPublishList(),page.getReviewPublishDetailList(),page.getReviewPublishChecklistResultList());
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
