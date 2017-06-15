package com.henerd.ex2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.henerd.notice.NoticeDTO;
import com.henerd.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject //noticeService 타입을 찾아서 inject해라
	private NoticeService noticeService;
	
	@RequestMapping(value="notice/noticeList", method=RequestMethod.GET)
	public void list(Model model, @RequestParam(defaultValue="1") Integer curPage)throws Exception{
		List<NoticeDTO> ar = noticeService.noticeList(curPage);
		model.addAttribute("list",ar);
	}
	
	
	@RequestMapping(value="notice/noticeView", method=RequestMethod.GET)
	public void view(Integer num,Model model)throws Exception{ //int num 해도 되지만 Integer num 하면 혹시모를 오류를 잡아줌 ..?
	NoticeDTO noticeDTO = noticeService.noticeView(num);
	model.addAttribute("dto", noticeDTO);		
	}
	
	
	@RequestMapping(value="notice/noticeWrite", method=RequestMethod.GET)
	public void write(){
		
	}
	
	@RequestMapping(value="notice/noticeWrite", method=RequestMethod.POST)
	public String write(NoticeDTO noticeDTO,RedirectAttributes rd)throws Exception{
		int result = noticeService.noticeWrite(noticeDTO);
		String message="FAIL";
		if(result>0){
			message="Done!";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
	}
	
	
	@RequestMapping(value="notice/noticeUpdate", method=RequestMethod.GET)
	public void update(Integer num,Model model)throws Exception{
		NoticeDTO noticeDTO = noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	
	@RequestMapping(value="notice/noticeUpdate", method=RequestMethod.POST)
	public String update(NoticeDTO noticeDTO,RedirectAttributes rd)throws Exception{
		int result = noticeService.noticeUpdate(noticeDTO);
		String message = "Update Fail";
		if(result>0){
			message = "Update Done!";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
		
	}
	
	@RequestMapping(value="notice/noticeDelete", method=RequestMethod.GET)
	public String delete(Integer num,RedirectAttributes rd)throws Exception{
		int result = noticeService.noticeDelete(num);
		String message="Delete Fail";
		if(result>0){
			message="Delete Success";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
		
	}
	
	
}
