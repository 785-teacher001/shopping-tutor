package la.dao.query.params;

public abstract class AbstractPaginationParams {
	
	/**
	 * フィールド
	 */
	private int pageSize; // ページあたり件数
	private int page;     // 表示するページ番号
	
	/**
	 * 引数なしコンストラクタ
	 */
	public AbstractPaginationParams() {}

	/**
	 * コンストラクタ
	 * @param pageSize ページあたり件数
	 * @param page     表示するページ番号
	 */
	public AbstractPaginationParams(int pageSize, int page) {
		super();
		this.pageSize = pageSize;
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
