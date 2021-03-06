package ml.stephen.core.mybatis.page;

import java.util.List;

/**
 * Created by Stephen on 16/9/7.
 * 对PageList<E>结果进行包装
 */
@SuppressWarnings("rawtypes")
public class PageInfo {
	private int pageNum;
	private int pageSize;
	private int startRow;
	private int endRow;
	private long total;
	private int pages;
	private List list;

	public PageInfo(List list) {
		if (list instanceof PageList) {
			PageList pageList = (PageList) list;
			this.pageNum = pageList.getPageNum();
			this.pageSize = pageList.getPageSize();
			this.startRow = pageList.getStartRow();
			this.endRow = pageList.getEndRow();
			this.total = pageList.getTotal();
			this.pages = pageList.getPages();
			this.list = pageList;
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}