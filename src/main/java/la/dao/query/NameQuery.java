package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.query.params.NameBindParams;

/**
 * 商品名あいまい検索の問い合わせクラス
 */
public class NameQuery extends AbstractQery<NameBindParams> {

	/** クラス定数：実行するSQL文字列 */
	private static final String SQL = "SELECT * FROM item WHERE name LIKE ? ORDER BY code LIMIT ? OFFSET ?";
	
	/**
	 * 引数なしコンストラクタ
	 */
	public NameQuery() {}

	/**
	 * コンストラクタ
	 * @param criteria 検索条件
	 */
	public NameQuery(NameBindParams params) {
		this.setParams(params);
	}

	/**
	 * @see AbstractQery#getSql()
	 */
	@Override
	public String getSql() {
		return SQL;
	}

	/**
	 * @see AbstractQery#bind(PreparedStatement)
	 */
	@Override
	public void bind(PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, "%" + this.getParams().getKeyword() + "%");
		pstmt.setInt(2, this.getParams().getPageSize());
		pstmt.setInt(3, this.calcOffset());
	}

}
