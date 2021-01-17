package shop.steamowls.steam.admin.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.steamowls.steam.admin.admin.vo.AdminVo;
import shop.steamowls.steam.mypage.vo.BoardVo;

import static shop.steamowls.common.JdbcUtil.close;

public class QuestionDao {
	private Connection con;

	public QuestionDao() {

	}

	private static class LazyHolder {
		private static final QuestionDao INSTANCE = new QuestionDao();
	}

	public static QuestionDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public BoardVo qDetail(String board_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement("select obt.*, omt.id from owls_board_tb obt inner join owls_mber_tb omt on obt.member_sq = omt.sq  where board_sq = ?");
			pstmt.setInt(1, Integer.parseInt(board_sq));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setMember_sq(rs.getInt("member_sq"));
				vo.setMember_id(rs.getString("id"));
				vo.setBoard_subject(rs.getString("board_subject"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setBoard_address(rs.getString("board_address"));
				vo.setBoard_dttm(rs.getString("board_dttm"));
				vo.setAnswer_fl(rs.getBoolean("answer_fl"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	public BoardVo qAnswer(String board_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement("select * from owls_board_answer_tb where board_sq = ?");
			pstmt.setInt(1, Integer.parseInt(board_sq));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setAnswer_sq(rs.getInt("answer_sq"));
				vo.setAnswer_content(rs.getString("answer_content"));
				vo.setAnswer_dttm(rs.getString("answer_dttm"));
				vo.setAnswer_fl(rs.getBoolean("answer_fl"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	
	

	

}
