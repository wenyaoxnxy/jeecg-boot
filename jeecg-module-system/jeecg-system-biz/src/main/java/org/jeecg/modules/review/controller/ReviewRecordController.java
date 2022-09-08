package org.jeecg.modules.review.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.code.entity.ReviewCode;
import org.jeecg.modules.publish.entity.ReviewPublish;
import org.jeecg.modules.review.entity.ReviewRecord;
import org.jeecg.modules.review.handle.IReviewRecordHandle;
import org.jeecg.modules.review.service.IReviewRecordService;
import java.util.Date;
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
 * @Description: 评审记录登记表
 * @Author: jeecg-boot
 * @Date:   2022-08-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评审记录登记表")
@RestController
@RequestMapping("/review/reviewRecord")
public class ReviewRecordController extends JeecgController<ReviewRecord, IReviewRecordService> {
	@Autowired
	private IReviewRecordService reviewRecordService;

	@Autowired
	private IReviewRecordHandle reviewRecordHandle;
	
	/**
	 * 分页列表查询
	 *
	 * @param reviewRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-分页列表查询")
	@ApiOperation(value="评审记录登记表-分页列表查询", notes="评审记录登记表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ReviewRecord reviewRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ReviewRecord> queryWrapper = QueryGenerator.initQueryWrapper(reviewRecord, req.getParameterMap());
		Page<ReviewRecord> page = new Page<ReviewRecord>(pageNo, pageSize);
		IPage<ReviewRecord> pageList = reviewRecordService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param reviewRecord
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-添加")
	@ApiOperation(value="评审记录登记表-添加", notes="评审记录登记表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ReviewRecord reviewRecord) {
		reviewRecordService.save(reviewRecord);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param reviewRecord
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-编辑")
	@ApiOperation(value="评审记录登记表-编辑", notes="评审记录登记表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ReviewRecord reviewRecord) {
		reviewRecordService.updateById(reviewRecord);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-通过id删除")
	@ApiOperation(value="评审记录登记表-通过id删除", notes="评审记录登记表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		reviewRecordService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-批量删除")
	@ApiOperation(value="评审记录登记表-批量删除", notes="评审记录登记表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.reviewRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评审记录登记表-通过id查询")
	@ApiOperation(value="评审记录登记表-通过id查询", notes="评审记录登记表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ReviewRecord reviewRecord = reviewRecordService.getById(id);
		return Result.OK(reviewRecord);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param reviewRecord
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ReviewRecord reviewRecord) {
      return super.exportXls(request, reviewRecord, ReviewRecord.class, "评审记录登记表");
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
      return super.importExcel(request, response, ReviewRecord.class);
  }

	 /**
	  * 发起代码评审
	  *
	  * @param reviewRecord
	  * @return
	  */
	 @AutoLog(value = "评审记录登记表-发起代码评审")
	 @ApiOperation(value="评审记录登记表-发起代码评审", notes="评审记录登记表-发起代码评审")
	 @PostMapping(value = "/addCodeReview")
	 public Result<?> addCodeReview(@RequestBody ReviewRecord reviewRecord) {
		 ReviewCode reviewCode = reviewRecordHandle.perCheckCodeReview(reviewRecord);
		 return Result.OK(reviewCode);
	 }
	 /**
	  * 发起上线评审
	  *
	  * @param addPublishReview
	  * @return
	  */
	 @AutoLog(value = "评审记录登记表-发起上线评审")
	 @ApiOperation(value="评审记录登记表-发起代码评审", notes="评审记录登记表-发起代码评审")
	 @PostMapping(value = "/addPublishReview")
	 public Result<?> addPublishReview(@RequestBody ReviewRecord reviewRecord) {
		 ReviewPublish reviewPublish = reviewRecordHandle.perCheckPublishReview(reviewRecord);
		 return Result.OK(reviewPublish);
	 }
}
