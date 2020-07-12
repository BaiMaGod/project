package com.common.form;

import com.github.pagehelper.PageInfo;
import com.common.result.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageForm implements BeforeForm{
    @ApiModelProperty(value = "第几页,默认第一页")
    protected int page = 1;
    @ApiModelProperty(value = "每页条数,默认每页10条")
    protected int limit = 10;

    @ApiModelProperty(value = "排序规则，默认按创建时间倒序",hidden = true)
    protected String orderByClause = "createTime desc";

    public <T> Page pageHelperResult(PageInfo<T> pageInfo){
        Page page = new Page();

        page.setLimit(this.limit);
        page.setPage(this.page);
        page.setTotalRows(pageInfo.getTotal());
        page.setTotalPages(pageInfo.getNavigateLastPage());

        return page;
    }





    @Override
    public void filter() {
        System.out.println("filter");
    }


    @Override
    public void check() {
        System.out.println("check");
    }
}
