package shop.steamowls.steam.admin.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.steamowls.steam.admin.product.vo.ProductVo;

import static shop.steamowls.common.JdbcUtil.close;

public class ProductDao {
	private Connection con;

	public ProductDao() {

	}

	private static class LazyHolder {
		private static final ProductDao INSTANCE = new ProductDao();
	}

	public static ProductDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	
	public int pAdd(ProductVo productVo) {

		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement("insert into owls_product_tb(product_name, product_detail, product_people, product_price) values(?, ?, ?, ?)");
			pstmt.setString(1, productVo.getProduct_name());
			pstmt.setString(2, productVo.getProduct_detail());
			pstmt.setInt(3, productVo.getProduct_people());
			pstmt.setInt(4, productVo.getProduct_price());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<ProductVo> getProductList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from owls_product_tb where product_del_fl = 0");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProduct_sq(rs.getInt("product_sq"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_detail(rs.getString("product_detail"));
				vo.setProduct_people(rs.getInt("product_people"));
				vo.setProduct_price(rs.getInt("product_price"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int pDelete(ProductVo productVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("Update owls_product_tb set product_del_fl = 1 where product_sq = ? and product_del_fl = 0");
			pstmt.setInt(1, productVo.getProduct_sq());

			count = pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	

}