package board;

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

public class NoticeHandlerURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// cmd와 Handler 인스턴스 매핑 정보 저장
	// 브라우저에서 cmd가 요청이 되면 그것을 처리하는 핸들러 인스턴스를 생성을 해서 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	// 서블릿이 맨처음에 구동되는 불리는 메소드
	@Override
	public void init() throws ServletException {
		// web.xml에서 설정된 commandHandler.properties 파일을 이름을 얻어온다.
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		// commandHandler.properties 파일의 절대경로를 가져온다.
		String configFilePath = getServletContext().getRealPath(configFile);
		try (FileInputStream fis = new FileInputStream(configFilePath)){
			prop.load(fis);
		} catch(IOException e) {
			throw new ServletException(e);
		}
		
		Iterator<Object> keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String)keyIter.next();
			String handlerClassName = prop.getProperty(command);
			// command대한 핸들러 객체 인스턴스 만들어 준다.
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler)handlerClass.newInstance(); // new ProductListHandler()
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
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {	// 매핑 테이블에 없을 경우 (정의되지 않은 command일 경우)
			handler = new NullHandler();
		}
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