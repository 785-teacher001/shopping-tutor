package la.dao.criteria;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractCriteria {
	
	/**
	 * フィールド
	 */
	private int pageSize; // ページあたりの表示件数
	private int page;     // 表示ページ番号
	
	/**
	 * 引数なしコンストラクタ
	 */
	public AbstractCriteria() {}

	/**
	 * コンストラクタ
	 * @param pageSize ページあたりの表示件数
	 * @param page     表示ページ番号
	 */
	public AbstractCriteria(int pageSize, int page) {
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
	
	/**
	 * オフセット位置を計算する
	 * @return オフセット位置（スキップ行数）
	 */
	protected int calcOffset() {
		return (this.page - 1) * this.pageSize;
	}
	
	/**
	 * SQLを取得する
	 * @return SQL文字列
	 */
	public abstract String getSql();
	
	/**
	 * パラメータバインディング
	 * @param pstmt SQL実行オブジェクト
	 * @throws SQLException 
	 */
	public abstract void bind(PreparedStatement pstmt) throws SQLException;
	
}
