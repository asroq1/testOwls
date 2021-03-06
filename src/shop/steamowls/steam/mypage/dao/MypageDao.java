package shop.steamowls.steam.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.steamowls.common.Pagenation;
import shop.steamowls.steam.booking.vo.BookingVo;
import shop.steamowls.steam.member.vo.MemberVo;
import shop.steamowls.steam.mypage.vo.BoardVo;
import shop.steamowls.steam.mypage.vo.MypageVo;

import static shop.steamowls.common.JdbcUtil.close;

public class MypageDao {
	private Connection con;

	public MypageDao() {

	}

	private static class LazyHolder {
		private static final MypageDao INSTANCE = new MypageDao();
	}

	public static MypageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int modify(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			if (mypageVo.getPw() == null) {
				pstmt = con.prepareStatement("update owls_mber_tb set name = ?, tel = ? where sq = ? and del_fl = 0");
				pstmt.setString(1, mypageVo.getName());
				pstmt.setString(2, mypageVo.getTel());
				pstmt.setInt(3, mypageVo.getSq());

				count = pstmt.executeUpdate();

			} else {
				pstmt = con.prepareStatement(
						"update owls_mber_tb set name = ?, pw = ?, tel = ? where sq = ? and del_fl = 0");
				pstmt.setString(1, mypageVo.getName());
				pstmt.setString(2, mypageVo.getPw());
				pstmt.setString(3, mypageVo.getTel());
				pstmt.setInt(4, mypageVo.getSq());

				count = pstmt.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int withdraw(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			pstmt = con.prepareStatement("update owls_mber_tb set del_fl = 1 where sq = ? and del_fl = 0");
			pstmt.setInt(1, mypageVo.getSq());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public MypageVo mCheckPw(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MypageVo vo = null;
		try {
			pstmt = con
					.prepareStatement("select sq, name, pw, tel from owls_mber_tb " + "where sq = ? and del_fl = 0 ");
			pstmt.setInt(1, mypageVo.getSq());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MypageVo();
				vo.setSq(rs.getInt("sq"));
				vo.setName(rs.getString("name"));
				vo.setPw(rs.getString("pw"));
				vo.setTel(rs.getString("tel"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public ArrayList<BookingVo> bHistory(BookingVo bookingVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookingVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from (owls_booking_tb A, owls_product_tb B) "
					+ "where A.product_sq = B.product_sq and A.member_sq = ? and A.booking_fl = 1 "
					+ "order by A.booking_date,A.booking_start asc");
			pstmt.setInt(1, bookingVo.getMember_sq());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookingVo vo = new BookingVo();
				vo = new BookingVo();
				vo.setBooking_sq(rs.getInt("booking_sq"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setProduct_detail(rs.getString("product_detail"));
				vo.setBooking_people(rs.getInt("booking_people"));
				vo.setProduct_price(rs.getInt("product_price"));
				vo.setProduct_imagePath(rs.getString("product_imagePath"));
				vo.setBooking_date(rs.getString("Booking_date"));
				vo.setBooking_start(rs.getString("Booking_start"));
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

	public BookingVo bCancel(BookingVo bookingVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookingVo vo = null;

		try {
			pstmt = con.prepareStatement("select * from (owls_booking_tb A, owls_product_tb B) "
					+ "where A.product_sq = B.product_sq and A.booking_sq = ? and A.booking_fl = 1 ");
			pstmt.setInt(1, bookingVo.getBooking_sq());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BookingVo();
				vo.setBooking_sq(rs.getInt("booking_sq"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBooking_people(rs.getInt("booking_people"));
				vo.setProduct_price(rs.getInt("product_price"));
				vo.setBooking_date(rs.getString("Booking_date"));
				vo.setBooking_start(rs.getString("Booking_start"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public int bCancelProc(int booking_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("Update owls_booking_tb set booking_fl = 0 where booking_sq = ?");
			pstmt.setInt(1, booking_sq);

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public BoardVo qDetail(String board_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement(
					"select a.board_sq, b.sq , b.id, a.board_content, a.board_subject, a.board_content, a.board_address, date_format(a.board_dttm, '%Y-%m-%d %H:%i') as board_dttm, c.answer_fl, c.answer_content from owls_board_tb a"
							+ " inner join owls_mber_tb b" + " on a.member_sq = b.sq"
							+ " inner join owls_board_answer_tb c" + " on a.board_sq = c.board_sq"
							+ " where b.del_fl = 0 and a.board_del_fl = 0 and a.board_sq = ?");
			pstmt.setInt(1, Integer.parseInt(board_sq));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setMember_sq(rs.getInt("sq"));
				vo.setMember_id(rs.getString("id"));
				vo.setBoard_subject(rs.getString("board_subject"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setBoard_address(rs.getString("board_address"));
				vo.setBoard_dttm(rs.getString("board_dttm"));
				vo.setAnswer_fl(rs.getBoolean("answer_fl"));
				vo.setAnswer_content(rs.getString("answer_content"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public ArrayList<BoardVo> getBoardList(Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(
					"select obt.board_sq, obt.board_subject, obt.board_content, obt.board_address, obt.member_sq, date_format(obt.board_dttm, '%Y-%m-%d %H:%i') as board_dttm,"
							+ " omt.id from owls_board_tb obt INNER JOIN owls_mber_tb omt on obt.member_sq=omt.sq where obt.board_del_fl = false order by obt.board_sq desc limit ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setMember_id(rs.getString("id"));
				vo.setBoard_subject(rs.getString("board_subject"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setBoard_address(rs.getString("board_address"));
				vo.setBoard_dttm(rs.getString("board_dttm"));
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

	public int getBoardCount() {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select" + " count(obt.board_sq)"
					+ " from owls_board_tb obt INNER JOIN owls_mber_tb omt" + " on obt.member_sq=omt.sq"
					+ " where obt.board_del_fl = false" + " order by obt.board_sq desc");

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int qDelete(String board_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			pstmt = con.prepareStatement(
					"update owls_board_tb set board_del_fl = 1 where board_sq = ? and board_del_fl = 0");
			pstmt.setInt(1, Integer.parseInt(board_sq));

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int QWriting(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert into owls_board_tb (member_sq, board_subject, board_content) values(?, ?, ?)");

			pstmt.setInt(1, boardVo.getMember_sq());
			pstmt.setString(2, boardVo.getBoard_subject());
			pstmt.setString(3, boardVo.getBoard_content());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public BoardVo findBoardSq(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement("select * from owls_board_tb order by board_sq desc limit 1;");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public int addAnswerTb(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into owls_board_answer_tb (board_sq) values(?)");

			pstmt.setInt(1, vo.getBoard_sq());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public BoardVo qModify(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			pstmt = con.prepareStatement(
					"select member_sq, board_sq, board_subject, board_content, board_address, board_dttm"
							+ " from owls_board_tb where board_sq = ? and board_del_fl = 0");
//			pstmt.setInt(1, boardVo.getMember_sq());
			pstmt.setInt(1, boardVo.getBoard_sq());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("member_sq"));
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setBoard_subject(rs.getString("board_subject"));
				vo.setBoard_content(rs.getString("board_content"));
				vo.setBoard_address(rs.getString("board_address"));
				vo.setBoard_dttm(rs.getString("board_dttm"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}

	public int qModifyProc(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"update owls_board_tb set board_subject = ?, board_content = ? where board_sq = ?");
			pstmt.setString(1, boardVo.getBoard_subject());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_sq());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<MypageVo> rMyReview(MypageVo mypageVo, Pagenation pagenation) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MypageVo> list = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(
					"select" + " A.review_sq, A.review_subject, A.review_content, date_format(A.review_dttm, '%Y-%m-%d %H:%i') as review_dttm,A.review_star, B.id"
							+ " from owls_review_tb A INNER JOIN owls_mber_tb B" + " on A.member_sq=B.sq"
							+ " where A.review_del_fl = false" + " and A.member_sq = ?"
							+ " order by A.review_sq desc limit ?,?");
			pstmt.setInt(1, mypageVo.getSq());
			pstmt.setInt(2, pagenation.getStartArticleNumber());
			pstmt.setInt(3, pagenation.getARTICLE_COUNT_PER_PAGE());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MypageVo vo = new MypageVo();
				vo = new MypageVo();
				vo.setId(rs.getString("id"));
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setReview_star(rs.getFloat("review_star"));
				vo.setReview_subject(rs.getString("review_subject"));
				vo.setReview_content(rs.getString("review_content"));
				vo.setReview_dttm(rs.getString("review_dttm"));

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

	public MypageVo rMyReviewDetail(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MypageVo vo = null;
		try {
			pstmt = con.prepareStatement(
					"select" + " review_sq, member_sq, review_star, review_subject, review_content, review_dttm"
							+ " from owls_review_tb" + " where review_sq = ?" + " and review_del_fl = 0");
			pstmt.setInt(1, mypageVo.getReview_sq());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MypageVo();
				vo.setReview_sq(rs.getInt("review_sq"));
				vo.setSq(rs.getInt("member_sq"));
				vo.setReview_star(rs.getDouble("review_star"));
				vo.setReview_subject(rs.getString("review_subject"));
				vo.setReview_content(rs.getString("review_content"));
				vo.setReview_dttm(rs.getString("review_dttm"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return vo;
	}

	public int rMyReviewModify(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update owls_review_tb"
					+ " set review_star = ?, review_subject = ?, review_content = ?"
					+ " where review_sq = ? and review_del_fl = 0;");
			pstmt.setDouble(1, mypageVo.getReview_star());
			pstmt.setString(2, mypageVo.getReview_subject());
			pstmt.setString(3, mypageVo.getReview_content());
			pstmt.setInt(4, mypageVo.getReview_sq());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int rMyReviewDelete(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update owls_review_tb" + " set review_del_fl = 1" + " where review_sq = ?"
					+ " and review_del_fl = 0");
			pstmt.setInt(1, mypageVo.getReview_sq());

			count = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int rWriting(MypageVo mypageVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(
					"insert" + " into owls_review_tb(member_sq, review_star, review_subject, review_content)"
							+ " values(?, ?, ?, ?)");
			pstmt.setInt(1, mypageVo.getSq());
			pstmt.setDouble(2, mypageVo.getReview_star());
			pstmt.setString(3, mypageVo.getReview_subject());
			pstmt.setString(4, mypageVo.getReview_content());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<MypageVo> reviewList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MypageVo> list = new ArrayList<>();

		try {
			pstmt = con
					.prepareStatement("select" + " A.member_sq, A.review_star, A.review_subject, A.review_content, B.id"
							+ " from (owls_review_tb A, owls_mber_tb B)" + " where A.member_sq = B.sq"
							+ " order by review_star, review_dttm desc limit 1,10");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				MypageVo vo = new MypageVo();
				vo = new MypageVo();
				vo.setId(rs.getString("id"));
				vo.setSq(rs.getInt("member_sq"));
				vo.setReview_star(rs.getFloat("review_star"));
				vo.setReview_subject(rs.getString("review_subject"));
				vo.setReview_content(rs.getString("review_content"));

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

	public int getReviewCount(int member_sq) {
		PreparedStatement pstmt = null; // 쿼리문 작성할 메소드
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select" + " count(A.review_sq)"
					+ " from owls_review_tb A INNER JOIN owls_mber_tb B" + " on A.member_sq=B.sq"
					+ " where A.review_del_fl = false" + " and A.member_sq = ?" + " order by A.review_sq desc");

			pstmt.setInt(1, member_sq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int qAnswer(BoardVo boardVo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into owls_board_answer_tb (board_sq, answer_content) values(?, ?)");

			pstmt.setInt(1, boardVo.getBoard_sq());
			pstmt.setString(2, boardVo.getAnswer_content());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
