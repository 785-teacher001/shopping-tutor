package test.dao;

import la.bean.ItemBean;

/**
 * 単体テスト用ItemmBean：テスト比較用のシリアル化メソッド実装
 */
public class ItemBeanEx extends ItemBean {

	public ItemBeanEx(int code, String name, int price) {
		super(code, name, price);
	}

	/**
	 * シリアル化メソッド
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ItemBean [");
		builder.append("code="  + this.getCode()  + ", ");
		builder.append("name="  + this.getName()  + ", ");
		builder.append("price=" + this.getPrice());
		builder.append("]");
		return builder.toString();
	}

}
