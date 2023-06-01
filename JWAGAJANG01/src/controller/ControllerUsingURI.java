package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import common.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {
	//직렬화하는 과정에서 값을 체크하는 용도로 serialVersionUID를 사용함.
	//직렬화를 할때 UID를 같이 저장해서 역직렬화할시에 UID를 체크해서 다르면 예외가 발생한다.
	private static final long serialVersionUID = 1L;
	// command와 Handler 인스턴스 매핑 정보 저장
	// 브라우저에서 command가 요청이 되면 그것을 처리하는 핸들러 인스턴스를 생성을 해서 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	// init메소드는 HttpServlet의 부모추상클래스인 GenericServlet에 존재하는 메소드이다. 클라이언트로부터 최초요청시 WAS에서 SERVLET으로 객체생성을 요청하며 딱 한번만 실행된다.
	// init메소드가 호출 될 때 ServletConfig를 자동으로 생성하여 넘겨줌.
	// web.xml에서 필요에 따라 입력한 파라미터 값들을 가져옴.
	// 그래서 web.xml에서 변경사항이 있을 시 서버를 재시작해서 서블릿객체를 새로 생성해야 함.
	@Override
	public void init() throws ServletException {
		// 매개변수로 web.xml에서 지정했던 param-name을 넘겨주면 그에 해당하는 값을 가져온다.
		String configFile = getInitParameter("configFile");
		//Properties 객체는 Key와 Value를 쌍으로 저장하는데, 환경설정 정보가 저장된 .properties파일의 내용을 읽어들일 때 사용된다.
		Properties prop = new Properties();
		// 실제 WAS가 구동될때의 저장된 물리적 경로를 구한다.
		// 실제 서버가 실행되는 물리적 경로는 workdirectory의 .metadata폴더 안에 위치한다.
		String configFilePath = getServletContext().getRealPath(configFile);
		
		//매개변수가 참조하는 값을 바이트스트림으로 읽기 위해 FileInputStream객체를 사용한다.
		try (FileInputStream fis = new FileInputStream(configFilePath)){
			//properties객체를 이용하여 바이트스트림으로 읽은 properites의 물리적 경로를 불러온 것.
			prop.load(fis);	
			//properties에 저장한 key값을 getProperty로 출력하면 값이 나온다.
			/*
			 * System.out.println(prop.getProperty("/index.do"));
			 * System.out.println(prop.getProperty("/noticeList.do"));
			 */
		} catch(IOException e) {
			throw new ServletException(e);
		}
		//keyset()은 map의 전체 값을 출력.
		Iterator<Object> keyIter = prop.keySet().iterator();
		
		// KeyIter에 담겨져 있는 key와 value 값 전체 출력.
		/*
		 * while (keyIter.hasNext()) { Object key = keyIter.next(); Object value =
		 * prop.get(key); System.out.println("[key]:" + key + ", [value]:" + value); }
		 */
		 
		
		while(keyIter.hasNext()) {
			//
			String command = (String)keyIter.next();
			String handlerClassName = prop.getProperty(command);
			// command대한 핸들러 객체 인스턴스 만들어 준다.
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler)handlerClass.newInstance(); // new ProductListHandler()
				//properties에 저장된 키에맞는 값의 객체의 경로를 찾아 인스턴스화 시켜서 객체정보를 값으로 HashMap에 저장.
				commandHandlerMap.put(command, handlerInstance);
			} catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getRequestURI();

		
		// uri에서 프로젝트이름을 포함한 컨텍스트패스
		/*
		 * if(command.indexOf(req.getContextPath()) == 0) { command =
		 * command.substring(req.getContextPath().length()); }
		 */
		
		 
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {	// 매핑 테이블에 없을 경우 (정의되지 않은 command일 경우)
			handler = new NullHandler();
		}
		//요청된URI를 HashMap에 저장되있는 Key값으로 활용하여 Value에 저장돼있는 핸들러객체의 process함수를 실행한다. 
		String viewPage = null;
		try {
			viewPage = handler.process(req, resp);
		} catch(Throwable e) {
			throw new ServletException(e);
		}
		if(viewPage != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}
}