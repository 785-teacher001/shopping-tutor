package la.dao.query.params;

public class CategoryBindParams extends AbstractPaginationParams {

	/**
	 * フィールド
	 */
	private int categoryCode; // カテゴリコード

	/**
	 * 引数なしコンストラクタ
	 */
	public CategoryBindParams() {}

	/**
	 * コンストラクタ
	 * @param categoryCode カテゴリコード
	 */
	public CategoryBindParams(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * コンストラクタ
	 * @param pageSize ページあたり件数
	 * @param page     表示するページ番号
	 */
	public CategoryBindParams(int pageSize, int page) {
		super(pageSize, page);
	}

	/**
	 * コンストラクタ
	 * @param categoryCode カテゴリコード
	 * @param pageSize     ページあたり件数
	 * @param page         表示するページ番号
	 */
	public CategoryBindParams(int categoryCode, int pageSize, int page) {
		this(pageSize, page);
		this.categoryCode = categoryCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	
}
