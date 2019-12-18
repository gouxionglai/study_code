/**
 * 
 */
package com.gxl.framework.utils;
 
import com.gxl.framework.model.SearchBaseObject;

import java.util.List;
import java.util.Map;


/**
 * 分页工具类
 */
public class PageHelper
{
    // 总共的数据量
    private int total;
    private Integer rowNumberStart;
    private Integer rowNumberEnd;
    
    private String ascRow;
    private String descRow;
 
    // 每页显示多少条
    private int pageSize = 20;
 
    // 共有多少页
    private int totalPage;
 
    // 当前是第几页
    private int pageIndex = 1;
    
//    //传参使用
//    private SearchBaseObject paramObject;
 
    // 分页使用
    private List<?> data;
 
    // 连接路径
    private String path = "";
 
    /**
     * 页码HTML信息
     */
    @SuppressWarnings("unused")
    private String pageHTML;
 
    private int startPage; // 开始页面
 
    private int endPage; // 结束页面
 
    private int displayNum = 10; // 显示的页数
    
    public void init(SearchBaseObject object) {
      
        rowNumberStart = (pageIndex - 1) * pageSize;
        rowNumberEnd = pageIndex * pageSize;

     
        if (ascRow != null) {
            object.setOrderBy(ascRow + " asc");
        } else if (descRow != null) {
            object.setOrderBy(descRow + " desc");
        }

        object.setOffset(rowNumberStart);
        object.setRow_count(pageSize);


    }

    public void finish(Map<String, ?> result) {
        
        setData((List<?>)result.get(BOConstants.VALUE_LIST));
        total = (Integer) result.get(BOConstants.ROW_COUNT);
        rowNumberEnd = rowNumberEnd > total ? total : rowNumberEnd;
        totalPage = (total + pageSize - 1) / pageSize;

    }
 
    /**
     * @return the startPage
     */
    public int getStartPage()
    {
        return startPage;
    }
 
    /**
     * @return the endPage
     */
    public int getEndPage()
    {
        return endPage;
    }
 
    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }
 
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
 
    public void setIndex(int index)
    {
        this.pageIndex = index;
    }
 
    /**
     * 设置路径前缀，页面第一页index为1
     * 
     * @param path
     *            此path含有参数形式，如/aa/article?page=,或者/bb/article/list/
     */
    public void setPath(String path)
    {
        this.path = path;
    }
 
    public int getPageSize()
    {
        return pageSize;
    }
 
    public int getTotalPage()
    {
        return (this.total + this.pageSize - 1) / this.pageSize;
    }
 
   
 
