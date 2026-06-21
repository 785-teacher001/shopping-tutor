package la.dao.query.params;

public class NameBindParams extends AbstractPaginationParams {

	/**
	 * フィールド
	 */
	private String keyword; // 検索キーワード

	/**
	 * 引数なしコンストラクタ
	 */
	public NameBindParams() {}

	/**
	 * コンストラクタ
	 * @param keyword ページあたり件数
	 */
	public NameBindParams(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * コンストラクタ
	 * @param pageSize ページあたり件数
	 * @param page     表示するページ番号
	 */
	public NameBindParams(int pageSize, int page) {
		super(pageSize, page);
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param pageSize ページあたり件数
	 * @param page     表示するページ番号
	 */
	public NameBindParams(String keyword, int pageSize, int page) {
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
