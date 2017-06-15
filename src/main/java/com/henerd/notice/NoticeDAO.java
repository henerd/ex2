package com.henerd.notice;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.henerd.utill.DBConnector;
import com.henerd.utill.RowMaker;

@Repository("notice")
//NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAO {
	
	@Inject
	private DataSource dataSource;

	public int noticeCount() throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		int result=0;
		ResultSet rs = null;
		String sql="select nvl(count(num), 0) from notice";
		st=con.prepareStatement(sql);
		rs=st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		
		DBConnector.disConnect(rs, st, con);
		
		return result;
		
	}
	
	
	
	public NoticeDTO noticeView(int num) throws Exception{
		NoticeDTO noticeDTO = null;
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql="select * from notice where num=?";
		st = con.prepareStatement(sql);
		st.setInt(1, num);
		rs=st.executeQuery();
		if(rs.next()){
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
		}
		DBConnector.disConnect(rs, st, con);
		

		return noticeDTO;
	}



	//list
	public List<NoticeDTO> noticeList(RowMaker rowMaker)throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<NoticeDTO> ar = new ArrayList<NoticeDTO>();
		
		String sql="select * from "
				+ "(select rownum r, n.* from "
				+ "(select * from notice order by num desc) n) "
				+ "where r between ? and ?";
		st = con.prepareStatement(sql);
		st.setInt(1, rowMaker.getStartRow());
		st.setInt(2, rowMaker.getLastRow());
		rs = st.executeQuery();
		
		while(rs.next()){
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(noticeDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}


	public int noticeWrite(NoticeDTO noticeDTO) throws Exception{
		int result = 0;
		Connection con=dataSource.getConnection();
		PreparedStatement st =null;
		
		String sql="insert into notice(num,title,writer,contents,hit,reg_date) "
				+ "values(n_seq.nextval,?,?,?,0,sysdate)";
		st=con.prepareStatement(sql);
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getWriter());
		st.setString(3, noticeDTO.getContents());
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}


	public int noticeUpdate(NoticeDTO noticeDTO)throws Exception{
		int result = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement st =null;
		String sql="update notice set title=?, contents=? where num=?";
		st=con.prepareStatement(sql);
		st.setString(1, noticeDTO.getTitle());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;

	}



	public int noticeDelete(int num)throws Exception{
		int result = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement st = null;
		String sql="delete from notice where num=?";
		st=con.prepareStatement(sql);
		st.setInt(1, num);
		result = st.executeUpdate();
		DBConnector.disConnect(st, con);

		return result;

	}


}