//    /**
//     * @return the rowNumberStart
//     */
//    public Integer getRowNumberStart() {
//        return rowNumberStart;
//    }
//
//    /**
//     * @param rowNumberStart the rowNumberStart to set
//     */
//    public void setRowNumberStart(Integer rowNumberStart) {
//        this.rowNumberStart = rowNumberStart;
//    }
//
//    /**
//     * @return the rowNumberEnd
//     */
//    public Integer getRowNumberEnd() {
//        return rowNumberEnd;
//    }
//
//    /**
//     * @param rowNumberEnd the rowNumberEnd to set
//     */
//    public void setRowNumberEnd(Integer rowNumberEnd) {
//        this.rowNumberEnd = rowNumberEnd;
//    }

    /**
     * @return the ascRow
     */
    public String getAscRow() {
        return ascRow;
    }

    /**
     * @param ascRow the ascRow to set
     */
    public void setAscRow(String ascRow) {
        this.ascRow = ascRow;
    }

    /**
     * @return the descRow
     */
    public String getDescRow() {
        return descRow;
    }

    /**
     * @param descRow the descRow to set
     */
    public void setDescRow(String descRow) {
        this.descRow = descRow;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

  

    /**
     * @return the displayNum
     */
    public int getDisplayNum() {
        return displayNum;
    }

    /**
     * @param displayNum the displayNum to set
     */
    public void setDisplayNum(int displayNum) {
        this.displayNum = displayNum;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @param startPage the startPage to set
     */
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    /**
     * @param endPage the endPage to set
     */
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List<?> getData()
    {
        return data;
    }
 
    public void setData(List<?> data)
    {
        this.data = data;
    }
 
    /**
     * @return the total
     */
    public int getTotal()
    {
        return total;
    }
 
    /**
     * @param total
     *            the total to set
     */
    public void setTotal(int total)
    {
        this.total = total;
    }
 
    public String getPageHTML()
    {
        totalPage = getTotalPage();
        StringBuffer displayInfo = new StringBuffer();
        if (totalPage != 0 && pageSize != 0)
        {
            displayInfo.append("<div class='pager'>");
            displayInfo.append("<span class='item nolink'>共<span class='num'>" + totalPage + "</span>页/<span class='num'>" + total + "</span>条记录</span>");
            //displayInfo.append("<span class='item nolink'>/<span class='num'>" + total + "</span>条记录</span>");
            // 判断如果当前是第一页 则“首页”和“第一页”失去链接
            if (pageIndex <= 1)
            {
                displayInfo.append("<span class='item nolink'>首页</span>");
                displayInfo.append("<span class='item nolink'>上一页</span>");
            }
            else
            {
                displayInfo.append("<span class='item'><a href='" + path + "1'>首页</a></span>");
                displayInfo.append("<span class='item'><a href='" + path + (pageIndex - 1) + "'>上一页</a></span>");
            }
 
            countPages();
            displayInfo.append("<span class='item nums'>");
            for (int i = startPage; i <= endPage; i++)
            {
                if (i == pageIndex)
                {
                    displayInfo.append("<span class='nolink'>" + i + "</span>");
                }
                else
                {
                    displayInfo.append("<a href='" + path + i + "'>" + i + "</a>");
                }
            }
            displayInfo.append("</span>");
 
            if (pageIndex >= totalPage)
            {
                displayInfo.append("<span class='item nolink'>下一页</span>");
                displayInfo.append("<span class='item nolink'>尾页</span>");
            }
            else
            {
                displayInfo.append("<span class='item'><a href='" + path + (pageIndex + 1) + "'>下一页</a></span>");
                displayInfo.append("<span class='item'><a href='" + path + totalPage + "'>尾页</a></span>");
            }
            displayInfo.append("</div>");
        }
        return displayInfo.toString();
    }
 
    /**
     * 计算起始页和结束页
     */
    public void countPages()
    {
 
        if (pageIndex - displayNum / 2 < 1)
        {
            startPage = 1;
            endPage = displayNum > totalPage ? totalPage : displayNum;
        }
        else if (pageIndex + displayNum / 2 > totalPage)
        {
            int n = totalPage - displayNum + 1;
            startPage = n > 0 ? n : 1;
            endPage = totalPage;
        }
        else
        {
            startPage = pageIndex - displayNum / 2;
            endPage = startPage + displayNum - 1;
        }
    }
 
    /**
     * @param pageHTML the pageHTML to set
     */
    public void setPageHTML(String pageHTML)
    {
        this.pageHTML = pageHTML;
    }
 
    public static void main(String[] args)
    {
        PageHelper p = new PageHelper();
        // p.totalPage = p.getTotalPage();
        p.setTotal(1002);
        p.setPageSize(20);
        p.setPath("/bb/article/list/");
        // for (int i = -80; i < 80; i++)
        // {
        // p.setIndex(i);
        // p.countPages();
        // System.out.println(i+"----"+p.getStartPage() + "-----" +
        // p.getEndPage());
        // }
        p.setIndex(33);
        System.out.println(p.getPageHTML());
    }

    /**
     * @return the rowNumberStart
     */
    public Integer getRowNumberStart() {
        return rowNumberStart;
    }

    /**
     * @param rowNumberStart the rowNumberStart to set
     */
    public void setRowNumberStart(Integer rowNumberStart) {
        this.rowNumberStart = rowNumberStart;
    }

    /**
     * @return the rowNumberEnd
     */
    public Integer getRowNumberEnd() {
        return rowNumberEnd;
    }

    /**
     * @param rowNumberEnd the rowNumberEnd to set
     */
    public void setRowNumberEnd(Integer rowNumberEnd) {
        this.rowNumberEnd = rowNumberEnd;
    }
    
    
}

