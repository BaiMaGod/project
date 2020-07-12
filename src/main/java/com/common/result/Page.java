package com.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页信息对象
 */
@Data
public class Page {
    @ApiModelProperty(value = "当前第几页")
    private int page;
    @ApiModelProperty(value = "每页条数")
    private int limit;
    @ApiModelProperty(value = "总条数")
    private long totalRows;
    @ApiModelProperty(value = "总页数")
    private int totalPages;
    @ApiModelProperty(value = "起始条序号")
    private int startRow;
    @ApiModelProperty(value = "结束条序号")
    private int endRow;


    public Page() {}
    public Page(int curPage) {
        super();
        this.page = curPage >= 0 ? curPage : 1;
    }
    public Page(int curPage,int pageRows) {
        super();
        this.page = curPage >= 0 ? curPage : 1;
        this.limit = pageRows > 0 ? pageRows : 10;
    }


    public int getStartRow() {
        return this.limit*(this.page-1);
    }

    public int getEndRow() {
        return this.startRow+this.limit;
    }

    public int getTotalPages() {
        if(this.limit>0){
            return (int) (this.totalRows+this.limit-1)/this.limit;
        }

        return 1;
    }
    

}