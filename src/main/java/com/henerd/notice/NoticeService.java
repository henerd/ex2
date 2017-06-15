package com.henerd.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.henerd.utill.PageMaker;

@Service
//noticeService noticeService = new noticeService();
public class NoticeService {
	
	
	@Inject
	private NoticeDAO noticeDAO;
	
	//View
	public NoticeDTO noticeView(int num)throws Exception{
		return noticeDAO.noticeView(num);
	}

	public List<NoticeDTO> noticeList(int curPage)throws Exception{
		int rs = noticeDAO.noticeCount();
		PageMaker pm = new PageMaker(curPage);
		
		return noticeDAO.noticeList(pm.getRowMaker());
	}
	
	
	
	public int noticeWrite(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.noticeWrite(noticeDTO);
		
	}
	
	public int noticeUpdate(NoticeDTO noticeDTO)throws Exception{
		return noticeDAO.noticeUpdate(noticeDTO);
	}
	
	
	
	public int noticeDelete(int num)throws Exception{
		return noticeDAO.noticeDelete(num);
	}
}
