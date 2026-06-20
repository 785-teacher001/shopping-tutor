package la.dao.criteria;

/**
 * 商品名あいまい検索条件クラス
 */
public class NameCriteria extends AbstractCriteria {

	/**
	 * フィールド
	 */
	private String keyword; // 検索キーワード
	
	/**
	 * 引数なしコンストラクタ
	 */
	public NameCriteria() {}

	/**
	 * コンストラクタ
	 * @param keyword  検索キーワード
	 * @param pageSize ページあたり表示件数
	 * @param page     表示するページ番号
	 */
	public NameCriteria(String keyword, int pageSize, int page) {
		super(pageSize, page);
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
