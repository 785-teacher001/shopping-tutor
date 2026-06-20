package la.dao.criteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 商品名あいまい検索条件クラス
 */
public class NameCriteria extends AbstractCriteria {

	/**
	 * フィールド
	 */
	private String sql = "SELECT * FROM item WHERE name LIKE ? ORDER BY code LIMIT ? OFFSET ?"; // 実行するSQL
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

	/**
	 * @see AbstractCriteria#getSql()
	 */
	@Override
	public String getSql() {
		return this.sql;
	}

	/**
	 * @see AbstractCriteria#bind(PreparedStatement)
	 */
	@Override
	public void bind(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setInt(2, this.getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
