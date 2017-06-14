package easybuy.util;


public class PageUtil {
	private int curPage;
	private int rowsPrePage;
	private int maxPage;
	private Long maxRowsCount;

	
	public PageUtil(int rowsPrePage, Long maxRowsCount) {

		this.rowsPrePage = rowsPrePage;
		this.maxRowsCount = maxRowsCount;
		maxPage();//
	}

	public PageUtil() {

	}

	
	public void maxPage() {

		if (maxRowsCount % rowsPrePage == 0) {

			maxPage = Integer.parseInt(maxRowsCount.toString())/ rowsPrePage;
		} else {
			maxPage =  Integer.parseInt(maxRowsCount.toString()) / rowsPrePage + 1;
		}
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getRowsPrePage() {
		return rowsPrePage;
	}

	public void setRowsPrePage(int rowsPrePage) {
		this.rowsPrePage = rowsPrePage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public Long getMaxRowsCount() {
		return maxRowsCount;
	}

	public void setMaxRowsCount(Long maxRowsCount) {
		this.maxRowsCount = maxRowsCount;
	}

}
