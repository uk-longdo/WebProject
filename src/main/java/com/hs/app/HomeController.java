package com.hs.app;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hs.app.dao.Dress_inven_Dao;
import com.hs.app.dao.Dress_list_Dao;
import com.hs.app.dao.Dress_sort_Dao;
import com.hs.app.dao.File_upload_Dao;
import com.hs.app.dao.Good_list_Dao;
import com.hs.app.dao.Notice_Dao;
import com.hs.app.dao.Paging_Dao;
import com.hs.app.dao.Qa_Board_Dao;
import com.hs.app.dao.Reply_Dao;
import com.hs.app.dao.Review_Dao;
import com.hs.app.dao.User_Dao;
import com.hs.app.dto.Dress_list_Dto;
import com.hs.app.dto.File_upload_Dto;
import com.hs.app.dto.Review_Dto;
import com.mysql.cj.xdevapi.JsonArray;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	User_Dao udao;
	
	@Autowired
	Dress_list_Dao dldao;
	
	@Autowired
	Review_Dao rdao;
	
	@Autowired
	Dress_inven_Dao didao;
	
	@Autowired
	Reply_Dao rpdao;
	
	@Autowired
	Dress_sort_Dao dsdao;
	
	@Autowired
	Notice_Dao ndao;
	
	@Autowired
	File_upload_Dao iudao;
	
	@Autowired
	Good_list_Dao gldao;
	
	@Autowired
	Paging_Dao pdao;
	
	@Autowired
	Qa_Board_Dao qadao;
	
	@Autowired
	private ServletContext context;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	
	/*
	 * 
	 * �ο�� �α��� �κ� 
	 * 
	 * 
	 * */
	
	@RequestMapping("/login")
	public String login() {
		System.out.println("�α���");
		return "login";
	}
	
	@RequestMapping("/logindo")
	   public String logindo(String user_id,String user_password, HttpSession session, Model model){
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("user_id", user_id);
	      map.put("user_password", user_password);
	      int grede = udao.admintest(user_id);
	      
	      
	      int res = udao.logindo(map);
	      if(res==1) {
	    	  //�α��� ���Ǻκ�
	    	  if(grede >= 6) {
	    		  session.setAttribute("loginid", user_id);
	    		  session.setAttribute("loginidgrade", grede);
	    		  
	    		  return "main";
	    	  }else {
	    		  session.setAttribute("loginid", user_id);
	    		  return "main";
	    	  }
	      }else {
	    	 return "login";
	      }
		
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, String user_id ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		session.removeAttribute("loginidgrade");
		session.removeAttribute("loginid");
		return "main";
		
	}
	
	//�α��� �������� �ܿ� ���� ^^~
	@SuppressWarnings("unchecked")
	@RequestMapping("/loginchk")
	public void loginchk(HttpServletResponse respose, String user_id, 
			String user_password) throws IOException {
		 
		Map<String, Object> map = new HashMap<String, Object>();
	      map.put("user_id", user_id);
	      map.put("user_password", user_password);
	      
		int test = udao.logindo(map);
		JSONObject json = new JSONObject();
		if(test == 0) {
			json.put("result", "���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�");
			respose.getWriter().print(json.toString());
		}else {
			json.put("result", "����");
			respose.getWriter().print(json.toString());
		}
	}
	
	//ajax ȸ������ �ߺ� üũ
    @SuppressWarnings("unchecked")
   @RequestMapping( "/log_upchk")
    public void  log_upchk(String user_id, HttpServletResponse respose) throws IOException {
       String str="�̹� �����ϴ� ���̵� �Դϴ�.";
       System.out.println(user_id);
      
       int log_upchk = udao.log_upchk(user_id); //�α�üũ dao ����
       
       System.out.println("ddddddd"+log_upchk);
       
       JSONObject json = new JSONObject();  //���̽� ��ü ����
       
       if(log_upchk==0) {
          str="��� �����մϴ�.";
          
       }
       System.out.println(str);
       json.put("result", str);
       respose.getWriter().print(json.toString());
       
    }

	
	@RequestMapping("/log_up")
	public String regmem() {
	
		return "log_up";
	}
	
	
	@RequestMapping("/insertlogin")
	public String insertlogin(String user_id, String user_password, String user_name, String user_tel, String user_addres ) {
		System.out.println("ȸ������");
		//insert into user_list values (null, #{user_id}, #{user_password}, #{user_addres}, #{user_tel},default, #{user_name}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("user_password", user_password);
		map.put("user_name", user_name);
		map.put("user_tel", user_tel);
		map.put("user_addres", user_addres);
		
		udao.insertlogin(map);
		
		return "redirect:/";
	}
		
	
	/*
	 * 
	 * ��´� ��� �κ� 
	 * 
	 * 
	 * */
	
	@RequestMapping("/listreg")
	public String listreg(Model model) {
		model.addAttribute("result", dsdao.sortlist());
		return "list_reg";
		
	}
	
	
	
	@RequestMapping("/insertlist")
	public String insertlist(int sort_num, String dress_name, int dress_price, String dress_color, String dress_content, 
								MultipartHttpServletRequest mrequest) {
		System.out.println("����� ����");
		//insert into dress_list values(null, #{sort_num}, #{dress_name},#{dress_price},#{dress_color},#{dress_content},#{dress_file_name})
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println(sort_num);
		//�Ѿ�� ���Ͽ��� �̸� �̾Ƴ��� - filename
		MultipartFile file = mrequest.getFile("dress_file_name");
		String dress_file_name = file.getOriginalFilename();
		
		//�Ѿ�� name �������� - name
		String name=mrequest.getParameter("name");
		
		//�Ѿ�� mrequest�� ������ ���Ͼ��ε� ó��
		String root_path=context.getRealPath("/");
		String attach_path="resources/thumbnail/";
		
		//���Ͼ��ε� �޼���
		//file.transferTo(new File("��ġ�� ������ ���ϸ�"));
		try {
			file.transferTo(new File(root_path+attach_path+dress_file_name));
			System.out.println(dress_file_name+"���� ���ε� �Ϸ�!");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("sort_num", sort_num);
		map.put("dress_name", dress_name);
		map.put("dress_price", dress_price);
		map.put("dress_color", dress_color);
		map.put("dress_content", dress_content);
		map.put("dress_file_name", dress_file_name);
		
		
		dldao.insertlist(map);
		
		
		return "redirect:/dresslist";
	}
	
	
	
	//�̹��� ���� ���ε�!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@RequestMapping("/insertfile")
	public String insertfile(int dress_num,  int sort_num, String file_path,
								MultipartHttpServletRequest mrequest) {
		
		System.out.println("����� ����");
		//insert into dress_list values(null, #{dress_num}, #{sort_num},#{file_name},#{file_path})
		Map<String, Object> map = new HashMap<String, Object>();
		
		//�Ѿ�� ���Ͽ��� �̸� �̾Ƴ��� - filename
		MultipartFile file = mrequest.getFile("file_name");
		String file_name = file.getOriginalFilename();

		
		//�Ѿ�� name �������� - name
		String name=mrequest.getParameter("name");
//		System.out.println(file_name+":"+name);
		
		
		
		//�Ѿ�� mrequest�� ������ ���Ͼ��ε� ó��		
		String root_path=context.getRealPath("/");
		String attach_path="resources/"+file_path+"/";
		
		
		//���Ͼ��ε� �޼���
		//file.transferTo(new File("��ġ�� ������ ���ϸ�"));
		try {
			file.transferTo(new File(root_path+attach_path+file_name));
			System.out.println(file_name+"���� ���ε� �Ϸ�!");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("dress_num", dress_num);
		map.put("sort_num", sort_num);
		map.put("file_name", file_name);
		map.put("file_path", file_path);
		
		iudao.insertfile(map);
		
		return "redirect:/";
	}
	
	
	
	@RequestMapping("/insertinven")
	public String insertinven(int dress_num, String[] dress_size, int[] dress_stock) {
		System.out.println("����� ����");
		//insert into dress_list values(null, #{sort_num}, #{dress_name},#{dress_price},#{dress_color},#{dress_content},#{dress_file_name})
		for(int i=0;i<dress_size.length;i++) {
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("dress_num", dress_num);
			map.put("dress_size", dress_size[i]);
			map.put("dress_stock", dress_stock[i]);
			didao.insertinven(map);			
		}
		
		return "redirect:/invenlist?dress_num="+dress_num;
	}
	
	@RequestMapping("/dresslist")
	public String dresslist(@RequestParam(value="pageNum", required = false, defaultValue = "1")String strNum, Model model) {
		
		int pageSize=9;// ���������� ��µ� ����
   	  	int totalCount=pdao.getTotalCount();// ��ü ���� ex) 25
   	  	int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
   	  
   	  	int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
   	  	Map<String, Object> map = new HashMap<String, Object>();
   	  	map.put("start", numtmp);
   	  	map.put("cnt", pageSize);
			
   	  	List<Dress_list_Dto> list = pdao.getAll(map);	
			
   	  	model.addAttribute("pageCount", pageCount);
		model.addAttribute("dresslist", list);
		return "dress_list";
		
	}
	
	//��� �������� ���̴� �κ�
	@RequestMapping("/invenlist")
	public String inveninsert(int dress_num, Model model, HttpSession session) {
		model.addAttribute("dressinven",didao.dresslist(dress_num));
		model.addAttribute("dressone", dldao.dressone(dress_num));
		model.addAttribute("fileimg", dldao.detailpage(dress_num));
		model.addAttribute("sort", dsdao.sortlist());
		return "inven_insert";
		
	}
	
	
	
	/*
	 * 
	 * 
	 * ������ �Խ��� �κ� 
	 * 
	 * 
	 * */
	
	
	
	@RequestMapping("/review_list")
	   public String review(@RequestParam(value="pageNum", 
				  required = false, 
				  defaultValue = "1")String strNum,	Model model, HttpSession session,String user_id) {
	      
			System.out.println(strNum);
	  	
	  		//����¡ó��
			int pageSize=15;// ���������� ��µ� ����
			int totalCount=rdao.reviewTotalCount();// ��ü ���� ex) 25
			int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
		
			int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
			
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("start", numtmp);
			 map.put("cnt", pageSize);
			
			List<Review_Dto> list =  rdao.review_list(map);	
			
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("result",list);
		
			
	      return "review_list";
	   }
	
	   @RequestMapping("/review")
	   public String reviews(HttpSession session, String user_id) {
		   session.setAttribute("loginid", user_id);
	      return "review_insert";
	   }
	   
	   @RequestMapping("do_review")
	   public String doreview(String review_title, String user_id, String review_content) {
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("user_id", user_id);
	      map.put("review_title", review_title);
	      map.put("review_content", review_content);
	      
	      rdao.insert(map);
	      return "redirect:/review_list";
	   }
	   
	   @RequestMapping("/review_read")
	   public String read(int idx, String user_id, Model model, HttpSession session) {
	      //rdao.updateHit(idx);
	      model.addAttribute("result", rdao.read(idx));
	      model.addAttribute("result2", rpdao.read(idx)); //���
	      rdao.updateHit(idx); //��ȸ��
	      
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("review_idx", idx);
	      map.put("user_id", user_id);
	      
	      int cnt = gldao.goodcnt(map);
	      System.out.println("���ƿ� ī��Ʈ~~" + cnt);
	      
	      model.addAttribute("goodcnt", cnt);
	      
	      int commtcnt = rpdao.commentcnt(idx);
	      session.setAttribute("commtcnt", commtcnt);
	      
	      return "review_read";
	   }
	   
	   @RequestMapping("/review_update")
	   public String review_update(int idx, Model model) {
	      //rdao.updateHit(idx);
	      model.addAttribute("result", rdao.read(idx));
	      
	      return "review_update";
	   }
	   
	   @RequestMapping("do_update")
	   public String updates(int review_idx, String review_title, String review_content) {
		   
	         Map<String, Object> map = new HashMap<String, Object>();
	         
	         map.put("review_title", review_title);
	         map.put("review_content", review_content);
	         map.put("review_idx", review_idx);
	         
	         rdao.review_update(map);
	         
	         return "redirect:/review_list";
	         
	      }
	   
	   @RequestMapping("/delete")
	   public String deletdo(int review_idx) {
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("review_idx", review_idx);
	      rdao.delete(map);
	      
	      
	      
	      return "redirect:/review_list";

	   }
	   
	   @RequestMapping("replyProc")
	   public String replyProc(int review_idx, String user_id, String comment_content) {
	      Map<String, Object> map = new HashMap<String, Object>();

	      map.put("review_idx", review_idx);
	      map.put("user_id", user_id);
	      map.put("comment_content", comment_content);

	      rpdao.insert(map);
	      
	      
	      return "redirect:/review_read?idx="+review_idx;

	   }
	   
	   @RequestMapping("/updatereply")
	      public String updatereply(int review_idx, Model model, int comment_idx, String comment_content) {

	         Map<String, Object> map = new HashMap<String, Object>();

	         map.put("review_idx", review_idx);
	         map.put("comment_idx", comment_idx);
	         map.put("comment_content", comment_content);

	         model.addAttribute("result", rpdao.reply_update(map));

	         return "redirect:/review_read?idx="+review_idx;

	      }
	      
	      
	      @RequestMapping("/deletereply")
	      public String deletereply(int review_idx, int comment_idx) {
	         Map<String, Object> map = new HashMap<String, Object>();
	          map.put("comment_idx", comment_idx);
	          rpdao.deletereply(map);
	         
	         return "redirect:/review_read?idx="+review_idx;
	      }
	      
	   
	   //����
	   
	   @RequestMapping("/notice_list")
	   public String notice_list(@RequestParam(value="pageNum", 
				  required = false, 
				  defaultValue = "1")String strNum,	Model model, HttpSession session,String user_id) {
	      
			System.out.println(strNum);
	  		
	  		//����¡ ó��
			int pageSize=15;// ���������� ��µ� ����
			int totalCount=ndao.noticeTotalCount();// ��ü ���� ex) 25
			int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
			
			int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
			
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("start", numtmp);
			 map.put("cnt", pageSize);
		
			
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("result", ndao.notice_list(map));
	      return "notice_list";
	      
	   }
	   
	   @RequestMapping("/notice_insert")
	   public String notice_insert() {
	      
	      return "notice_insert";
	      
	   }
	   
	      @RequestMapping("do_notice")
	      public String donotice(String user_id, String notice_title, String notice_content) {
	         Map<String, Object> map = new HashMap<String, Object>();
	         map.put("user_id", user_id);
	         map.put("notice_title", notice_title);
	         map.put("notice_content", notice_content);
	         
	         ndao.insert(map);
	         return "redirect:/notice_list";
	      }
	      
	      @RequestMapping("/notice_read")
	      public String notice_read(int idx, Model model) {
	   
	         model.addAttribute("result", ndao.notice_read(idx));
	        
	         ndao.notice_hit(idx); //��ȸ��
	         
	         
	         return "notice_read";
	      }
	      
	      @RequestMapping("/notice_update")
	      public String notice_update(int idx, Model model) {
	         model.addAttribute("result", ndao.notice_read(idx));
	         
	         return "notice_update";
	      }
	      
	      @RequestMapping("doupdate_notice")
	      public String notice_doupdate(int notice_idx, String notice_title, String notice_content) {
	         
	            Map<String, Object> map = new HashMap<String, Object>();
	            
	            map.put("notice_title", notice_title);
	            map.put("notice_content", notice_content);
	            map.put("notice_idx", notice_idx);
	            
	            ndao.notice_update(map);
	            
	            return "redirect:/notice_list";
	            
	    }
	      
	      @RequestMapping("/notice_delete")
	      public String notice_deletdo(int notice_idx) {
	         Map<String, Object> map = new HashMap<String, Object>();
	         map.put("notice_idx", notice_idx);
	         ndao.delete(map);
	         
	         return "redirect:/notice_list";

	      }
	
	
	
	
	
	/*
	 * 
	 * ��� �ޱ� �� 
	 * 
	 * 
	 * */
	
	      @RequestMapping("/sideAll")
	      public String sideAll(@RequestParam(value="pageNum", required = false, defaultValue = "1")String strNum,	Model model) {
	    	  System.out.println(strNum);
	    	  
	    	  //����¡ ó��
	    	  int pageSize=9;// ���������� ��µ� ����
	    	  int totalCount=pdao.getTotalCount();// ��ü ���� ex) 25
	    	  int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
	    	  
	    	  int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
	    	  Map<String, Object> map = new HashMap<String, Object>();
	    	  map.put("start", numtmp);
	    	  map.put("cnt", pageSize);
				
	    	  List<Dress_list_Dto> list = pdao.getAll(map);	
				
	    	  model.addAttribute("pageCount", pageCount);
	    	  model.addAttribute("result",list);
			
	    	  return "side_all";	
				
			}
			
			@RequestMapping("/sideOuter")
			public String sideOuter(@RequestParam(value="pageNum", 
					  required = false, 
					  defaultValue = "1")String strNum,	Model model) {
				
					System.out.println(strNum);
		  		
		  		//����¡ ó��
				int pageSize=9;// ���������� ��µ� ����
				int totalCount=pdao.outergetTotalCount();// ��ü ���� ex) 25
				int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
				
				int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
				
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("start", numtmp);
				 map.put("cnt", pageSize);
				
				List<Dress_list_Dto> list = pdao.getOuter(map);	
				
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("result",list);
				
				
				return "side_Outer";
				
			}
			
			@RequestMapping("/sidePants")
			public String sidePants(@RequestParam(value="pageNum", 
					  required = false, 
					  defaultValue = "1")String strNum,	Model model) {
				
				System.out.println(strNum);
		  		
		  		//����¡ ó��
				int pageSize=9;// ���������� ��µ� ����
				int totalCount=pdao.pantsgetTotalCount();// ��ü ���� ex) 25
				int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
				
				int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
				
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("start", numtmp);
				 map.put("cnt", pageSize);
				
				List<Dress_list_Dto> list = pdao.getPants(map);	
				
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("result",list);
				
				return "side_Pants";
			}
			
			@RequestMapping("/sideTop")
			public String sideTop(@RequestParam(value="pageNum", 
					  required = false, 
					  defaultValue = "1")String strNum,	Model model) {
				
				System.out.println(strNum);
		  		
		  		//����¡ ó��
				int pageSize=9;// ���������� ��µ� ����
				int totalCount=pdao.topgetTotalCount();// ��ü ���� ex) 25
				int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
				
				int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
			
				
				 Map<String, Object> map = new HashMap<String, Object>();
				 map.put("start", numtmp);
				 map.put("cnt", pageSize);
				
				List<Dress_list_Dto> list = pdao.getTop(map);	
				
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("result",list);
				
				return "side_Top";
				
			}
			
			//��������
			
			@RequestMapping("/detailpage")
			public String detailpage(String dress_num,Model model,Model model2,HttpSession session) {
				System.out.println("���� �ѹ�: "+dress_num);
				
				List<File_upload_Dto> result = dldao.detailpage(Integer.parseInt(dress_num));
				List<Dress_list_Dto> result2 = dldao.dressnumlist(Integer.parseInt(dress_num));
				
				String content = dldao.dresscontent(Integer.parseInt(dress_num));
				String contentup = content.replaceAll("\r\n","<br>");
				
				session.setAttribute("contentup", contentup);
				
				model.addAttribute("result", result );
				model2.addAttribute("result2", result2 );
				model.addAttribute("inven", didao.dresslist(Integer.parseInt(dress_num)));
				
				
				return "detail_page";
				
			}
			
		//QNA�Խ��� �κ�
			@RequestMapping("/Qaboard_list")
			public String Qaboard_list(@RequestParam(value="pageNum", required = false, 
					  defaultValue = "1")String strNum, HttpSession session, Model model, String user_id) {
				System.out.println("�۸���Ʈ ��������");
				
				int pageSize=15;// ���������� ��µ� ����
				int totalCount=qadao.qaTotalCount();// ��ü ���� ex) 25
				int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
			
				int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
				
				Map<String, Object> map = new HashMap<String, Object>();
				 map.put("start", numtmp);
				 map.put("cnt", pageSize);
				
				model.addAttribute("pageCount", pageCount);
				model.addAttribute("result", qadao.qa_list(map));
				return "Qaboard_list";
			}
			
			@RequestMapping("/board_read")
			public String board_read(int idx, Model model) {
				System.out.println("���б� ������ ����");
				
				 model.addAttribute("result2", qadao.qa_read(idx));
				return "board_read";
			}
			
			@RequestMapping("/Qaboard_read")
			public String Qaboard_read(int idx, Model model) {
				System.out.println("���б� ��������");
				
				 model.addAttribute("result2", qadao.qa_read(idx));
				return "Qaboard_read";
			}
			
			
			@RequestMapping("/Qaboard_writer")
			public String Qaboard_writer() {
				System.out.println("�۾�����������");
				return "Qaboard_writer";
			}
			///////////���� �μ�Ʈ //////////
			@RequestMapping("/board_insert")
			public String board_insert(String user_id, String qa_title, String qa_credate,String qa_content, Model model,String qa_sel) {
				int qa_ref=1;
				int qa_re_step=1;
				int qa_re_level=1;
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", user_id);
				map.put("qa_title", qa_title);
				map.put("qa_content", qa_content);
				map.put("qa_ref", qa_ref);
				map.put("qa_re_step", qa_re_step);
				map.put("qa_re_level", qa_re_level);
				map.put("qa_sel", qa_sel);
				
				System.out.println("qa_sel");

				qadao.board_insert(map);
				
				return "redirect:/Qaboard_list";
			}
			
			
			@RequestMapping("/Qaboard_Rewriter")
			public String Qaboard_Rewriter() {
				System.out.println("��۾�����������");
				return "Qaboard_Rewriter";
			}
			
			/////////����μ�Ʈ////////�����ڸ� �����ϰ� ��ġ��
			@RequestMapping("/board_reinsert")
			public String board_reinsert(String user_id, String qa_title, String qa_credate,String qa_content) {
				int qa_ref=1;
				int qa_re_step=1;
				int qa_re_level=1;
				System.out.println("��۾��� ����");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_id", user_id);
				map.put("qa_title", qa_title);
				map.put("qa_content", qa_content);
				map.put("qa_ref", qa_ref);
				map.put("qa_re_step", qa_re_step+1);
				map.put("qa_re_level", qa_re_level+1);
				//�۾�
				qadao.qa_rewriter_insert(map);
				
				//������Ʈ
				qadao.qa_rewriter(map);
				
				
				return "redirect:/Qaboard_list";
			}
			
			///����///////////////
			@RequestMapping("/Qaboard_update")
			public String Qaboard_update(int idx, Model model) {
				System.out.println("���� ������������");
				model.addAttribute("res", qadao.qa_read(idx));
				return "Qaboard_update";
			}
			
			
			
			@RequestMapping("/qa_upadte")
			public String qa_upadte(int qa_idx, String qa_title, String qa_content) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("qa_idx", qa_idx);
				map.put("qa_title", qa_title);
				map.put("qa_content", qa_content);

				 
				
				 qadao.qa_update(map);
				return "redirect:/Qaboard_list";
			}
			
			//////////���� �ϱ�////////
			@RequestMapping("/Qaboard_delete")
			public String Qaboard_delete(int qa_idx) {
				System.out.println("������");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qa_idx", qa_idx);
				qadao.qa_delete(map);
				
				return "redirect:/Qaboard_list";
			}	
			
			
		
		//���ƿ� ��� �κ� ���� ����� �̾��� 
		
		
		@RequestMapping("/goods")
		public String goods(int review_idx, String user_id, RedirectAttributes redirectAttributes) {
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("review_idx", review_idx);
			map.put("user_id", user_id);
			
			int cnt = gldao.goodcnt(map);
			
			if(cnt >= 1) {
				System.out.println("�̹����ƿ��ߤ���!!!");
				gldao.gooddown(map);
				gldao.goodup(review_idx);
			}else {
				System.out.println("���ƿ��ߤ���!!!");
				gldao.goods(map);
				gldao.goodup(review_idx);
			}
			
			redirectAttributes.addAttribute("cnt", cnt);
			return "forward:/review_read?idx="+review_idx;
			
		}
		
		@RequestMapping("/userlist")
		public String userlist(@RequestParam(value="pageNum", required = false, 
				  defaultValue = "1")String strNum, Model model) {
			
			int pageSize=15;// ���������� ��µ� ����
			int totalCount= udao.userTotalCount();// ��ü ���� ex) 25
			int pageCount = (int)Math.ceil((double)totalCount/(double)pageSize);//3
			
			int numtmp = (Integer.parseInt(strNum)-1)*pageSize;
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", numtmp);
			map.put("cnt", pageSize);
			
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("result", udao.user_list(map));
			
			
			
			return "user_management";
			
		}
		
		//üũ�ڽ� �����ؼ� ȸ�� ���� �����ϱ� 
		@RequestMapping("/userdelete")
		public String userdelete(Model model, String user_idxs){
			System.out.println(user_idxs);
			if(user_idxs.contains(",")) {
				if (user_idxs.contains(",")) {
					String[] user_idx;
					user_idx = user_idxs.split(",");
					for(int i=0; i<user_idx.length; i++) {
						System.out.println("�ݺ�"+user_idx[i]);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("user_idx", Integer.parseInt(user_idx[i]));
						udao.userdelete(map);		
					}
				 }
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user_idx", Integer.parseInt(user_idxs));
				udao.userdelete(map);
			}
			
			        
			        
			    return "redirect:/userlist";
			}
		
		//����� �κ��丮 ���� idx�� ��� ��ü������ ��ȸ
		@RequestMapping("/deleteinven")
	      public String deleteinven(int dress_num, String dress_size, int dress_stock) {
			
			Map<String, Object> map = new HashMap<String, Object>();
	          map.put("dress_num", dress_num);
	          map.put("dress_size", dress_size);
	          map.put("dress_stock", dress_stock);
	          
	          didao.delecteinven(map);
	         
	         return "redirect:/invenlist?dress_num="+dress_num;
	      }
		
		
		
		
		
	
}
