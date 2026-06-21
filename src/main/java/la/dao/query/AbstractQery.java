package la.dao.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import la.dao.query.params.AbstractPaginationParams;

/**
 * 問い合わせクラス
 */
public abstract class AbstractQery<T extends AbstractPaginationParams> {
	
	/**
	 * フィールド
	 */
	private T params; // 検索条件クラス
	
	/**
	 * 引数なしコンストラクタ
	 */
	public AbstractQery() {}
	
	public T getParams() {
		return params;
	}
	
	public void setParams(T params) {
		this.params = params;
	}

	/**
	 * オフセット位置の計算
	 * @return オフセット位置（スキップ件数）
	 */
	protected int calcOffset() {
		int page = this.getParams().getPage();
		int pageSize = this.getParams().getPageSize();
		return (page - 1) * pageSize;
	}
	
	/**
	 * 実行するSQLを取得する
	 * @return 実行するSQL
	 */
	public abstract String getSql();
	
	/**
	 * パラメータでプレースホルダを置き換える
	 * @param pstmt SQL実行オブジェクト
	 * @throws SQLException 
	 */
	public abstract void bind(PreparedStatement pstmt) throws SQLException;
	
}
