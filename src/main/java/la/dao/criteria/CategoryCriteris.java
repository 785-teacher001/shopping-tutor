package la.dao.criteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * カテゴリ検索条件クラス
 */
public class CategoryCriteris extends AbstractCriteria {
	
	/**
	 * フィールド
	 */
	private String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT ? OFFSET ?"; // 実行するSQL
	private int categoryCode; // カテゴリコード

	
	/**
	 * 非数なしコンストラクタ
	 */
	public CategoryCriteris() {}

	/**
	 * コンストラクタ
	 * @param categoryCode カテゴリコード
	 * @param pageSize     ページあたり表示件数
	 * @param page         表示するページ番号
	 */
	public CategoryCriteris(int categoryCode, int pageSize, int page) {
		super(pageSize, page);
		this.categoryCode = categoryCode;
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
		pstmt.setInt(1, this.categoryCode);
		pstmt.setInt(2, this.getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
