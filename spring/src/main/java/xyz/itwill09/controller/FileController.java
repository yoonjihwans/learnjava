package xyz.itwill09.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.FileBoard;
import xyz.itwill09.service.FileBoardService;

//클라이언트로부터 전달받은 파일을 서버 디렉토리에 업로드 처리하는 방법
//1.commons-fileupload 라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml
//2.Spring Bean Configuration File(servlet-context.xml)에 파일 업로드 처리 기능을 제공하는 
//클래스를 Spring Bean으로 등록
//3.MultipartHttpServletRequest 객체를 사용해 [multipart/form-data] 형식으로 전달된 값 또는
//파일을 반환받아 사용

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
	//WebApplicationContext 객체(스프링 컨테이너)를 제공받아 필드에 저장되도록 의존성 주입
	private final WebApplicationContext context;
	//FileBoardService 인터페이스를 상속받은 자식클래스의 객체를 제공받아 필드에 저장되도록 의존성 주입 
	private final FileBoardService fileBoardService;
	
	@RequestMapping(value = "/upload1", method = RequestMethod.GET)
	public String uploadOne() {
		return "file/form_one";
	}
	
	/*
	//요청 처리 메소드의 매개변수를 MultipartHttpServletRequest 인터페이스로 작성하면 Front
	//Controller로부터 MultipartHttpServletRequest 객체를 제공받아 사용
	// => MultipartHttpServletRequest 객체 : [multipart/form-data] 형식으로 전달된 값 또는
	//파일을 처리하기 위한 객체
	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public String uploadOne(MultipartHttpServletRequest request) throws IOException {
		String uploaderName=request.getParameter("uploaderName");
		
		//MultipartHttpServletRequest.getFile(String name) : 전달파일의 정보가 저장된
		//MultipartFile 객체를 반환하는 메소드 
		MultipartFile uploaderFile=request.getFile("uploaderFile");
		
		//MultipartFile 객체에 저장된 전달파일에 대한 검증
		//MultipartFile.isEmpty() : MultipartFile 객체에 파일정보가 저장되어 있는 경우 [false]를
		//반환하고 파일정보가 저장되어 있지 않은 경우 [true]를 반환하는 메소드
		if(uploaderFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//MultipartFile.getContentType() : MultipartFile 객체에 저장된 전달파일의 형태(MimeType)를
		//반환하는 메소드
		System.out.println("파일 형태(MimeType) = "+uploaderFile.getContentType());
		//MultipartFile.getBytes() : MultipartFile 객체에 저장된 전달파일의 내용을 byte 배열로
		//반환하는 메소드
		System.out.println("파일 크기(Byte) = "+uploaderFile.getBytes().length);
		
		//전달파일이 저장될 서버 디렉토리(폴더)의 시스템 경로를 반환받아 저장
		String uploadDirectory=request.getServletContext().getRealPath("/resources/images/upload");
		System.out.println("uploadDirectory = "+uploadDirectory);
		
		//MultipartFile.getOriginalFilename() : MultipartFile 객체에 저장된 전달파일의 이름을
		//반환하는 메소드 - 원본 파일명
		String uploadFilename=uploaderFile.getOriginalFilename(); 

		//서버 디렉토리에 저장될 파일에 대한 정보가 저장된 File 객체 생성 - 업로드 처리될 파일 설정
		File file=new File(uploadDirectory, uploadFilename);
		
		//MultipartFile.transferTo(File file) : MultipartFile 객체에 저장된 전달파일의 내용을  
		//File 객체에 저장된 파일에 저장되도록 업로드 처리하는 메소드
		// => 서버 디렉토리에 업로드 처리되는 파일과 같은 이름의 파일이 있는 경우 기존 파일
		//대신 업로드 파일이 서버 디렉토리에 저장 - 덮어씌우기(OverWrite)
		uploaderFile.transferTo(file);
		
		request.setAttribute("uploaderName", uploaderName);
		request.setAttribute("uploadFilename", uploadFilename);
		
		return "file/upload_success_one";
	}
	*/
	
	//요청 처리 메소드에 매개변수를 작성하여 전달값 및 전달파일을 제공받아 사용
	// => 전달값과 전달파일의 이름과 같은 이름으로 매개변수 작성
	//문제점 : 전달 파일의 이름과 같은 이름의 파일이 서버 디렉토리에 있는 경우 기존 파일 대신
	//전달파일로 덮어씌워 저장(OverWrite)
	//해결법 : 전달파일의 이름으로 서버 디렉토리에 저장하기 않고 전달파일의 이름을 중복되지 
	//않는 이름으로 변경해 저장되도록 업로드 처리
	@RequestMapping(value = "/upload1", method = RequestMethod.POST)
	public String uploadOne(@RequestParam String uploaderName
			, @RequestParam MultipartFile uploaderFile, Model model) throws IOException {
		if(uploaderFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//WebApplicationContext.getServletContext() : WebApplicationContext 객체(스프링 컨테이너)를
		//사용해 ServletContext 객체를 반환하는 메소드
		String uploadDirectory=context.getServletContext().getRealPath("/resources/images/upload");
		//UUID.randomUUID() : 36Byte 크기의 식별자가 저장된 UUID 객체를 생성하여 반환하는 정적메소드
		//UUID.toString() : UUID 객체에 저장된 36Byte 크기의 식별자를 문자열로 반환하는 메소드
		String uploadFilename=UUID.randomUUID().toString()+"_"+uploaderFile.getOriginalFilename(); 
		
		File file=new File(uploadDirectory, uploadFilename);
		uploaderFile.transferTo(file);

		model.addAttribute("uploaderName", uploaderName);
		model.addAttribute("uploadFilename", uploadFilename);
		
		return "file/upload_success_one";
	}
	
	@RequestMapping(value = "/upload2", method = RequestMethod.GET)
	public String uploadTwo() {
		return "file/form_two";
	}
	
	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public String uploadTwo(@RequestParam String uploaderName
			, @RequestParam List<MultipartFile> uploaderFileList, Model model) throws IOException {
		String uploadDirectory=context.getServletContext().getRealPath("/resources/images/upload");

		//업로드 처리된 파일의 이름을 저장하기 위한 List 객체 생성
		List<String> filenameList=new ArrayList<String>();
		
		//List 객체에 저장된 요소값(MultipartFile 객체)을 차례대로 제공받아 변수에 저장해 반복 처리
		for(MultipartFile multipartFile : uploaderFileList) {
			if(multipartFile.isEmpty()) {
				return "file/upload_fail";
			}
			
			String uploadFilename=UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename(); 
			File file=new File(uploadDirectory, uploadFilename);
			multipartFile.transferTo(file);
			
			//List 객체의 요소값으로 업로드 처리된 파일을 추가하여 저장
			filenameList.add(uploadFilename);
		}
		
		model.addAttribute("uploaderName", uploaderName);
		model.addAttribute("filenameList", filenameList);
		
		return "file/upload_success_two";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String fileBoardWrite() {
		return "file/board_write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String fileBoardWrite(@ModelAttribute FileBoard fileBoard
			, @RequestParam MultipartFile upload) throws IOException {
		if(upload.isEmpty()) {
			return "file/board_write";
		}
		
		//전달파일을 저장하기 위한 서버 디렉토리의 시스템 경로를 반환받아 저장
		// => 다운로드 기능을 제공하는 프로그램에서만 파일에 접근 가능하도록 /WEB-INF 폴더에
		//업로드 폴더 생성
		String uploadDirectory=context.getServletContext().getRealPath("/WEB-INF/upload");
	
		//업로드 처리될 파일명을 생성하여 FileBoard 객체의 filename 필드값으로 변경
		fileBoard.setFilename(UUID.randomUUID().toString()+"_"+upload.getOriginalFilename());
		
		//전달파일을 서버 디렉토리에 저장되도록 업로드 처리
		// =>  FileBoard 객체의 filename 필드값을 반환받아 업로드 처리될 파일명으로 사용
		upload.transferTo(new File(uploadDirectory, fileBoard.getFilename()));
		
		//FileBoardService 객채로 addFileBoard() 메소드를 호출하여 FILE_BOARD 테이블에 행으로 삽입 처리
		fileBoardService.addFileBoard(fileBoard);
		
		//클라이언트에게 URL 주소를 전달하여 응답 처리 - Redirect 이동
		return "redirect:/file/list";
	}
	
	@RequestMapping("/list")
	public String fileBoardList(@RequestParam(defaultValue = "1") int pageNum
			, @RequestParam(defaultValue = "5") int pageSize, Model model) {
		Map<String, Object> map=fileBoardService.getFileBoardList(pageNum, pageSize);
		
		//model.addAttribute("map", map);
		
		model.addAttribute("pager", map.get("pager"));
		model.addAttribute("fileBoardList", map.get("fileBoardList"));
		
		return "file/board_list";
	}
	
	@RequestMapping("/delete")
	public String fileBoardDelete(@RequestParam int num) {
		//삭제될 게시글을 반환받아 저장 - 업로드 처리된 파일명을 제공받기 위해 사용
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		
		//업로드 처리된 파일이 저장된 서버 디렉토리의 시스템 경로를 반환받아 저장
		String uploadDirectory=context.getServletContext().getRealPath("/WEB-INF/upload");
		
		//서버 디렉토리에 저장된 게시글의 업로드 파일 삭제 처리
		new File(uploadDirectory, fileBoard.getFilename()).delete();

		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file/list";
	}
	
	//다운로드(Download) : 서버 디렉토리에 저장된 파일을 클라이언트에게 전달하여 저장하는 기능
	// => 요청 처리 메소드에 의해 반환되는 문자열(ViewName)로 다운로드 기능을 제공하는 객체의
	//메소드를 호출하여 서버 디렉토리에 저장된 파일을 클라이언트에게 전달하여 저장되도록 응답 처리
	// => BeanNameViewResolver 객체를 사용해 요청 처리 메소드의 반환값(ViewName)으로 응답 
	//처리 클래스의 객체로 메소드를 호출하여 메소드의 명령으로 응답 처리
	// => Spring Bean Configuration File(servlet-context.xml)에 BeanNameViewResolver 클래스를
	//Spring Bean으로 등록 - ViewResolver 중 가장 우선순위가 높도록 설정
	@RequestMapping("/download")
	public String fileBoardDownload(@RequestParam int num, Model model) {
		FileBoard fileBoard=fileBoardService.getFileBoard(num);

		//Model 객체를 사용해 응답 처리 클래스의 메소드에서 사용할 수 있는 Request Scope
		//속성값을 저장하여 제공
		model.addAttribute("uploadDirectory", context.getServletContext().getRealPath("/WEB-INF/upload"));
		model.addAttribute("uploadFilename", fileBoard.getFilename());
		
		//BeanNameViewResolver 객체에 의해 응답될 클래스의 Spring Bean 식별자(beanName)를 반환
		// => 응답 처리 클래스는 반드시 Spring Bean Configuration File(servlet-context.xml)에서
		//bean 엘리먼트를 사용해 Spring Bean으로 등록 - @Component 어노테이션 사용 가능
		return "fileDownload";
	}
}












