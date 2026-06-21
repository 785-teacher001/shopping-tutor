package la.dao.criteria;

/**
 * カテゴリ検索条件クラス
 */
public class CategoryCriteria extends AbstractCriteria {
	
	/**
	 * フィールド
	 */
	private int categoryCode; // カテゴリコード
	
	/**
	 * 引数なしコンストラクタ
	 */
	public CategoryCriteria() {}

	/**
	 * コンストラクタ
	 * @param categoryCode カテゴリコード
	 * @param pageSize     ページあたり表示件数
	 * @param page         表示するページ番号
	 */
	public CategoryCriteria(int categoryCode, int pageSize, int page) {
		super(pageSize, page);
		this.categoryCode = categoryCode;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

}
